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
                        <v-card-title>订单详情</v-card-title>

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
<#--                                <v-select-->
<#--                                        v-model="orderInfo.salesman"-->
<#--                                        :items="salesmans"-->
<#--                                        filled-->
<#--                                        label="销售姓名"-->
<#--                                        disabled-->
<#--                                ></v-select>-->
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
<#--                                <v-select-->
<#--                                        v-model="orderInfo.nameGiver"-->
<#--                                        :items="nameGivers"-->
<#--                                        filled-->
<#--                                        label="指定起名师"-->
<#--                                        disabled-->
<#--                                ></v-select>-->
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
<#--                                <v-select-->
<#--                                        v-model="orderInfo.plan"-->
<#--                                        :items="plans"-->
<#--                                        filled-->
<#--                                        label="套餐选择"-->
<#--                                        disabled-->
<#--                                ></v-select>-->
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
<#--                                <v-select-->
<#--                                        v-model="orderInfo.sex"-->
<#--                                        :items="sexes"-->
<#--                                        filled-->
<#--                                        label="性别"-->
<#--                                        disabled-->
<#--                                ></v-select>-->
                                <v-text-field
                                        v-model="orderInfo.nameSize"
                                        label="名字字数"
                                        disabled
                                ></v-text-field>
<#--                                <v-combobox-->
<#--                                        v-model="nameSizeArray"-->
<#--                                        :items="nameSizes"-->
<#--                                        label="名字字数"-->
<#--                                        multiple-->
<#--                                        chips-->
<#--                                        disabled-->
<#--                                ></v-combobox>-->
                                <v-text-field
                                        v-model="orderInfo.birthday"
                                        label="生日"
                                        disabled
                                ></v-text-field>
<#--                                <v-menu-->
<#--                                        v-model="dateMenu"-->
<#--                                        :close-on-content-click="false"-->
<#--                                        :nudge-right="40"-->
<#--                                        transition="scale-transition"-->
<#--                                        offset-y-->
<#--                                        min-width="auto"-->
<#--                                        disabled-->
<#--                                >-->
<#--                                    <template v-slot:activator="{ on, attrs }">-->
<#--                                        <v-text-field-->
<#--                                                v-model="orderInfo.birthday"-->
<#--                                                label="生日"-->
<#--                                                prepend-icon="mdi-calendar"-->
<#--                                                readonly-->
<#--                                                v-bind="attrs"-->
<#--                                                v-on="on"-->
<#--                                                disabled-->
<#--                                        ></v-text-field>-->
<#--                                    </template>-->
<#--                                    <v-date-picker-->
<#--                                            v-model="orderInfo.birthday"-->
<#--                                            @input="dateMenu = false"-->
<#--                                            locale="zh-cn"-->
<#--                                    ></v-date-picker>-->
<#--                                </v-menu>-->
                                <v-text-field
                                        v-model="orderInfo.birthdayHour"
                                        label="时(生日)"
                                        disabled
                                ></v-text-field>
<#--                                <v-select-->
<#--                                        v-model="orderInfo.birthdayHour"-->
<#--                                        :items="hours"-->
<#--                                        label="时(生日)"-->
<#--                                        required-->
<#--                                        disabled-->
<#--                                ></v-select>-->
                                <v-text-field
                                        v-model="orderInfo.birthdayMinute"
                                        label="分(生日)"
                                        disabled
                                ></v-text-field>
<#--                                <v-select-->
<#--                                        v-model="orderInfo.birthdayMinute"-->
<#--                                        :items="minutes"-->
<#--                                        label="分(生日)"-->
<#--                                        required-->
<#--                                        disabled-->
<#--                                ></v-select>-->
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
                                        :label="'调整——' + (index+1)"
                                        v-model="item.comment"
                                        disabled
                                >
                                </v-textarea>
<#--                                <v-btn-->
<#--                                        type="submit"-->
<#--                                >-->
<#--                                    上传-->
<#--                                </v-btn>-->
                            </v-form>
                        </v-card-text>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="6">
                        <v-row
                                v-for="(item, index) in generatedCharacterNames"
                                :key="index"
                        >
                            <v-col>
                                <v-card>
                                    <v-card-text>
                                        <v-text-field
                                                label="姓名"
                                                v-model="item.name"
                                                disabled
                                        >
                                        </v-text-field>
                                        <v-text-field
                                                label="拼音"
                                                v-model="item.pinyin"
                                                disabled
                                        >
                                        </v-text-field>
                                        <v-text-field
                                                label="五行"
                                                v-model="item.wuxing"
                                                disabled
                                        >
                                        </v-text-field>
                                        <v-textarea
                                                label="字义"
                                                v-model="item.meaning"
                                        >
                                        </v-textarea>
                                        <v-textarea
                                                label="出处"
                                                v-model="item.source"
                                        >
                                        </v-textarea>
                                    </v-card-text>
                                </v-card>
                            </v-col>
                        </v-row>
                    </v-col>
                    <v-col cols="6">
                        <v-row
                                v-for="(item, index) in generatedNameLibraryNames"
                                :key="index"
                        >
                            <v-col>
                                <v-card>
                                    <v-card-text>
                                        <v-text-field
                                                label="姓名"
                                                v-model="item.name"
                                                disabled
                                        >
                                        </v-text-field>
                                        <v-text-field
                                                label="拼音"
                                                v-model="item.pinyin"
                                                disabled
                                        >
                                        </v-text-field>
                                        <v-text-field
                                                label="五行"
                                                v-model="item.wuxing"
                                                disabled
                                        >
                                        </v-text-field>
                                        <v-textarea
                                                label="字义"
                                                v-model="item.meaning"
                                        >
                                        </v-textarea>
                                        <v-textarea
                                                label="出处"
                                                v-model="item.source"
                                        >
                                        </v-textarea>
                                    </v-card-text>
                                </v-card>
                            </v-col>
                        </v-row>
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
                orderId: "${id}",
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
            axios.get('/order/run/detail/' + this.orderInfo.orderId)
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