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
            right: null,
            visitCnt: null,
        },
        methods: {},
        created() {
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