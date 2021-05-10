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
                        <v-card-title>执行订单</v-card-title>
                        <v-card-text>
                            <v-form
                                    @submit.prevent="submit"
                            >

                                <v-text-field
                                        v-model="orderInfo.orderNumber"
                                        label="订单编号"
                                        disabled
                                ></v-text-field>
                                <v-text-field
                                        v-model="orderInfo.salesman"
                                        label="销售姓名"
                                        disabled
                                ></v-text-field>
                                <v-text-field
                                        v-model="orderInfo.wechatMachine"
                                        label="微信机号"
                                        disabled
                                ></v-text-field>
                                <v-text-field
                                        v-model="orderInfo.nameGiver"
                                        label="指定起名师"
                                        disabled
                                ></v-text-field>
                                <v-text-field
                                        v-model="orderInfo.bills"
                                        label="订单金额"
                                        disabled
                                ></v-text-field>
                                <v-text-field
                                        v-model="orderInfo.plan"
                                        label="套餐选择"
                                        disabled
                                ></v-text-field>
                                <v-text-field
                                        disabled
                                        v-model="orderInfo.deliveryTime"
                                        label="应交付时间"
                                ></v-text-field>
                                <v-text-field
                                        v-model="orderInfo.lastname"
                                        label="姓氏"
                                        disabled
                                ></v-text-field>
                                <v-text-field
                                        v-model="orderInfo.sex"
                                        label="性别"
                                        disabled
                                ></v-text-field>
                                <v-text-field
                                        v-model="orderInfo.nameSize"
                                        label="名字字数"
                                        disabled
                                ></v-text-field>
                                <v-text-field
                                        v-model="orderInfo.birthday"
                                        label="生日"
                                        disabled
                                ></v-text-field>
                                <v-text-field
                                        v-model="orderInfo.birthdayHour"
                                        label="时(生日)"
                                        disabled
                                ></v-text-field>
                                <v-text-field
                                        v-model="orderInfo.birthdayMinute"
                                        label="分(生日)"
                                        disabled
                                ></v-text-field>
                                <v-text-field
                                        v-model="orderInfo.bannedPinyin"
                                        label="禁用拼音"
                                        disabled
                                ></v-text-field>
                                <v-text-field
                                        v-model="orderInfo.bannedCharacter"
                                        label="讨厌的字"
                                        disabled
                                ></v-text-field>
                                <v-text-field
                                        v-model="orderInfo.generation"
                                        label="第二个字固定字（字辈）"
                                        disabled
                                ></v-text-field>
                                <v-textarea
                                        filled
                                        label="风格要求"
                                        v-model="orderInfo.style"
                                        disabled
                                ></v-textarea>
                                <v-textarea
                                        filled
                                        label="其他需求"
                                        v-model="orderInfo.notes"
                                        disabled
                                ></v-textarea>
                                <v-textarea
                                        filled
                                        v-for="(item, index) in comments"
                                        :key="index"
                                        :label="'待调整——' + (index+1)"
                                        v-model="item.comment"
                                        disabled
                                >
                                </v-textarea>
                            </v-form>
                        </v-card-text>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="6">
                        <v-card-title>字库生成名({{generatedCharacterNames.length}})</v-card-title>
                        <v-row
                                v-for="(item, index) in generatedCharacterNames"
                                :key="index"
                        >
                            <v-col>
                                <v-card>
                                    <v-card-text>
                                        <v-list>
                                            <v-list-item>
                                                <v-list-item-action>姓名</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{item.name}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                            <v-list-item>
                                                <v-list-item-action>拼音</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{item.pinyin}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                            <v-list-item>
                                                <v-list-item-action>五行</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{item.wuxing}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                            <v-list-item>
                                                <v-list-item-action>字义</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-content>{{item.meaning}}</v-list-item-content>
                                                </v-list-item-content>
                                            </v-list-item>
                                            <v-list-item>
                                                <v-list-item-action>出处</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-content>{{item.source}}</v-list-item-content>
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-list>
                                    </v-card-text>
                                </v-card>
                            </v-col>
                        </v-row>
                    </v-col>
                    <v-col cols="6">
                        <v-card-title>名库生成名({{generatedNameLibraryNames.length}})</v-card-title>
                        <v-row
                                v-for="(item, index) in generatedNameLibraryNames"
                                :key="index"
                        >
                            <v-col>
                                <v-card>
                                    <v-card-text>
                                        <v-list>
                                            <v-list-item>
                                                <v-list-item-action>姓名</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{item.name}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                            <v-list-item>
                                                <v-list-item-action>拼音</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{item.pinyin}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                            <v-list-item>
                                                <v-list-item-action>五行</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{item.wuxing}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                            <v-list-item>
                                                <v-list-item-action>字义</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-content>{{item.meaning}}</v-list-item-content>
                                                </v-list-item-content>
                                            </v-list-item>
                                            <v-list-item>
                                                <v-list-item-action>出处</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-content>{{item.source}}</v-list-item-content>
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-list>
                                    </v-card-text>
                                </v-card>
                            </v-col>
                        </v-row>
                    </v-col>
                    <v-col>
                        <v-card-text>
                            <v-btn
                                    depressed
                                    @click="generateCharactersName"
                            >
                                字库名字再生成
                            </v-btn>
                            <v-btn
                                    depressed
                                    @click="generateNameLibraryName"
                            >
                                名库名字再生成
                            </v-btn>
                            <v-btn
                                    depressed
                                    color="primary"
                                    @click="deliver"
                            >
                                交付
                            </v-btn>
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
            orderInfo: {
                id: "${id}",
                orderNumber: null,
                salesman: "婷婷",
                wechatMachine: null,
                nameGiver: "陈嘉清",
                bills: null,
                plan: "八字起名套餐3【选择本套餐请求红铟八字判断喜用五行及命局接口】",
                tillDeliveryTime: 48,
                lastname: null,
                sex: "未知",
                nameSize: "二字名",
                birthday: null,
                birthdayHour: 1,
                birthdayMinute: 1,
                bannedPinyin: null,
                bannedCharacter: null,
                generation: null,
                style: null,
                notes: null,
            },
            comments: [],
            generatedCharacterNames: [
                {
                    name: null,
                    pinyin: null,
                    wuxing: null,
                    meaning: null,
                    source: null,
                },
            ],
            generatedNameLibraryNames: [
                {
                    name: null,
                    pinyin: null,
                    wuxing: null,
                    meaning: null,
                    source: null,
                },
            ],
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
            progress: {
                value: 0,
                show: false,
                query: true,
            }
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
                this.progress.show = true
                axios.post('/order', this.orderForm)
                    .then((response) => {
                        if (response.status == 200) {
                            this.snackbar.message = "订单已生成"
                            this.progress.show = false
                            this.snackbar.show = true
                        } else {
                            this.errorSnackbar.message = "订单生成失败"
                            this.errorSnackbar.show = true
                        }
                    })
            },
            deliver() {
                axios.get('/order/deliver/' + this.orderInfo.id)
                    .then((response) => {
                        if (response.status == 200) {
                            this.snackbar.message = "订单已交付"
                            this.progress.show = false
                            this.snackbar.show = true
                        } else {
                            this.errorSnackbar.message = "订单交付失败"
                            this.errorSnackbar.show = true
                        }
                    })
            },
            generateCharactersName() {
                axios.get('/name-generator/characters/' + this.orderInfo.id)
                    .then((response) => {
                        if (response.status == 200) {
                            this.generatedCharacterNames = response.data
                        }
                    })
            },
            generateNameLibraryName() {
                axios.get('/name-generator/name-library/' + this.orderInfo.id)
                    .then((response) => {
                        if (response.status == 200) {
                            this.generatedNameLibraryNames = response.data
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
            'nameSizeArray': function () {
                this.orderForm.nameSize = this.nameSizeArray.join(', ')
            },
        },
        created() {
            axios.get('/getVisitCnt')
                .then((response) => {
                    this.visitCnt = response.data;
                })
            axios.get('/order/run/detail/' + this.orderInfo.id)
                .then((response) => {
                    this.orderInfo = response.data.orderInfo
                    this.comments = response.data.comments
                    this.generatedCharacterNames = response.data.generatedCharacterNames
                    this.generatedNameLibraryNames = response.data.generatedNameLibraryNames
                })
        },
    })

</script>
</body>
</html>