<template>
        <Modal>
        <template v-slot:header>
            Add New Cover Letter Template
        </template>
        <template v-slot:body>
            <label>Name</label>
            <input type="text" class="form-control" placeholder="Enter name">

            <select class="form-select" aria-label="Copy from: ">
                <option value="1">One</option>
                <option value="2">Two</option>
                <option value="3">Three</option>
            </select>
        </template>
        <template v-slot:footer>
            <button type="button" class="btn btn-primary" @click="goToTemplate">Add</button>
        </template>
    </Modal>

</template>

<script>
import Modal from "../components/Modal.vue";
import {mapMutations} from "vuex";
export default {
    name: "AddCoverLetterTemplateModal",
    components: {Modal},
    methods: {
        ...mapMutations([
            "CLOSE_MODAL"
        ]),
        goToTemplate() {
            console.log(111);
            this.$router.push({name: 'template-detail', params: { templateId: 1}});
        }
    }
}
</script>

<style scoped>


</style>