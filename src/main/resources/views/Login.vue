<template>
    <div class="container">
        {{getLoginRequest}}
            <h2 class="mb-3">Login</h2>
            <div class="input">
                <label for="email">Email address</label>
                <input
                        class="form-control"
                        type="text"
                        name="email"
                        placeholder="enter email"

                        v-model="email"
                />
            </div>
            <div class="input">
                <label for="password">Password</label>
                <input
                        class="form-control"
                        type="password"
                        name="password"
                        placeholder="enter password"
                        v-model="password"
                />
            </div>
            <div class="alternative-option mt-4">
                Don't have an account? <button @click="$router.push('/register')">Register</button>
            </div>
            <button type="submit" class="mt-4 btn-pers" @click="login">
                Login
            </button>
        <button type="submit" class="mt-4 btn-pers" @click="openResetPasswordMethod">
            forgetPassword
        </button>
        <button type="submit" class="mt-4 btn-pers" @click="$router.push('/')">
            go to home page
        </button>
    </div>
    <reset-password-modal v-if="getActiveModal.name === 'RESET_PASSWORD_MODAL'"></reset-password-modal>
</template>

<script>

import {mapActions, mapGetters, mapMutations} from "vuex";
import { createHelpers } from 'vuex-map-fields';
import ResetPasswordModal from "../components/Modals/ResetPasswordModal.vue";

// `fooModule` is the name of the Vuex module.
const { mapFields } = createHelpers({
    getterType: `authentication/getField`,
    mutationType: `authentication/updateField`,
});
export default {
    name: "Login",
    components: {ResetPasswordModal},
    computed:{
            ...mapFields([
                'loginRequest.email',
                'loginRequest.password'
            ]),
            ...mapGetters("authentication",[
                "getLoginRequest"
            ]),
        ...mapGetters("ui",[
            "getActiveModal"
        ]),
    },
    methods: {
        ...mapActions("authentication",[
            "login",
            "test"
        ]),
        ...mapMutations("ui",[
            "OPEN_MODAL"
        ]),
        openResetPasswordMethod() {
            this.OPEN_MODAL({
                name:'RESET_PASSWORD_MODAL'
            })
        }

    }
}
</script>

<style scoped>

</style>