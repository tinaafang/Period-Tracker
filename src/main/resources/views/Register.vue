<template>
    <div class="container">
        {{getRegisterRequest}}
        <h2 class="mb-3">Register</h2>
        <div class="input">
            <label for="email">User Name</label>
            <input
                    class="form-control"
                    type="text"
                    name="userName"
                    placeholder="enter userName"

                    v-model="userName"
            />
        </div>
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
            Already have an account? <a @click="$router.push('/login')">Login</a>
        </div>
        <button type="submit" class="mt-4 btn-pers" @click="register">
            Register
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
    name: "Register",
    computed:{
        ...mapFields([
            'registerRequest.userName',
            'registerRequest.email',
            'registerRequest.password'
        ]),
        ...mapGetters("authentication",[
            "getRegisterRequest"
        ]),
    },
    methods: {
        ...mapActions("authentication",[
            "register"
        ]),

    }
}
</script>

<style scoped>

</style>