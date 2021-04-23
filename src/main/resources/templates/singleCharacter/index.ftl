<!DOCTYPE html>
<html lang="en">
<head>
    <#include "/common/header.ftl">
</head>
<body>
<div id="app">
    <v-app>
        <#include "/common/nav.ftl">
        <v-main>
            <v-container>
                <v-row>
                    <v-col>
                        <v-card-title>单字上传</v-card-title>

                        <v-card-text>
                            <v-form
                                    @submit.prevent="submit"
                            >
                                <v-text-field
                                        v-model="singleCharacterForm.boyCharacters"
                                        label="男"
                                ></v-text-field>
                                <v-text-field
                                        v-model="singleCharacterForm.girlCharacters"
                                        label="女"
                                ></v-text-field>
                                <v-btn
                                        type="submit"
                                >
                                    上传
                                </v-btn>
                            </v-form>
                        </v-card-text>
                    </v-col>
                </v-row>
            </v-container>
        </v-main>
        <v-row justify="center">
            <v-dialog
                    v-model="dialog"
                    max-width="480"
                    scrollable
                    persistent
            >
                <v-card>
                    <v-card-title
                            class="headline"
                    >
                        请选择拼音
                    </v-card-title>
                    <v-divider></v-divider>
                    <v-card-text
                            style="height: 350px;"
                    >
                        <v-form
                                @submit.prevent="submitPinyin"
                        >
                            <v-row>
                                <v-col
                                        v-for="(item, index) in pinyinSelected"
                                >
                                    <v-select
                                            v-model="pinyinSelected[index]"
                                            :items="pinyinMap[index]"
                                            filled
                                            :label="index"
                                    ></v-select>
                                </v-col>
                            </v-row>
                        </v-form>
                    </v-card-text>
                    <v-divider></v-divider>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn
                                depressed
                                color="primary"
                                @click="submitPinyin"
                        >
                            确认
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>
        <v-Snackbar
                v-model="errorSnackbar.show"
                :multi-line="errorSnackbar.multiLine"
                :timeout="errorSnackbar.timeout"
        >
            {{ errorSnackbar.message }}
            <template v-slot:action="{ attrs }">
                <v-btn
                        color="red"
                        text
                        v-bind="attrs"
                        @click="errorSnackbar.show = false"
                >
                    关闭
                </v-btn>
            </template>
        </v-Snackbar>
        <v-Snackbar
                v-model="snackbar.show"
                :multi-line="snackbar.multiLine"
                :timeout="snackbar.timeout"
        >
            {{ snackbar.message }}
            <template v-slot:action="{ attrs }">
                <v-btn
                        color="red"
                        text
                        v-bind="attrs"
                        @click="snackbar.show = false"
                >
                    关闭
                </v-btn>
            </template>
        </v-Snackbar>
    </v-app>
</div>

<#include "/common/scripts.ftl">
<script>
    new Vue({
        el: '#app',
        vuetify: new Vuetify({
            theme: {
                dark: false,
            },
        }),
        data: {
            singleCharacterForm: {
                boyCharacters: "",
                girlCharacters: "",
            },
            dialog: false,
            pinyinMap: {},
            pinyinSelected: {},
            charactersAddFailed: {},
            errorSnackbar: {
                message: null,
                show: false,
                timeout: 10000,
                multiLine: true,
            },
            snackbar: {
                message: "所有字均上传成功",
                show: false,
                timeout: 10000,
                multiLine: false,
            },
        },
        methods: {
            submit() {
                axios.post('/single-character', this.singleCharacterForm)
                    .then((response) => {
                        this.errorSnackbar.message = response.data.charactersAddFailed.join(', ') + ' 添加失败'
                        this.charactersAddFailed = response.data.charactersAddFailed
                        if (response.status == 200) {
                            this.pinyinMap = response.data.pinyinSelectMap
                            for (let [key, val] of Object.entries(this.pinyinMap)) {
                                this.pinyinSelected[key] = val[0]
                            }
                            if (Object.keys(this.pinyinMap).length>0) {
                                this.dialog = true
                            } else if (this.charactersAddFailed.length>0) {
                                this.errorSnackbar.show = true
                            } else {
                                this.snackbar.show = true
                            }
                        }
                    })
            },
            submitPinyin() {
                axios.post('/single-character/pinyin', this.pinyinSelected)
                    .then((response) => {
                        this.dialog = false
                        if (this.charactersAddFailed.length>0) {
                            this.errorSnackbar.show = true
                        } else {
                            this.snackbar.show = true
                        }
                    })
            },
        },
        watch: {
            'snackbar.show': function () {
                if (this.snackbar.show) {
                    this.errorSnackbar.show = false
                }
            },
            'errorSnackbar.show': function () {
                if (this.errorSnackbar.show) {
                    this.snackbar.show = false
                }
            },
        },
    })

</script>
</body>
</html>