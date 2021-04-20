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
                                        v-model="singleCharacterVo.boyCharacters"
                                        label="男"
                                ></v-text-field>
                                <v-text-field
                                        v-model="singleCharacterVo.girlCharacters"
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
                    persistent
            >
                <v-card>
                    <v-card-title class="headline">
                        请选择合适的拼音
                    </v-card-title>

                    <v-card-text>
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

                    <v-card-actions>
                        <v-spacer></v-spacer>

                        <v-btn
                                color="green darken-1"
                                text
                                @click="submitPinyin"
                        >
                            选定
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>
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
            singleCharacterVo: {
                boyCharacters: "",
                girlCharacters: "",
            },
            dialog: false,
            pinyinMap: {},
            pinyinSelected: {},
        },
        methods: {
            submit() {
                axios.post('/single-character', this.singleCharacterVo)
                    .then((response) => {
                        this.pinyinMap = response.data
                        for (let [key, val] of Object.entries(this.pinyinMap)) {
                            this.pinyinSelected[key] = val[0]
                        }
                        this.dialog = true;
                    }).catch((response) => {
                    console.log("错误" + response)
                })
            },
            submitPinyin() {
                axios.post('/single-character/pinyin', this.pinyinSelected)
                    .then((response) => {
                        this.dialog = false
                        window.location.href = "/single-character"
                    }).catch((response) => {
                    console.log("错误" + response)
                })
            },
        },
    })

</script>
</body>
</html>