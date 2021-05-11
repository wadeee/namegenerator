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
                        <v-card>
                            <v-card-text>
                            <v-list>
                                <v-row>
                                    <v-col cols="6">
                                        <v-list-item>
                                            <v-list-item-action>订单编号</v-list-item-action>
                                            <v-list-item-content>
                                                <v-list-item-title>{{orderInfo.orderNumber}}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-col>
                                    <v-col cols="6">
                                        <v-list-item>
                                            <v-list-item-action>销售姓名</v-list-item-action>
                                            <v-list-item-content>
                                                <v-list-item-title>{{orderInfo.salesman}}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-col>
                                    <v-col cols="6">
                                        <v-list-item>
                                            <v-list-item-action>微信机号</v-list-item-action>
                                            <v-list-item-content>
                                                <v-list-item-title>{{orderInfo.wechatMachine}}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-col>
                                    <v-col cols="6">
                                        <v-list-item>
                                            <v-list-item-action>指定起名师</v-list-item-action>
                                            <v-list-item-content>
                                                <v-list-item-title>{{orderInfo.nameGiver}}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-col>
                                    <v-col cols="6">
                                        <v-list-item>
                                            <v-list-item-action>订单金额</v-list-item-action>
                                            <v-list-item-content>
                                                <v-list-item-title>{{orderInfo.bills}}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-col>
                                    <v-col cols="6">
                                        <v-list-item>
                                            <v-list-item-action>套餐</v-list-item-action>
                                            <v-list-item-content>
                                                <v-list-item-title>{{orderInfo.plan}}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-col>
                                    <v-col
                                            cols="6"
                                            v-if="orderInfo.wuxing != null && orderInfo.wuxing != ''"
                                    >
                                        <v-list-item>
                                            <v-list-item-action>五行</v-list-item-action>
                                            <v-list-item-content>
                                                <v-list-item-title>{{orderInfo.wuxing}}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-col>
                                    <v-col cols="6">
                                        <v-list-item>
                                            <v-list-item-action>应交付时间</v-list-item-action>
                                            <v-list-item-content>
                                                <v-list-item-title>{{orderInfo.deliveryTime}}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-col>
                                    <v-col cols="6">
                                        <v-list-item>
                                            <v-list-item-action>姓氏</v-list-item-action>
                                            <v-list-item-content>
                                                <v-list-item-title>{{orderInfo.lastname}}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-col>
                                    <v-col cols="6">
                                        <v-list-item>
                                            <v-list-item-action>名字字数</v-list-item-action>
                                            <v-list-item-content>
                                                <v-list-item-title>{{orderInfo.nameSize}}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-col>
                                    <v-col cols="6">
                                        <v-list-item>
                                            <v-list-item-action>生日</v-list-item-action>
                                            <v-list-item-content>
                                                <v-list-item-title>{{orderInfo.birthday}}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-col>
                                    <v-col cols="6">
                                        <v-list-item>
                                            <v-list-item-action>生日(农历)</v-list-item-action>
                                            <v-list-item-content>
                                                <v-list-item-title>{{orderInfo.birthdayLunar}}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-col>
                                    <v-col cols="6">
                                        <v-list-item>
                                            <v-list-item-action>时(生日)</v-list-item-action>
                                            <v-list-item-content>
                                                <v-list-item-title>{{orderInfo.birthdayHour}}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-col>
                                    <v-col cols="6">
                                        <v-list-item>
                                            <v-list-item-action>分(生日)</v-list-item-action>
                                            <v-list-item-content>
                                                <v-list-item-title>{{orderInfo.birthdayMinute}}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-col>
                                    <v-col cols="6">
                                        <v-list-item>
                                            <v-list-item-action>禁用拼音</v-list-item-action>
                                            <v-list-item-content>
                                                <v-list-item-title>{{orderInfo.bannedPinyin}}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-col>
                                    <v-col cols="6">
                                        <v-list-item>
                                            <v-list-item-action>讨厌的字</v-list-item-action>
                                            <v-list-item-content>
                                                <v-list-item-title>{{orderInfo.bannedCharacter}}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-col>
                                    <v-col cols="6">
                                        <v-list-item>
                                            <v-list-item-action>第二个字固定字（字辈）</v-list-item-action>
                                            <v-list-item-content>
                                                <v-list-item-title>{{orderInfo.generation}}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-col>
                                    <v-col cols="12">
                                        <v-list-item>
                                            <v-list-item-action>风格要求</v-list-item-action>
                                            <v-list-item-content>
                                                {{orderInfo.style}}
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-col>
                                    <v-col cols="12">
                                        <v-list-item>
                                            <v-list-item-action>其他需求</v-list-item-action>
                                            <v-list-item-content>
                                                {{orderInfo.notes}}
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-col>
                                    <v-col
                                            cols="12"
                                            v-for="(item, index) in comments"
                                            :key="index"
                                    >
                                        <v-list-item>
                                            <v-list-item-action>待调整——{{index+1}}</v-list-item-action>
                                            <v-list-item-content>
                                                {{item.comment}}
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-col>
                                </v-row>
                            </v-list>
                        </v-card-text>
                        </v-card>
                    </v-col>
                </v-row>
                <v-row
                        v-if="this.mingpen != null"
                >
                    <v-col>
                        <v-card-title>八字命盘</v-card-title>
                        <v-card>
                            <v-card-text>
                                <v-list>
                                    <v-row>
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>主星</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{mingpen.zhuxing}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-col>
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>天干</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{mingpen.tiangan}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-col>
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>地支</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{mingpen.dizhi}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-col>
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>大运</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{mingpen.dayun}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-col>
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>用神</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{mingpen.yongshen}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-col>
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>喜神</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{mingpen.xishen}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-col>
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>忌神</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{mingpen.jishen}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-col>
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>叫运时间</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{mingpen.jiaoyunshijian}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-col>
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>强弱</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{mingpen.qiangruo}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-col>
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>五行</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{mingpen.wuxing}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-col>
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>木</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{mingpen.mu}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-col>
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>金</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{mingpen.jin}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-col>
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>水</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{mingpen.shui}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-col>
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>土</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{mingpen.tu}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-col>
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>火</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{mingpen.huo}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-col>
                                    </v-row>
                                </v-list>
                            </v-card-text>
                        </v-card>
                    </v-col>
                </v-row>
                <v-row
                        v-if="this.mingju != null"
                >
                    <v-col>
                        <v-card-title>命局分析</v-card-title>
                        <v-card>
                            <v-card-text>
                                <v-list>
                                    <v-row>
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>性格</v-list-item-action>
                                                <v-list-item-content>
                                                    {{mingju.xingge}}
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-col>
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>学历</v-list-item-action>
                                                <v-list-item-content>
                                                    {{mingju.xueli}}
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-col>
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>财富事业</v-list-item-action>
                                                <v-list-item-content>
                                                    {{mingju.caifushiye}}
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-col>
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>六亲</v-list-item-action>
                                                <v-list-item-content>
                                                    {{mingju.liuqin}}
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-col>
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>疾病</v-list-item-action>
                                                <v-list-item-content>
                                                    {{mingju.jibing}}
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-col>
                                    </v-row>
                                </v-list>
                            </v-card-text>
                        </v-card>
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
                                <v-expansion-panels
                                        multiple
                                >
                                    <v-expansion-panel>
                                        <v-expansion-panel-header>
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
                                            </v-list>
                                        </v-expansion-panel-header>
                                        <v-expansion-panel-content>
                                            <v-list>
                                                <v-list-item>
                                                    <v-list-item-action>字义</v-list-item-action>
                                                    <v-list-item-content class="wrapper">{{item.meaning}}</v-list-item-content>
                                                </v-list-item>
                                                <v-list-item>
                                                    <v-list-item-action>出处</v-list-item-action>
                                                    <v-list-item-content class="wrapper">{{item.source}}</v-list-item-content>
                                                </v-list-item>
                                            </v-list>
                                        </v-expansion-panel-content>
                                    </v-expansion-panel>
                                </v-expansion-panels>
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
                                <v-expansion-panels
                                        multiple
                                >
                                    <v-expansion-panel>
                                        <v-expansion-panel-header>
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
                                            </v-list>
                                        </v-expansion-panel-header>
                                        <v-expansion-panel-content>
                                            <v-list>
                                                <v-list-item>
                                                    <v-list-item-action>字义</v-list-item-action>
                                                    <v-list-item-content class="wrapper">{{item.meaning}}</v-list-item-content>
                                                </v-list-item>
                                                <v-list-item>
                                                    <v-list-item-action>出处</v-list-item-action>
                                                    <v-list-item-content class="wrapper">{{item.source}}</v-list-item-content>
                                                </v-list-item>
                                            </v-list>
                                        </v-expansion-panel-content>
                                    </v-expansion-panel>
                                </v-expansion-panels>
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
            mingpen: null,
            mingju: null,
            orderInfo: {
                id: "${id}",
                orderNumber: null,
                salesman: "婷婷",
                wechatMachine: null,
                nameGiver: "陈嘉清",
                bills: null,
                plan: "八字起名套餐3【选择本套餐请求红铟八字判断喜用五行及命局接口】",
                wuxing: null,
                tillDeliveryTime: 48,
                lastname: null,
                sex: "未知",
                nameSize: "三字名",
                birthday: null,
                birthdayLunar: null,
                birthdayHour: null,
                birthdayMinute: null,
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
            axios.get('/order/mingpen/' + this.orderInfo.id)
                .then((response) => {
                    if (response.data  === "") {
                        this.mingpen = null
                    } else {
                        this.mingpen = response.data
                    }
                })
            axios.get('/order/mingju/' + this.orderInfo.id)
                .then((response) => {
                    if (response.data  === "") {
                        this.mingju = null
                    } else {
                        this.mingju = response.data
                    }
                })
        },
    })

</script>
</body>
</html>