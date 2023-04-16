<template>
    <div id="app">
        <button @click="test">
            hello world
        </button>
<!--        <router-view />-->
    </div>
</template>

<script>
import {mapActions} from "vuex";

export default {
    name: 'App',
    methods: {
        ...mapActions([
            "test"
        ])
    }
}
</script>

<style>
/* any styles for the component go here */
</style>