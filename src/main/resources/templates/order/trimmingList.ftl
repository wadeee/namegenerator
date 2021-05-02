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
                                        @click="runOrder(item)"
                                >执行
                                </v-btn>
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
            orderList: [],
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
                axios.post('/order/list-data/trimming', {'pageNo': this.pageNo})
                    .then((response) => {
                        this.pageNo = response.data.pageNum
                        this.pageSize = response.data.pages
                        this.orderList = response.data.list
                        for (let item of this.orderList) {
                            item.deliveryTime = item.deliveryTime.substr(0, 16).replace('T', ' ')
                        }
                    })
            },
            runOrder(item) {
                window.location.href = '/order/run/' + item.id
            }
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
        },
    })

</script>
</body>
</html>