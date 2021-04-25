<v-snackbar
        v-model="errorSnackbar.show"
        :multi-line="errorSnackbar.multiLine"
        :timeout="errorSnackbar.timeout"
>
    {{ errorSnackbar.message }}
    <template v-slot:action="{ attrs }">
        <v-btn
                color="red"
                text
                v-bind="attrs"
                @click="errorSnackbar.show = false"
        >
            关闭
        </v-btn>
    </template>
</v-snackbar>