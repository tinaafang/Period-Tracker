<template>
    <Alert v-if="getActiveAlert.message"></Alert>
    <router-view></router-view>
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