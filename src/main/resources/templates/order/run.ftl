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
                        top
                ></v-progress-linear>
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
                                        >
                                            <v-list-item>
                                                <v-list-item-action>五行</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>
                                                        <v-row>
                                                            <v-col cols="6">
                                                                <v-text-field
                                                                        v-model="orderInfo.wuxing"
                                                                        class="top-zero"
                                                                ></v-text-field>
                                                            </v-col>
                                                            <v-col cols="6">
                                                                <v-btn
                                                                        depressed
                                                                        @click="submitWuxing"
                                                                        :disabled="progress.show"
                                                                >
                                                                    修改
                                                                </v-btn>
                                                            </v-col>
                                                        </v-row>
                                                    </v-list-item-title>
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
                                                <v-list-item-action>性别</v-list-item-action>
                                                <v-list-item-content>
                                                    <v-list-item-title>{{orderInfo.sex}}</v-list-item-title>
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
                                                <v-list-item-action>固定字（字辈）</v-list-item-action>
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
                                        <v-col cols="6">
                                            <v-list-item>
                                                <v-list-item-action>宜忌</v-list-item-action>
                                                <v-list-item-content>
                                                    {{mingju.yiji}}
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
                    <v-col
                            cols="6"
                            style="margin-bottom: 68px"
                    >
                        <v-card-title>字库生成名({{generatedCharacterNames.length}})</v-card-title>
                        <v-row
                                v-for="(item, index) in generatedCharacterNames"
                                :key="index"
                        >
                            <v-col>
                                <v-expansion-panels
                                        multiple
                                >
                                    <v-btn
                                            :disabled="usedRunInfoNames.has(item.name)"
                                            depressed
                                            :color="runInfoNames.has(item.name) ? 'primary' : 'blue-grey'"
                                            @click="recommend(item)"
                                            width="100%"
                                            small
                                    >
                                    </v-btn>
                                    <v-expansion-panel>
                                        <v-expansion-panel-header>
                                            <v-list>
                                                <v-list-item>
                                                    <v-list-item-action>姓名</v-list-item-action>
                                                    <v-list-item-content>
                                                        <v-list-item-title>
                                                            {{orderInfo.lastname + item.name}}
                                                        </v-list-item-title>
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
                                                    <v-list-item-content class="wrapper">{{item.meaning}}
                                                    </v-list-item-content>
                                                </v-list-item>
                                                <v-list-item>
                                                    <v-list-item-action>出处</v-list-item-action>
                                                    <v-list-item-content class="wrapper">{{item.source}}
                                                    </v-list-item-content>
                                                </v-list-item>
                                            </v-list>
                                            <v-btn
                                                    class="copy"
                                                    depressed
                                                    v-clipboard:copy="copyContent(item)"
                                            >
                                                复制
                                            </v-btn>
                                            <v-btn
                                                    :disabled="runInfoNames.has(item.name) || usedRunInfoNames.has(item.name)"
                                                    depressed
                                                    @click="recommend(item)"
                                            >
                                                推荐
                                            </v-btn>
                                        </v-expansion-panel-content>
                                    </v-expansion-panel>
                                </v-expansion-panels>
                            </v-col>
                        </v-row>
                    </v-col>
                    <v-col
                            cols="6"
                            style="margin-bottom: 68px"
                    >
                        <v-card-title>名库生成名({{generatedNameLibraryNames.length}})</v-card-title>
                        <v-row
                                v-for="(item, index) in generatedNameLibraryNames"
                                :key="index"
                        >
                            <v-col>
                                <v-expansion-panels
                                        multiple
                                >
                                    <v-btn
                                            :disabled="usedRunInfoNames.has(item.name)"
                                            depressed
                                            :color="runInfoNames.has(item.name) ? 'primary' : 'blue-grey'"
                                            @click="recommend(item)"
                                            width="100%"
                                            small
                                    >
                                    </v-btn>
                                    <v-expansion-panel>
                                        <v-expansion-panel-header>
                                            <v-list>
                                                <v-list-item>
                                                    <v-list-item-action>姓名</v-list-item-action>
                                                    <v-list-item-content>
                                                        <v-list-item-title>{{orderInfo.lastname + item.name}}
                                                        </v-list-item-title>
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
                                                    <v-list-item-content class="wrapper">{{item.meaning}}
                                                    </v-list-item-content>
                                                </v-list-item>
                                                <v-list-item>
                                                    <v-list-item-action>出处</v-list-item-action>
                                                    <v-list-item-content class="wrapper">{{item.source}}
                                                    </v-list-item-content>
                                                </v-list-item>
                                            </v-list>
                                            <v-btn
                                                    class="copy"
                                                    depressed
                                                    v-clipboard:copy="copyContent(item)"
                                            >
                                                复制
                                            </v-btn>
                                            <v-btn
                                                    :disabled="runInfoNames.has(item.name) || usedRunInfoNames.has(item.name)"
                                                    depressed
                                                    @click="recommend(item)"
                                            >
                                                推荐
                                            </v-btn>
                                        </v-expansion-panel-content>
                                    </v-expansion-panel>
                                </v-expansion-panels>
                            </v-col>
                        </v-row>
                    </v-col>
                </v-row>
                <v-row justify="center">
                    <v-dialog
                            v-model="dialog"
                            max-width="750"
                            scrollable
                    >
                        <v-card>
                            <v-card-title
                                    class="headline"
                            >
                                Word生成
                            </v-card-title>
                            <v-divider></v-divider>
                            <v-card-text
                                    style="height: 750px;"
                            >
                                <v-form
                                        @submit.prevent="submitRunInfo"
                                >
                                    <v-row
                                            v-for="(item, index) in runInfo"
                                            :key="index">
                                        <v-col>
                                            <v-card>
                                                <v-card-title
                                                        class="headline"
                                                >
                                                    {{item.name}}
                                                    <v-chip
                                                            class="ma-2"
                                                            close
                                                            small
                                                            color="red"
                                                            text-color="white"
                                                            @click="removeRecommend(index)"
                                                            @click:close="removeRecommend(index)"
                                                    >
                                                        删除
                                                    </v-chip>
                                                    <v-chip
                                                            v-if="index>0"
                                                            class="ma-2"
                                                            color="cyan"
                                                            text-color="white"
                                                            @click="upRecommend(index)"
                                                            small
                                                    >
                                                        上移
                                                    </v-chip>
                                                </v-card-title>
                                                <v-card-text>
                                                    <v-text-field
                                                            v-model="item.wuxing"
                                                            label="五行"
                                                    ></v-text-field>
                                                    <v-textarea
                                                            label="意义"
                                                            v-model="item.meaning"
                                                            auto-grow
                                                    >
                                                    </v-textarea>
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
                                        @click="submitRunInfo"
                                        :disabled="progress.show"
                                >
                                    确认
                                </v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                </v-row>
                <v-footer
                        fixed
                        color="white"
                        padless
                        style="left: 256px"
                >
                    <v-card
                            tile
                            width="100%"
                    >
                        <v-card-text>
                            <v-btn
                                    depressed
                                    color="primary"
                                    @click="deliver"
                            >
                                交付
                            </v-btn>
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
                            <v-badge
                                    bordered
                                    color="primary"
                                    :content="runInfo.length"
                                    :value="runInfo.length>0"
                                    overlap
                            >
                                <v-btn
                                        depressed
                                        color="primary"
                                        @click="dialog = true"
                                >
                                    Word生成
                                </v-btn>
                            </v-badge>
                        </v-card-text>
                    </v-card>
                </v-footer>
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
            progress: {
                value: 0,
                show: false,
                query: true,
            },
            dialog: false,
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
            },
            runInfo: [],
            runInfoNames: new Set(),
            usedRunInfoNames: new Set(),
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
            copyContent(item) {
                let result = '姓名：' + this.orderInfo.lastname + item.name + '\n' +
                    '拼音：' + item.pinyin + '\n' +
                    '五行：' + item.wuxing + '\n' +
                    '字义：' + item.meaning + '\n' +
                    '出处：' + item.source + '\n'
                return result
            },
            submitWuxing() {
                this.progress.show = true
                axios.get('/order/updateWuxing/' + this.orderInfo.id + '/' + this.orderInfo.wuxing)
                    .then((response) => {
                        this.progress.show = false
                        if (response.status == 200) {
                            this.snackbar.message = "订单已更新"
                            this.progress.show = false
                            this.snackbar.show = true
                            location.reload()
                        }
                    })
            },
            recommend(item) {
                this.runInfo.push({
                    orderId: this.orderInfo.id,
                    name: item.name,
                    wuxing: item.wuxing,
                    meaning: this.nullToString(item.meaning) + "\n" + this.nullToString(item.source)
                })
                this.runInfoNames.add(item.name)
            },
            removeRecommend(i) {
                this.runInfoNames.delete(this.runInfo[i].name)
                this.runInfo.splice(i, 1)
            },
            upRecommend(i) {
                this.runInfo[i - 1] = this.runInfo.splice(i, 1, this.runInfo[i - 1])[0];
            },
            nullToString(str) {
                return (str == null ? '' : str)
            },
            submitRunInfo() {
                this.progress.show = true
                axios.post('/order/run-info/' + this.orderInfo.id, this.runInfo, {responseType: 'blob'})
                    .then(this.downloadFile)
            },
            downloadFile(response) {
                if (response.status == 200) {
                    this.refreshRunInfo()
                    let url = window.URL.createObjectURL(new Blob([response.data]))
                    let link = document.createElement('a')
                    link.style.display = 'none'
                    link.href = url
                    link.setAttribute('download', this.orderInfo.orderNumber + this.orderInfo.lastname + (this.orderInfo.sex === "未知" ? "" : this.orderInfo.sex) + '起名.docx')
                    document.body.appendChild(link)
                    link.click()
                    this.progress.show = false
                    this.dialog = false
                }
            },
            refreshRunInfo() {
                axios.get('/order/run-info/' + this.orderInfo.id)
                    .then((response) => {
                        if (response.data !== "") {
                            this.usedRunInfoNames = new Set();
                            this.runInfoNames = new Set();
                            this.runInfo = [];
                            for (let i = 0; i < response.data.length; i++) {
                                this.usedRunInfoNames.add(response.data[i].name)
                            }
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
            axios.get('/order/run/detail/' + this.orderInfo.id)
                .then((response) => {
                    this.orderInfo = response.data.orderInfo
                    this.comments = response.data.comments
                    this.generatedCharacterNames = response.data.generatedCharacterNames
                    this.generatedNameLibraryNames = response.data.generatedNameLibraryNames
                })
            axios.get('/order/mingpen/' + this.orderInfo.id)
                .then((response) => {
                    if (response.data === "") {
                        this.mingpen = null
                    } else {
                        this.mingpen = response.data
                    }
                })
            axios.get('/order/mingju/' + this.orderInfo.id)
                .then((response) => {
                    if (response.data === "") {
                        this.mingju = null
                    } else {
                        this.mingju = response.data
                    }
                })
            axios.get('/order/run-info/' + this.orderInfo.id)
                .then((response) => {
                    if (response.data !== "") {
                        for (let i = 0; i < response.data.length; i++) {
                            this.usedRunInfoNames.add(response.data[i].name)
                        }
                    }
                })
            axios.get('/getVisitCnt')
                .then((response) => {
                    this.visitCnt = response.data;
                })
        },
    })

</script>
</body>
</html>