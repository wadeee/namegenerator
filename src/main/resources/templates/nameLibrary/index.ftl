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
                        <v-card-title>名库上传</v-card-title>

                        <v-card-text>
                            <v-form
                                    @submit.prevent="submit"
                            >
                                <v-text-field
                                        v-model="nameLibraryForm.name"
                                        label="姓名"
                                ></v-text-field>
                                <v-textarea
                                        filled
                                        label="整体寓意"
                                        v-model="nameLibraryForm.meaning"
                                ></v-textarea>
                                <v-textarea
                                        filled
                                        v-model="nameLibraryForm.source"
                                        label="出处"
                                ></v-textarea>
                                <v-checkbox
                                        v-model="nameLibraryForm.male"
                                        label="男"
                                ></v-checkbox>
                                <v-checkbox
                                        v-model="nameLibraryForm.female"
                                        label="女"
                                ></v-checkbox>
                                <v-btn
                                        type="submit"
                                >
                                    上传
                                </v-btn>
                            </v-form>
                        </v-card-text>
                    </v-col>
                </v-row>
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
            </v-container>
        </v-main>
        <#include "/common/snakbar.ftl">
        <#include "/common/errorSnakbar.ftl">
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
            nameLibraryForm: {
                name: null,
                meaning: null,
                source: null,
                male: false,
                female: false,
            },
            pinyinMap: {},
            pinyinSelected: {},
            dialog: false,
            snackbar: {
                message: "上传成功",
                show: false,
                timeout: 10000,
                multiLine: false,
            },
            errorSnackbar: {
                message: null,
                show: false,
                timeout: 10000,
                multiLine: false,
            },
        },
        computed: {
            pinyin: function () {
                let temp = []
                for (let i = 0; i < this.nameLibraryForm.name.length; i++) {
                    temp.push(this.pinyinSelected[this.nameLibraryForm.name[i]])
                }
                return temp.join(' ')
            },
        },
        methods: {
            submit() {
                axios.post('/name-library', this.nameLibraryForm)
                    .then((response) => {
                        if (response.status == 200) {
                            this.pinyinMap = response.data.pinyinSelectMap
                            this.pinyinSelected = {}
                            for (let [key, val] of Object.entries(this.pinyinMap)) {
                                this.pinyinSelected[key] = val[0]
                            }
                            this.dialog = true
                        }
                    })
            },
            submitPinyin() {
                axios.post('/name-library/pinyin', {'pinyin': this.pinyin, 'name': this.nameLibraryForm.name})
                    .then((response) => {
                        this.dialog = false
                        this.snackbar.show = true
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
            'dialog': function () {
                if (this.dialog) {
                    this.errorSnackbar.show = false
                    this.snackbar.show = false
                }
            },
        },
    })

</script>
</body>
</html>