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
                Don't have an account? <a @click="$router.push('/register')">Register</a>
            </div>
            <button type="submit" class="mt-4 btn-pers" @click="login">
                Login
            </button>
        <button type="submit" class="mt-4 btn-pers" @click="test">
            test
        </button>
    </div>
</template>

<script>

import {mapActions, mapGetters} from "vuex";
import { createHelpers } from 'vuex-map-fields';

// `fooModule` is the name of the Vuex module.
const { mapFields } = createHelpers({
    getterType: `authentication/getField`,
    mutationType: `authentication/updateField`,
});
export default {
    name: "Login",
    computed:{
            ...mapFields([
                'loginRequest.email',
                'loginRequest.password'
            ]),
            ...mapGetters("authentication",[
                "getLoginRequest"
            ]),
    },
    methods: {
        ...mapActions("authentication",[
            "login",
            "test"
        ]),

    }
}
</script>

<style scoped>

</style>