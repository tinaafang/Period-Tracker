<template>
    <div class="modal show">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <slot name="header"></slot>
                    <button type="button" class="btn-close" @click="CLOSE_MODAL"></button>
                </div>
                <div class="modal-body">
                    <slot name="body"></slot>
                </div>
                <div class="modal-footer">
                    <slot name="footer"></slot>
                </div>
            </div>
        </div>
    </div>






</template>

<script>
import {mapMutations} from "vuex";

export default {
    name: "Modal",
    props:{
        cleanUpBeforeClose: {
            type: Function,
            required: false
        }
    },
    methods: {
        ...mapMutations([
            "CLOSE_MODAL"
        ]),
    },
    unmounted() {
        if(this.cleanUpBeforeClose !== undefined) {
            this.cleanUpBeforeClose();
        }
    }
}
</script>

<style scoped>
.show {
    display: block !important;
}
</style>