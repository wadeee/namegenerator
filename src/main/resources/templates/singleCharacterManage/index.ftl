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
                        <v-card-title>字库管理</v-card-title>
                        <v-card-text>
                            <v-form
                                    @submit.prevent="submit"
                            >
                                <v-text-field
                                        v-model="characterAmount"
                                        label="单字总量"
                                        disabled
                                ></v-text-field>
                                <v-text-field
                                        v-model="searchInfo.character"
                                        label="查找单字"
                                ></v-text-field>
                                <v-btn
                                        type="submit"
                                >
                                    查找
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
                    scrollable
                    max-width="700"
            >
                <v-card>
                    <v-card-title
                            class="headline"
                            v-text="charaterInfo.character"
                    >
                    </v-card-title>
                    <v-divider></v-divider>
                    <v-card-text
                            style="height: 470px;"
                    >
                        <v-form
                                @submit.prevent="updateCharacter"
                        >
                            <v-text-field
                                    filled
                                    v-model="charaterInfo.character"
                                    label="文字"
                                    disabled
                            ></v-text-field>
                            <v-text-field
                                    filled
                                    v-model="charaterInfo.pinyin"
                                    label="拼音"
                            ></v-text-field>
                            <v-textarea
                                    filled
                                    label="字义"
                                    v-model="charaterInfo.meaning"
                            ></v-textarea>
                            <v-text-field
                                    filled
                                    v-model="charaterInfo.wuxing"
                                    label="五行"
                            ></v-text-field>
                            <v-text-field
                                    filled
                                    v-model="charaterInfo.idiom"
                                    label="成语"
                            ></v-text-field>
                            <v-textarea
                                    filled
                                    label="诗词"
                                    v-model="charaterInfo.poetry"
                            ></v-textarea>
                            <v-checkbox
                                    v-model="charaterInfo.boy"
                                    label="男"
                            ></v-checkbox>
                            <v-checkbox
                                    v-model="charaterInfo.girl"
                                    label="女"
                            ></v-checkbox>
                        </v-form>
                    </v-card-text>
                    <v-divider></v-divider>
                    <v-card-actions>
                        <v-spacer></v-spacer>

                        <v-btn
                                depressed
                                @click="dialog = false"
                        >
                            关闭
                        </v-btn>
                        <v-btn
                                depressed
                                color="error"
                                @click="deleteCharacter"
                        >
                            删除
                        </v-btn>
                        <v-btn
                                depressed
                                color="primary"
                                @click="updateCharacter"
                        >
                            确认
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
            characterAmount: ${characterAmount},
            searchInfo: {
                character: "",
            },
            charaterInfo: {
                character: null,
                idiom: null,
                meaning: null,
                pinyin: null,
                poetry: null,
                wuxing: null,
                boy: false,
                girl: false,
            },
            dialog: false,
        },
        methods: {
            submit() {
                axios.post('/single-character-manage', this.searchInfo)
                    .then((response) => {
                        if (response.data != '') {
                            this.charaterInfo = response.data
                            this.dialog = true
                        }
                    }).catch((response) => {
                    console.log("错误" + response)
                })
            },
            updateCharacter() {
                axios.post('/single-character-manage/update', this.charaterInfo)
                    .then((response) => {
                        this.dialog = false
                    }).catch((response) => {
                    console.log("错误" + response)
                })
            },
            deleteCharacter() {
                axios.post('/single-character-manage/delete', this.searchInfo)
                    .then((response) => {
                        console.log(response.data)
                        this.dialog = false
                        window.location.href = "/single-character-manage"
                    }).catch((response) => {
                    console.log("错误" + response)
                })
            },
        },
    })

</script>
</body>
</html>