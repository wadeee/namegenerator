<v-navigation-drawer
        absolute
        permanent>
    <v-list-item>
        <v-list-item-content>
            <v-list-item-title class="title">
                起名工具
            </v-list-item-title>
            <v-list-item-subtitle>
                按照五行属性生成名字
            </v-list-item-subtitle>
        </v-list-item-content>
    </v-list-item>

    <v-divider></v-divider>

    <v-list
            dense
            nav
    >
        <v-list-item
                link
                href="/single-character"
        >
            <v-list-item-icon>
                <v-icon>mdi-view-dashboard</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
                <v-list-item-title>字库上传</v-list-item-title>
            </v-list-item-content>
        </v-list-item>
        <v-list-item
                link
                href="/single-character-manage"
                active
        >
            <v-list-item-icon>
                <v-icon>mdi-view-dashboard</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
                <v-list-item-title>字库管理</v-list-item-title>
            </v-list-item-content>
        </v-list-item>
    </v-list>

</v-navigation-drawer>