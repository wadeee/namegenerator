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
                        <v-card-title>
                            应回访列表
                            <v-chip
                                    class="ma-2"
                                    v-for="(value, key) in salesmanCount"
                                    :key = 'key'
                            >
                                {{key}} | {{value}}
                            </v-chip>
                        </v-card-title>
                        <v-data-table
                                :headers="headers"
                                :items="customerInfoList"
                                :items-per-page="17"
                                class="elevation-1"
                                hide-default-footer
                        >
                            <template v-slot:item.actions="{ item }">
                                <v-btn
                                        outlined
                                        small
                                        color="error"
                                        @click="deleteCustomer(item)"
                                >删除</v-btn>
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
            salesmanCount: {},
            headers: [
                {
                    text: '微信号',
                    align: 'start',
                    sortable: false,
                    value: 'wechat',
                },
                {
                    text: '微信号机',
                    value: 'wechatMachine',
                    sortable: false,
                },
                {
                    text: '销售姓名',
                    value: 'salesman',
                    sortable: false,
                },
                {
                    text: '应回访时间',
                    value: 'visitDate',
                    sortable: false,
                },
                {
                    text: '操作',
                    value: 'actions',
                    sortable: false,
                },
            ],
            customerInfoList: [
            ],
            dateMenu: false,
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
                axios.post('/customer-info/list-data', {'pageNo': this.pageNo})
                    .then((response) => {
                        this.pageNo = response.data.pageNum
                        this.pageSize = response.data.pages
                        this.customerInfoList = response.data.list
                    })
            },
            deleteCustomer(item) {
                axios.get('/customer-info/delete/' + item.id)
                    .then((response) => {
                        if (response.status == 200) {
                            this.refreshList()
                            this.snackbar.message = "订单删除成功"
                            this.snackbar.show = true
                        }
                    })
            },
            refreshSalesmanCount() {
                axios.get('/customer-info/salesman-count')
                    .then((response) => {
                        this.salesmanCount = response.data
                    })
            },
        },
        watch: {
            'pageNo': function () {
                this.refreshList()
            }
        },
        created() {
            this.refreshList()
            axios.get('/getVisitCnt')
                .then((response) => {
                    this.visitCnt = response.data;
                })
            this.refreshSalesmanCount()
        },
    })

</script>
</body>
</html>