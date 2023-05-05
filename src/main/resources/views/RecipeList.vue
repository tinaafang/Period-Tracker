<template>
<button @click="test">test</button>
</template>

<script>
import {mapActions} from "vuex";

export default {
    name: "RecipeList",
    methods: {
        ...mapActions([
            "test"
        ])
    }
}
</script>

<style scoped>

</style>