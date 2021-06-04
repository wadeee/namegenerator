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
            <v-container fluid>
                <v-row>
                    <v-col cols="12">
                        <v-card
                                elevation="6"
                        >
                            <v-simple-table>
                                <template v-slot:default>
                                    <thead>
                                    <tr>
                                        <th class="text-left">
                                            个人信息
                                        </th>
                                        <th class="text-left">
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#list formInfo?keys as key>
                                        <tr>
                                            <td>${key}</td>
                                            <td>${formInfo[key]}</td>
                                        </tr>
                                        </#list>
                                    </tbody>
                                </template>
                            </v-simple-table>
                        </v-card>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="12">
                        <v-card
                                elevation="6"
                        >
                            <#if mingpen??>
                            <v-simple-table>
                                <template v-slot:default>
                                    <thead>
                                    <tr>
                                        <th class="text-left">
                                            八字命盘
                                        </th>
                                        <th class="text-left">
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#list mingpen?keys as key>
                                        <tr>
                                            <td>
                                                <#if pinyinMap[key]??>
                                                    ${pinyinMap[key]}
                                                <#else>
                                                    ${key}
                                                </#if>
                                            </td>
                                            <td>
                                                <#if mingpen[key]??>
                                                    ${mingpen[key]}
                                                </#if>
                                            </td>
                                        </tr>
                                    </#list>
                                    </tbody>
                                </template>
                            </v-simple-table>
                            </#if>
                        </v-card>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="12">
                        <v-card
                                elevation="6"
                        >
                            <#if mingju??>
                            <v-simple-table>
                                <template v-slot:default>
                                    <thead>
                                    <tr>
                                        <th class="text-left">
                                            命局分析
                                        </th>
                                        <th class="text-left">
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#list mingju?keys as key>
                                        <tr>
                                            <td>
                                                <#if pinyinMap[key]??>
                                                    ${pinyinMap[key]}
                                                <#else>
                                                    ${key}
                                                </#if>
                                            </td>
                                            <td>
                                                <#if mingju[key]??>
                                                    ${mingju[key]}
                                                </#if>
                                            </td>
                                        </tr>
                                    </#list>
                                    </tbody>
                                </template>
                            </v-simple-table>
                            </#if>
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