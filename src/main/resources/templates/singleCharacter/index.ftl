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
                        <v-card-title>单字上传</v-card-title>

                        <v-card-text>
                            <v-form
                                    @submit.prevent="submit"
                            >
                                <v-text-field
                                        v-model="singleCharacterVo.boyCharacters"
                                        label="男"
                                ></v-text-field>
                                <v-text-field
                                        v-model="singleCharacterVo.girlCharacters"
                                        label="女"
                                ></v-text-field>
                                <v-btn
                                        type="submit"
                                >
                                    上传
                                </v-btn>
                            </v-form>
                        </v-card-text>
                    </v-col>
                </v-row>
            </v-container>
        </v-main>
        <v-row justify="center">
            <v-dialog
                    v-model="dialog"
                    max-width="290"
            >
                <v-card>
                    <v-card-title class="headline">
                        Use Google's location service?
                    </v-card-title>

                    <v-card-text>
                        Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.
                    </v-card-text>

                    <v-card-actions>
                        <v-spacer></v-spacer>

                        <v-btn
                                color="green darken-1"
                                text
                                @click="dialog = false"
                        >
                            Disagree
                        </v-btn>

                        <v-btn
                                color="green darken-1"
                                text
                                @click="dialog = false"
                        >
                            Agree
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>
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
            singleCharacterVo: {
                boyCharacters: "",
                girlCharacters: "",
            },
            dialog: false,
        },
        methods: {
            submit() {
                axios.post('/single-character', this.singleCharacterVo)
                    .then((response) => {
                        console.log("返回的值" + response.data)
                    }).catch((response) => {
                    console.log("错误" + response)
                })
            },
        },
    })

</script>
</body>
</html>