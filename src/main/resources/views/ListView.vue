<template>

    <div>
    </div>
    <div>
        <button @click="openAddCoverLetterTemplateModal">
        </button>
    </div>
    <AddCoverLetterTemplateModal v-if="this.getActiveModal"/>
</template>

<script>
import AddCoverLetterTemplateModal from "./AddCoverLetterTemplateModal.vue";
import {mapGetters} from "vuex";

export default {
    name: "ListView",
    components: {AddCoverLetterTemplateModal},
    computed: {
        ...mapGetters([
            "getActiveModal"]),
        openAddCoverLetterTemplateModal() {

        }
    }
}
</script>

<style scoped>

</style>