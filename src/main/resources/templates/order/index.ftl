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
                <v-progress-linear
                        v-model="progress.value"
                        :active="progress.show"
                        :indeterminate="progress.query"
                        :query="progress.query"
                        fixed
                        bottom
                ></v-progress-linear>
                <v-row>
                    <v-col>
                        <v-card-title>
                            添加订单
                            <v-chip
                                    class="mr-2"
                                    @click="getClipboardContent"
                                    small
                            >
                                <v-icon left small>
                                    mdi-brightness-5
                                </v-icon>
                                读取粘贴板
                            </v-chip>
                        </v-card-title>

                        <v-card-text>
                            <v-form
                                    @submit.prevent="submit"
                                    ref="form"
                            >
                                <v-row>
                                    <v-col cols="4">
                                        <v-text-field
                                                v-model="orderForm.orderNumber"
                                                label="订单编号"
                                                :rules="rules"
                                        ></v-text-field>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-select
                                                v-model="orderForm.salesman"
                                                :items="salesmans"
                                                label="销售姓名"
                                                :rules="rules"
                                        ></v-select>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-text-field
                                                v-model="orderForm.wechatMachine"
                                                label="微信机号"
                                                :rules="rules"
                                        ></v-text-field>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-select
                                                v-model="orderForm.nameGiver"
                                                :items="nameGivers"
                                                label="指定起名师"
                                                :rules="rules"
                                        ></v-select>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-text-field
                                                v-model="orderForm.bills"
                                                label="订单金额"
                                                :rules="rules"
                                        ></v-text-field>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-select
                                                v-model="orderForm.plan"
                                                :items="plans"
                                                label="套餐选择"
                                                :rules="rules"
                                        ></v-select>
                                    </v-col>
                                    <v-col
                                            cols="4"
                                            v-if="orderForm.plan!=null && orderForm.plan.startsWith('寓意')"
                                    >
                                        <v-text-field
                                                v-model="orderForm.wuxing"
                                                label="五行"
                                        ></v-text-field>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-select
                                                v-model="orderForm.tillDeliveryTime"
                                                :items="tillDeliveryTimes"
                                                label="应交付时间(小时)"
                                                :rules="rules"
                                        ></v-select>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-text-field
                                                v-model="orderForm.lastname"
                                                label="姓氏"
                                                :rules="rules"
                                        ></v-text-field>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-select
                                                v-model="orderForm.sex"
                                                :items="sexes"
                                                label="性别"
                                                :rules="rules"
                                        ></v-select>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-combobox
                                                v-model="nameSizeArray"
                                                :items="nameSizes"
                                                label="名字字数"
                                                multiple
                                                chips
                                                :rules="listRules"
                                        ></v-combobox>
                                    </v-col>
                                    <v-col cols="4">
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
                                                        v-model="orderForm.birthday"
                                                        label="生日"
                                                        prepend-icon="mdi-calendar"
                                                        readonly
                                                        v-bind="attrs"
                                                        v-on="on"
                                                ></v-text-field>
                                            </template>
                                            <v-date-picker
                                                    v-model="orderForm.birthday"
                                                    @input="dateMenu = false"
                                                    locale="zh-cn"
                                            ></v-date-picker>
                                        </v-menu>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-select
                                                v-model="orderForm.birthdayHour"
                                                :items="hours"
                                                label="时(生日)"
                                                required
                                        ></v-select>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-select
                                                v-model="orderForm.birthdayMinute"
                                                :items="minutes"
                                                label="分(生日)"
                                                required
                                        ></v-select>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-text-field
                                                v-model="orderForm.bannedPinyin"
                                                label="禁用拼音"
                                        ></v-text-field>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-text-field
                                                v-model="orderForm.bannedCharacter"
                                                label="讨厌的字"
                                        ></v-text-field>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-text-field
                                                v-model="orderForm.generation"
                                                label="第二个字固定字（字辈）"
                                        ></v-text-field>
                                    </v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="6">
                                        <v-textarea
                                                label="风格要求"
                                                v-model="orderForm.style"
                                        ></v-textarea>
                                    </v-col>
                                    <v-col cols="6">
                                        <v-textarea
                                                label="其他需求"
                                                v-model="orderForm.notes"
                                        ></v-textarea>
                                    </v-col>
                                    <v-col cols="6">
                                        <v-btn
                                                type="submit"
                                                :disabled="progress.show"
                                        >
                                            上传
                                        </v-btn>
                                    </v-col>
                                </v-row>
                            </v-form>
                        </v-card-text>
                    </v-col>
                </v-row>
                <v-row justify="center">
                    <v-dialog
                            v-model="dialog"
                            max-width="550"
                            scrollable
                            persistent
                    >
                        <v-card>
                            <v-card-title
                                    class="headline"
                            >
                                请选择五行
                            </v-card-title>
                            <v-divider></v-divider>
                            <v-card-text
                                    style="height: 550px;"
                            >
                                <v-form
                                        @submit.prevent="submitWuxing"
                                >
                                    <v-row>
                                        <v-col>
                                            <v-card elevation="0">
                                                <v-card-title>用神</v-card-title>
                                                <v-card-text>
                                                    <v-row>
                                                        <v-col
                                                                cols="6"
                                                                v-for="item in yongshen"
                                                                :key="item"
                                                        >
                                                            <v-checkbox
                                                                    v-model="selectedWuxings"
                                                                    :label="item"
                                                                    :value="item"
                                                            ></v-checkbox>
                                                        </v-col>
                                                    </v-row>
                                                </v-card-text>
                                            </v-card>
                                        </v-col>
                                    </v-row>
                                    <v-row>
                                        <v-col>
                                            <v-card elevation="0">
                                                <v-card-title>喜神</v-card-title>
                                                <v-card-text>
                                                    <v-row>
                                                        <v-col
                                                                cols="6"
                                                                v-for="item in xishen"
                                                                :key="item"
                                                        >
                                                            <v-checkbox
                                                                    v-model="selectedWuxings"
                                                                    :label="item"
                                                                    :value="item"
                                                            ></v-checkbox>
                                                        </v-col>
                                                    </v-row>
                                                </v-card-text>
                                            </v-card>
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
                                        @click="submitWuxing"
                                        :disabled="progress.show"
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
            rules: [
                value => !!value || '必填',
            ],
            listRules: [
                value => value.length > 0 || '必填',
            ],
            orderForm: {
                orderNumber: null,
                salesman: null,
                wechatMachine: null,
                nameGiver: null,
                bills: null,
                plan: null,
                wuxing: '',
                tillDeliveryTime: null,
                lastname: null,
                sex: null,
                nameSize: null,
                birthday: null,
                birthdayHour: null,
                birthdayMinute: null,
                bannedPinyin: null,
                bannedCharacter: null,
                generation: null,
                style: null,
                notes: null,
            },
            clipboardContent: null,
            dateMenu: false,
            nameSizeArray: [],
            nameSizes: ["二字名", "三字名", "四字名",],
            nameSizeMapper: {
                '2': "二字名",
                '3': "三字名",
                '4': "四字名",
            },
            sexes: ["男", "女", "未知"],
            salesmans: ["婷婷", "肖鑫"],
            nameGivers: ["陈嘉清", "江钰婷"],
            plans: [
                "寓意起名套餐1",
                "寓意起名套餐2",
                "八字起名套餐1【选择本套餐请求红铟八字判断喜用五行】",
                "八字起名套餐2【选择本套餐请求红铟八字判断喜用五行及命局接口】",
                "八字起名套餐3【选择本套餐请求红铟八字判断喜用五行及命局接口】"],
            tillDeliveryTimes: [
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9,
                10,
                11,
                12,
                13,
                14,
                15,
                16,
                17,
                18,
                19,
                20,
                21,
                22,
                23,
                24,
                25,
                26,
                27,
                28,
                29,
                30,
                31,
                32,
                33,
                34,
                35,
                36,
                37,
                38,
                39,
                40,
                41,
                42,
                43,
                44,
                45,
                46,
                47,
                48,
            ],
            hours: [
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9,
                10,
                11,
                12,
                13,
                14,
                15,
                16,
                17,
                18,
                19,
                20,
                21,
                22,
                23,
                24,
            ],
            minutes: [
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9,
                10,
                11,
                12,
                13,
                14,
                15,
                16,
                17,
                18,
                19,
                20,
                21,
                22,
                23,
                24,
                25,
                26,
                27,
                28,
                29,
                30,
                31,
                32,
                33,
                34,
                35,
                36,
                37,
                38,
                39,
                40,
                41,
                42,
                43,
                44,
                45,
                46,
                47,
                48,
                49,
                50,
                51,
                52,
                53,
                54,
                55,
                56,
                57,
                58,
                59,
                60,
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
            },
            xishen: [],
            yongshen: [],
            selectedWuxings: [],
            dialog: false,
            orderId: null,
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
                if (this.validate()) {
                    this.progress.show = true
                    axios.post('/order', this.orderForm)
                        .then((response) => {
                            if (response.status == 200) {
                                this.orderId = response.data.id
                                if (response.data.xiyongshenMap != "" && response.data.xiyongshenMap != null) {
                                    this.xishen = response.data.xiyongshenMap.xishen
                                    this.yongshen = response.data.xiyongshenMap.yongshen
                                    this.selectedWuxings = this.yongshen.concat(this.xishen)
                                    this.progress.show = false
                                    this.dialog = true
                                } else {
                                    this.generateCharactersName()
                                    this.generateNameLibraryName()
                                    this.snackbar.message = "订单已生成"
                                    this.progress.show = false
                                    this.snackbar.show = true
                                }
                            } else {
                                this.errorSnackbar.message = "订单生成失败"
                                this.errorSnackbar.show = true
                            }
                        })
                }
            },
            submitWuxing() {
                this.progress.show = true
                axios.post('/order/updateWuxing/' + this.orderId, this.selectedWuxings)
                    .then((response) => {
                        this.progress.show = false
                        if (response.status == 200) {
                            this.snackbar.message = "订单已生成"
                            this.dialog = false
                            this.progress.show = false
                            this.snackbar.show = true
                        }
                    })
            },
            generateCharactersName() {
                axios.get('/name-generator/characters/' + this.orderId)
                    .then((response) => {
                        if (response.status == 200) {
                            this.generatedCharacterNames = response.data
                        }
                    })
            },
            generateNameLibraryName() {
                axios.get('/name-generator/name-library/' + this.orderId)
                    .then((response) => {
                        if (response.status == 200) {
                            this.generatedNameLibraryNames = response.data
                        }
                    })
            },
            validate() {
                return this.$refs.form.validate()
            },
            getClipboardContent() {
                navigator.clipboard.readText()
                    .then((value) => {
                        this.clipboardContent = value
                        this.orderForm.lastname = this.matcher(value, /(?<=姓氏：[　\s]*)[^　\s]+/)
                        this.orderForm.sex = this.matcher(value, /(?<=性别：[　\s]*)([男女]|未知)/)
                        this.nameSizeArray = this.nameSizeMatcher(value, /(?<=名字字数【姓\+名】：.*)\d+/g)
                        this.orderForm.birthday = this.matcher(value, /(?<=【阳历】：[　\s]*)\d*-\d*-\d*/)
                        this.orderForm.birthdayHour = Number(this.matcher(value, /(?<=【阳历】：.*)\d*(?=:\d*)/))
                        this.orderForm.birthdayMinute = Number(this.matcher(value, /(?<=【阳历】：.*\d*:)\d*/))
                        this.orderForm.bannedPinyin = this.matcher(value, /(?<=需避开长辈的字：[　\s]*)[^　\s].+/)
                        this.orderForm.bannedCharacter = this.matcher(value, /(?<=讨厌的字：[　\s]*)[^　\s].+/)
                        this.orderForm.style = this.matcher(value, /父母名字：[　\s]*[^　\s].+/) + "\n" + this.matcher(value, /(?<=风格要求：[　\s]*)[^　\s].+/)
                        this.orderForm.notes = this.matcher(value, /(?<=其他需求【是否排辈等】：[　\s]*)[^　\s].+/)
                    })
                    .catch(() => {
                        this.errorSnackbar.message = '您的格式不对'
                        this.errorSnackbar.show = true
                    })
            },
            matcher(str, pattern) {
                if (str.match(pattern)) {
                    return str.match(pattern)[0]
                }
                return ''
            },
            nameSizeMatcher(str, pattern) {
                let result = []
                if (str.match(pattern)) {
                    for(let v of str.match(pattern)) {
                        result.push(this.nameSizeMapper[v])
                    }
                }
                return result
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
        },
        mounted() {
            this.validate()
        },
    })

</script>
</body>
</html>