<template>
    <router-view></router-view>
    <Alert v-if="getActiveAlert.message"></Alert>
</template>

<script>
import Alert from "./components/Alert.vue";
import {mapGetters} from "vuex";
export default {
    name: 'App',
    components:{Alert},
    computed:{
        ...mapGetters("ui",[
            "getActiveAlert"
        ])
    }
}
</script>

<style>

/* any styles for the component go here */
</style>