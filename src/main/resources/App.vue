<template>
    <div>
    </div>
    <div>
        <button @click="openAddCoverLetterTemplateModal">
            Add
        </button>
        <button @click="CLOSE_MODAL">
            close
        </button>
    </div>
    <AddCoverLetterTemplateModal v-if="this.getActiveModal.name === 'ADD_COVER_LETTER_TEMPLATE_MODAL'"/>
</template>

<script>
import {mapActions, mapGetters, mapMutations} from "vuex";
import AddCoverLetterTemplateModal from "./views/AddCoverLetterTemplateModal.vue";

export default {
    name: 'App',
    components: {AddCoverLetterTemplateModal},
    computed: {
        ...mapGetters([
        "getActiveModal"])
    },
    methods: {
        ...mapMutations([
            "OPEN_MODAL",
            "CLOSE_MODAL"
        ]),
        openAddCoverLetterTemplateModal() {
            this.OPEN_MODAL({
                name: "ADD_COVER_LETTER_TEMPLATE_MODAL",
                data:"idk yet"
            })
        }
    },
    unmounted() {
    }
}
</script>

<style>
/* any styles for the component go here */
</style>