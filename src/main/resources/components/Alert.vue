<template>
    <div class="alert show" :class="colorToClassName" role="alert">
        {{message}}
        <button type="button" @click="CLOSE_ALERT">
        </button>
    </div>
</template>

<script>
import {mapGetters, mapMutations} from "vuex";

export default {
    name: "Alert",
    // props: {
    //     message: {
    //         type: String,
    //         required: true
    //     },
    //     color: {
    //         type: String,
    //         required: false,
    //         default: 'yellow'
    //     },
    // },
    computed: {
        ...mapGetters("ui",[
            "getActiveAlert"
        ]),
        message() {
            return this.getActiveAlert.message;
        },
        color() {
            return this.getActiveAlert.color? this.getActiveAlert.color: 'yellow';
        },
        colorToClassName() {
            if(this.color === 'red') {
                return 'alert-danger';
            }
            if(this.color === 'yellow') {
                return 'alert-warning';
            }
            if(this.color === 'green') {
                return 'alert-success';
            }
        }
    },
    methods: {
        ...mapMutations("ui",[
            "CLOSE_ALERT"
        ])
    }
}
</script>

<style scoped>
.show {
    display: block !important;
}
</style>