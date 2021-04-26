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
                        <v-card-title>名库管理</v-card-title>
                        <v-card-text>
                            <v-form
                                    @submit.prevent="submit"
                            >
                                <v-text-field
                                        v-model="nameAmount"
                                        label="名字总量"
                                        disabled
                                ></v-text-field>
                                <v-text-field
                                        v-model="searchInfo.name"
                                        label="查找名字"
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
                            v-text="nameInfo.name"
                    >
                    </v-card-title>
                    <v-divider></v-divider>
                    <v-card-text
                            style="height: 470px;"
                    >
                        <v-form
                                @submit.prevent="updateName"
                        >
                            <v-text-field
                                    filled
                                    v-model="nameInfo.name"
                                    label="名字"
                                    disabled
                            ></v-text-field>
                            <v-text-field
                                    filled
                                    v-model="nameInfo.pinyin"
                                    label="拼音"
                            ></v-text-field>
                            <v-textarea
                                    filled
                                    label="寓意"
                                    v-model="nameInfo.meaning"
                            ></v-textarea>
                            <v-text-field
                                    filled
                                    v-model="nameInfo.wuxing"
                                    label="五行"
                            ></v-text-field>
                            <v-textarea
                                    filled
                                    v-model="nameInfo.source"
                                    label="出处"
                            ></v-textarea>
                            <v-checkbox
                                    v-model="nameInfo.male"
                                    label="男"
                            ></v-checkbox>
                            <v-checkbox
                                    v-model="nameInfo.female"
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
                                @click="deleteName"
                        >
                            删除
                        </v-btn>
                        <v-btn
                                depressed
                                color="primary"
                                @click="updateName"
                        >
                            确认
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>
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
            nameAmount: ${nameAmount},
            searchInfo: {
                name: '',
            },
            nameInfo: {
                name: null,
                meaning: null,
                pinyin: null,
                source: null,
                wuxing: null,
                male: false,
                female: false,
            },
            dialog: false,
            errorSnackbar: {
                message: null,
                show: false,
                timeout: 10000,
                multiLine: false,
            },
            snackbar: {
                message: null,
                show: false,
                timeout: 10000,
                multiLine: false,
            },
        },
        methods: {
            submit() {
                axios.post('/name-library-manage', this.searchInfo)
                    .then((response) => {
                        switch (response.status) {
                            case 204:
                                this.errorSnackbar.message = "'" + this.searchInfo.name + "'名字未录入名库"
                                this.errorSnackbar.show = true
                                return
                            case 200:
                                this.nameInfo = response.data
                                this.errorSnackbar.show = false
                                this.dialog = true
                        }
                    })
            },
            updateName() {
                axios.post('/name-library-manage/update', this.nameInfo)
                    .then((response) => {
                        this.dialog = false
                        this.snackbar.message = this.searchInfo.name + " 名字已更新"
                        this.snackbar.show = true
                    })
            },
            deleteName() {
                axios.post('/name-library-manage/delete', this.searchInfo)
                    .then((response) => {
                        this.dialog = false
                        this.errorSnackbar.message = this.searchInfo.name + " 名字已删除"
                        this.errorSnackbar.show = true
                        this.updateAmount()
                    })
            },
            updateAmount() {
                axios.get('/name-library-manage/get-amount')
                    .then((response) => {
                        this.nameAmount = response.data.amount
                    })
            }
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