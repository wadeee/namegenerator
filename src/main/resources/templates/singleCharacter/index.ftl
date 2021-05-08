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
                                        v-model="searchInfo.character"
                                        label="字符"
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
                                    <v-select
                                            v-model="charaterInfo.pinyin"
                                            :items="pinyins"
                                            filled
                                            label="拼音"
                                    ></v-select>
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
                                            v-model="charaterInfo.male"
                                            label="男"
                                    ></v-checkbox>
                                    <v-checkbox
                                            v-model="charaterInfo.female"
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
                                        color="primary"
                                        @click="updateCharacter"
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
            visitCnt: null,
            searchInfo: {
                character: null,
            },
            charaterInfo: {
                character: null,
                idiom: null,
                meaning: null,
                pinyin: null,
                poetry: null,
                wuxing: null,
                male: false,
                female: false,
            },
            dialog: false,
            pinyins: [],
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
                axios.post('/single-character', this.searchInfo)
                    .then((response) => {
                        this.charaterInfo = response.data
                        this.pinyins = this.charaterInfo.pinyin.split(/，/)
                        this.charaterInfo.pinyin = this.pinyins[0]
                        this.errorSnackbar.show = false
                        this.dialog = true
                    })
            },
            updateCharacter() {
                axios.post('/single-character-manage/update', this.charaterInfo)
                    .then((response) => {
                        this.dialog = false
                        this.snackbar.message = this.searchInfo.character + " 字已上传"
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
        created() {
            axios.get('/getVisitCnt')
                .then((response) => {
                    this.visitCnt = response.data;
                })
        },
    })

</script>
</body>
</html>