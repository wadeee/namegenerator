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
                    <v-col cols="12">
                        <v-card
                                elevation="6"
                                width="900"
                        >
                            <v-card-title>{{title}}</v-card-title>
                            <v-card-text>
                                <v-form
                                        ref="form"
                                        lazy-validation
                                        method="post"
                                        action="/hongyan/result"
                                >
                                    <v-text-field
                                            name="lastName"
                                            v-model="formData.lastName"
                                            :counter="10"
                                            label="姓"
                                            required
                                    ></v-text-field>

                                    <v-text-field
                                            name="name"
                                            v-model="formData.name"
                                            label="名"
                                            required
                                    ></v-text-field>

                                    <v-select
                                            name="sex"
                                            v-model="formData.sex"
                                            :items="sexes"
                                            label="性别"
                                            required
                                    ></v-select>

                                    <v-row>
                                        <v-col
                                                cols="4"
                                        >
                                            <v-checkbox
                                                    v-model="formData.payed"
                                                    name="payed"
                                                    value="payed"
                                                    label="已支付"
                                                    required
                                            ></v-checkbox>
                                        </v-col>
                                        <v-col
                                                cols="4"
                                        >
                                            <v-menu
                                                    v-model="menu"
                                                    :close-on-content-click="false"
                                                    :nudge-right="40"
                                                    transition="scale-transition"
                                                    offset-y
                                                    min-width="auto"
                                            >
                                                <template v-slot:activator="{ on, attrs }">
                                                    <v-text-field
                                                            name="date"
                                                            v-model="formData.date"
                                                            label="生日"
                                                            prepend-icon="mdi-calendar"
                                                            readonly
                                                            v-bind="attrs"
                                                            v-on="on"
                                                    ></v-text-field>
                                                </template>
                                                <v-date-picker
                                                        v-model="formData.date"
                                                        @input="menu = false"
                                                        locale="zh-cn"
                                                ></v-date-picker>
                                            </v-menu>
                                        </v-col>
                                    </v-row>

                                    <v-select
                                            name="hour"
                                            v-model="formData.hour"
                                            :items="hours"
                                            label="时"
                                            required
                                    ></v-select>

                                    <v-select
                                            name="minute"
                                            v-model="formData.minute"
                                            :items="minutes"
                                            label="分"
                                            required
                                    ></v-select>

                                    <v-row>
                                        <v-col
                                                cols="4"
                                        >
                                            <v-checkbox
                                                    name="service"
                                                    value="mingpen"
                                                    v-model="formData.service"
                                                    label="八字命盘"
                                            ></v-checkbox>
                                        </v-col>
                                        <v-col
                                                cols="4"
                                        >
                                            <v-checkbox
                                                    name="service"
                                                    value="mingju"
                                                    v-model="formData.service"
                                                    label="命局分析"
                                            ></v-checkbox>
                                        </v-col>
                                    </v-row>
                                    <v-btn
                                            class="mr-4"
                                            type="submit"
                                    >
                                        算命
                                    </v-btn>
                                </v-form>
                            </v-card-text>
                        </v-card>
                    </v-col>
                </v-row>
            </v-container>
        </v-main>
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
                formData: {
                    lastName: null,
                    name: null,
                    date: new Date().toISOString().substr(0, 10),
                    hour: null,
                    minute: null,
                    payed: ["payed"],
                    sex: null,
                    service: [],
                },
                title: '${title}',
                menu: false,
                sexes: [
                    '男',
                    '女',
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
                visitCnt: null,
            },
            methods: {},
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