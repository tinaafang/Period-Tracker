<template>
<button @click="test">test</button>
    <button @click="$router.push('login')">go to login page</button>
    <button @click="logout">logout</button>
</template>

<script>
import {mapActions} from "vuex";

export default {
    name: "RecipeList",
    methods: {
        ...mapActions("authentication",[
            "test",
            "logout"
        ])
    }
}
</script>

<style scoped>

</style>