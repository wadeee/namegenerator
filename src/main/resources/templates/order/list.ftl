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
                        <v-data-table
                                :headers="headers"
                                :items="orderList"
                                :items-per-page="17"
                                class="elevation-1"
                                hide-default-footer
                        >
                            <template v-slot:item.actions="{ item }">
                                <v-btn
                                        outlined
                                        small
                                        color="teal"
                                        @click="editOrder(item)"
                                >修改</v-btn>
                                <v-btn
                                        v-if="item.status != '已交付'"
                                        outlined
                                        small
                                        color="indigo"
                                        @click="commentOrder(item)"
                                >调整</v-btn>
                            </template>
                        </v-data-table>
                    </v-col>
                </v-row>
                <v-row justify="center">
                    <v-col cols="8">
                        <v-pagination
                                v-model="pageNo"
                                :length="pageSize"
                        ></v-pagination>
                    </v-col>
                </v-row>
                <v-row justify="center">
                    <v-dialog
                            v-model="editDialog"
                            scrollable
                            max-width="800"
                    >
                        <v-card>
                            <v-card-title
                                    class="headline"
                                    v-text="editForm.orderNumber + ' 订单详情'"
                            >
                            </v-card-title>
                            <v-divider></v-divider>
                            <v-card-text>
                                <v-form
                                        @submit.prevent="updateOrder"
                                >
                                    <v-text-field
                                            filled
                                            v-model="editForm.orderNumber"
                                            label="订单编号"
                                            disabled
                                    ></v-text-field>
                                    <v-select
                                            v-model="editForm.salesman"
                                            :items="salesmans"
                                            filled
                                            label="销售姓名"
                                    ></v-select>
                                    <v-text-field
                                            v-model="editForm.wechatMachine"
                                            label="微信机号"
                                    ></v-text-field>
                                    <v-select
                                            v-model="editForm.nameGiver"
                                            :items="nameGivers"
                                            filled
                                            label="指定起名师"
                                    ></v-select>
                                    <v-text-field
                                            v-model="editForm.bills"
                                            label="订单金额"
                                    ></v-text-field>
                                    <v-select
                                            v-model="editForm.plan"
                                            :items="plans"
                                            filled
                                            label="套餐选择"
                                    ></v-select>
                                    <v-text-field
                                            filled
                                            disabled
                                            v-model="editForm.deliveryTime"
                                            label="应交付时间"
                                    ></v-text-field>
                                    <v-text-field
                                            v-model="editForm.lastname"
                                            label="姓氏"
                                    ></v-text-field>
                                    <v-select
                                            v-model="editForm.sex"
                                            :items="sexes"
                                            filled
                                            label="性别"
                                    ></v-select>
                                    <v-combobox
                                            v-model="nameSizeArray"
                                            :items="nameSizes"
                                            label="名字字数"
                                            multiple
                                            chips
                                    ></v-combobox>
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
                                                    v-model="editForm.birthday"
                                                    label="生日"
                                                    prepend-icon="mdi-calendar"
                                                    readonly
                                                    v-bind="attrs"
                                                    v-on="on"
                                            ></v-text-field>
                                        </template>
                                        <v-date-picker
                                                v-model="editForm.birthday"
                                                @input="dateMenu = false"
                                                locale="zh-cn"
                                        ></v-date-picker>
                                    </v-menu>
                                    <v-select
                                            v-model="editForm.birthdayHour"
                                            :items="hours"
                                            label="时(生日)"
                                            required
                                    ></v-select>
                                    <v-select
                                            v-model="editForm.birthdayMinute"
                                            :items="minutes"
                                            label="分(生日)"
                                            required
                                    ></v-select>
                                    <v-text-field
                                            v-model="editForm.bannedPinyin"
                                            label="禁用拼音"
                                    ></v-text-field>
                                    <v-text-field
                                            v-model="editForm.bannedCharacter"
                                            label="讨厌的字"
                                    ></v-text-field>
                                    <v-text-field
                                            v-model="editForm.generation"
                                            label="第二个字固定字（字辈）"
                                    ></v-text-field>
                                    <v-textarea
                                            filled
                                            label="风格要求"
                                            v-model="editForm.style"
                                    ></v-textarea>
                                    <v-textarea
                                            filled
                                            label="其他需求"
                                            v-model="editForm.notes"
                                    ></v-textarea>
                                </v-form>
                            </v-card-text>
                            <v-divider></v-divider>
                            <v-card-actions>
                                <v-spacer></v-spacer>

                                <v-btn
                                        depressed
                                        @click="editDialog = false"
                                >
                                    关闭
                                </v-btn>
                                <v-btn
                                        depressed
                                        color="primary"
                                        @click="updateOrder"
                                >
                                    确认
                                </v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                </v-row>
                <v-row justify="center">
                    <v-dialog
                            v-model="commentsDialog"
                            scrollable
                            max-width="800"
                    >
                        <v-card>
                            <v-card-title
                                    class="headline"
                                    v-text="editForm.orderNumber + ' 订单调整'"
                            >
                            </v-card-title>
                            <v-divider></v-divider>
                            <v-card-text>
                                <v-form
                                        @submit.prevent="addComment"
                                >
                                    <v-text-field
                                            filled
                                            v-model="editForm.orderNumber"
                                            label="订单编号"
                                            disabled
                                    ></v-text-field>
                                    <v-select
                                            v-model="editForm.salesman"
                                            :items="salesmans"
                                            filled
                                            label="销售姓名"
                                            disabled
                                    ></v-select>
                                    <v-text-field
                                            v-model="editForm.wechatMachine"
                                            label="微信机号"
                                            disabled
                                    ></v-text-field>
                                    <v-select
                                            v-model="editForm.nameGiver"
                                            :items="nameGivers"
                                            filled
                                            label="指定起名师"
                                            disabled
                                    ></v-select>
                                    <v-text-field
                                            v-model="editForm.bills"
                                            label="订单金额"
                                            disabled
                                    ></v-text-field>
                                    <v-select
                                            v-model="editForm.plan"
                                            :items="plans"
                                            filled
                                            label="套餐选择"
                                            disabled
                                    ></v-select>
                                    <v-text-field
                                            filled
                                            disabled
                                            v-model="editForm.deliveryTime"
                                            label="应交付时间"
                                            disabled
                                    ></v-text-field>
                                    <v-text-field
                                            v-model="editForm.lastname"
                                            label="姓氏"
                                            disabled
                                    ></v-text-field>
                                    <v-select
                                            v-model="editForm.sex"
                                            :items="sexes"
                                            filled
                                            label="性别"
                                            disabled
                                    ></v-select>
                                    <v-combobox
                                            v-model="nameSizeArray"
                                            :items="nameSizes"
                                            label="名字字数"
                                            multiple
                                            chips
                                            disabled
                                    ></v-combobox>
                                    <v-menu
                                            v-model="dateMenu"
                                            :close-on-content-click="false"
                                            :nudge-right="40"
                                            transition="scale-transition"
                                            offset-y
                                            min-width="auto"
                                            disabled
                                    >
                                        <template v-slot:activator="{ on, attrs }">
                                            <v-text-field
                                                    v-model="editForm.birthday"
                                                    label="生日"
                                                    prepend-icon="mdi-calendar"
                                                    readonly
                                                    v-bind="attrs"
                                                    v-on="on"
                                                    disabled
                                            ></v-text-field>
                                        </template>
                                        <v-date-picker
                                                v-model="editForm.birthday"
                                                @input="dateMenu = false"
                                                locale="zh-cn"
                                        ></v-date-picker>
                                    </v-menu>
                                    <v-select
                                            v-model="editForm.birthdayHour"
                                            :items="hours"
                                            label="时(生日)"
                                            required
                                            disabled
                                    ></v-select>
                                    <v-select
                                            v-model="editForm.birthdayMinute"
                                            :items="minutes"
                                            label="分(生日)"
                                            required
                                            disabled
                                    ></v-select>
                                    <v-text-field
                                            v-model="editForm.bannedPinyin"
                                            label="禁用拼音"
                                            disabled
                                    ></v-text-field>
                                    <v-text-field
                                            v-model="editForm.bannedCharacter"
                                            label="讨厌的字"
                                            disabled
                                    ></v-text-field>
                                    <v-text-field
                                            v-model="editForm.generation"
                                            label="第二个字固定字（字辈）"
                                            disabled
                                    ></v-text-field>
                                    <v-textarea
                                            filled
                                            label="风格要求"
                                            v-model="editForm.style"
                                            disabled
                                    ></v-textarea>
                                    <v-textarea
                                            filled
                                            label="其他需求"
                                            v-model="editForm.notes"
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
                                    <v-textarea
                                            filled
                                            label="本次调整"
                                            v-model="commentForm.comment"
                                    >
                                    </v-textarea>
                                </v-form>
                            </v-card-text>
                            <v-divider></v-divider>
                            <v-card-actions>
                                <v-spacer></v-spacer>

                                <v-btn
                                        depressed
                                        @click="commentsDialog = false"
                                >
                                    关闭
                                </v-btn>
                                <v-btn
                                        depressed
                                        color="primary"
                                        @click="addComment"
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
            headers: [
                {
                    text: '订单编号',
                    align: 'start',
                    sortable: false,
                    value: 'orderNumber',
                },
                {
                    text: '套餐',
                    value: 'plan',
                    sortable: false,
                },
                {
                    text: '应交付时间',
                    value: 'deliveryTime',
                    sortable: false,
                },
                {
                    text: '状态',
                    value: 'status',
                    sortable: false,
                },
                {
                    text: '操作',
                    value: 'actions',
                    sortable: false,
                },
            ],
            orderList: [
            ],
            editForm: {
                id: null,
                orderNumber: null,
                salesman: null,
                wechatMachine: null,
                nameGiver: null,
                bills: null,
                plan: null,
                deliveryTime: null,
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
            commentForm: {
                orderId: null,
                comment: null,
                commentCnt: 0,
            },
            comments: [],
            editDialog: false,
            commentsDialog: false,
            dateMenu: false,
            nameSizeArray: ["二字名"],
            nameSizes: ["二字名", "三字名", "四字名",],
            sexes: ["男", "女", "未知"],
            salesmans: ["销售A", "销售B", "销售C", "销售D"],
            nameGivers: ["起名师A", "起名师B", "起名师C", "起名师D"],
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
            pageNo: 1,
            pageSize: 1,
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
        methods: {
            refreshList() {
                axios.post('/order/list-data', {'pageNo': this.pageNo})
                    .then((response) => {
                        this.pageNo = response.data.pageNum
                        this.pageSize = response.data.pages
                        this.orderList = response.data.list
                        for (let item of this.orderList) {
                            item.deliveryTime = item.deliveryTime.substr(0,16).replace('T',' ')
                        }
                    })
            },
            editOrder(item) {
                axios.get('/order/detail/' + item.id )
                    .then((response) => {
                        this.editForm = response.data
                        this.nameSizeArray = this.editForm.nameSize.split(', ')
                        this.editDialog = true
                    })
            },
            commentOrder(item) {
                this.commentForm.orderId = item.id
                axios.get('/order/detail/' + item.id )
                    .then((response) => {
                        this.editForm = response.data
                    })
                axios.get('/order/comments/' + item.id )
                    .then((response) => {
                        this.comments = response.data
                        this.commentForm.commentCnt = this.comments.length + 1
                        this.commentsDialog = true
                    })
            },
            updateOrder() {
                axios.post('/order/update', this.editForm)
                    .then((response) => {
                        if (response.status == 200) {
                            this.refreshList()
                            this.editDialog = false
                            this.snackbar.message = "订单修改成功"
                            this.snackbar.show = true
                        } else {
                            this.errorSnackbar.message = "订单修改失败"
                            this.errorSnackbar.show = true
                        }
                    })
            },
            addComment() {
                axios.post('/order/comments/add', this.commentForm)
                    .then((response) => {
                        if (response.status == 200) {
                            this.refreshList()
                            this.commentsDialog = false
                            this.snackbar.message = "订单调整成功"
                            this.snackbar.show = true
                            this.commentForm.comment = null
                        } else {
                            this.errorSnackbar.message = "订单调整失败"
                            this.errorSnackbar.show = true
                        }
                    })
            }
        },
        watch: {
            'nameSizeArray': function () {
                this.editForm.nameSize = this.nameSizeArray.join(', ')
            },
            'pageNo': function () {
                this.refreshList()
            }
        },
        created() {
            this.refreshList()
            axios.get('/getVisitCnt')
                .then((response) => {
                    console.log(response.data)
                    this.visitCnt = response.data;
                })
        },
    })

</script>
</body>
</html>