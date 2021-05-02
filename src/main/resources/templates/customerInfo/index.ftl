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
                        <v-card-title>客户信息录入</v-card-title>

                        <v-card-text>
                            <v-form
                                    @submit.prevent="submit"
                            >
                                <v-text-field
                                        v-model="customerInfoForm.wechat"
                                        label="微信号"
                                ></v-text-field>
                                <v-text-field
                                        v-model="customerInfoForm.wechatMachine"
                                        label="微信号机"
                                ></v-text-field>
                                <v-select
                                        v-model="customerInfoForm.salesman"
                                        :items="salesmans"
                                        filled
                                        label="销售姓名"
                                ></v-select>
                                <v-menu
                                        v-model="dateMenu"
                                        :close-on-content-click="false"
                                        :nudge-right="40"
                                        transition="scale-transition"
                                        offset-y
                                        min-width="auto"
                                >
                                    <template v-slot:activator="{ on, attrs }">
                                        <v-text-field
                                                v-model="customerInfoForm.birthday"
                                                label="预产期"
                                                prepend-icon="mdi-calendar"
                                                readonly
                                                v-bind="attrs"
                                                v-on="on"
                                        ></v-text-field>
                                    </template>
                                    <v-date-picker
                                            v-model="customerInfoForm.birthday"
                                            @input="dateMenu = false"
                                            locale="zh-cn"
                                    ></v-date-picker>
                                </v-menu>
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
            customerInfoForm: {
                wechat: null,
                wechatMachine: null,
                salesman: null,
                birthday: null,
            },
            dateMenu: false,
            salesmans: ["销售A", "销售B", "销售C", "销售D"],
            errorSnackbar: {
                message: "客户信息上传失败",
                show: false,
                timeout: 10000,
                multiLine: true,
            },
            snackbar: {
                message: "客户信息上传成功",
                show: false,
                timeout: 10000,
                multiLine: true,
            },
        },
        methods: {
            submit() {
                axios.post('/customer-info/add', this.customerInfoForm)
                    .then((response) => {
                        if (response.status == 200) {
                            this.snackbar.show = true
                        }
                    }).catch(()=> {
                    this.errorSnackbar.show = true
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