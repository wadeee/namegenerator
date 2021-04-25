<v-snackbar
        v-model="snackbar.show"
        :multi-line="snackbar.multiLine"
        :timeout="snackbar.timeout"
        color="blue-grey"
>
    {{ snackbar.message }}
    <template v-slot:action="{ attrs }">
        <v-btn
                text
                v-bind="attrs"
                @click="snackbar.show = false"
        >
            关闭
        </v-btn>
    </template>
</v-snackbar>
