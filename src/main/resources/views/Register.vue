<template>
    <div class="container">
        <h2 class="mb-3">Register</h2>
        <div class="input">
            <label>User Name</label>
            <input
                    class="form-control"
                    type="text"
                    placeholder="enter userName"
                    v-model="userName"
            />
            <p class="text-danger" v-if="validationErrors.userName">{{validationErrors.userName}}</p>
        </div>
        <div class="input">
            <label>Email address</label>
            <input
                    class="form-control"
                    type="text"
                    placeholder="enter email"

                    v-model="email"
            />
            <p class="text-danger" v-if="validationErrors.email">{{validationErrors.email}}</p>
        </div>
        <div class="input">
            <label>Password</label>
            <input
                    class="form-control"
                    type="password"
                    placeholder="enter password"
                    v-model="password"
            />
            <p class="text-danger" v-if="validationErrors.password">{{validationErrors.password}}</p>
        </div>
        <div class="alternative-option mt-4">
            Already have an account? <button @click="$router.push('/login')">Login</button>
        </div>
        <div v-if="getRegisterRequest.token" class="alternative-option mt-4" @click="resendValidationEmail">
            Resend Validation Email
        </div>
        <button type="submit" class="mt-4 btn-pers" @click="sendRegisterRequest">
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
    data() {
        return {
            validationErrors: {
                userName: null,
                password: null,
                email: null
            }
        }
    },
    computed:{
        ...mapFields([
            'registerRequest.userName',
            'registerRequest.email',
            'registerRequest.password'
        ]),
        ...mapGetters("authentication",[
            "getRegisterRequest"
        ]),
        isValidEmail() {
            const emailPattern = new RegExp("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            return emailPattern.test(this.getRegisterRequest.email);
        },
        isValidPassowrd() {
            const lettersAndNumbersPattern = new RegExp("^[A-Za-z0-9]*$");
            const onlyLettersPattern = new RegExp("^[a-zA-Z]+$");
            const onlyNumbersPattern = new RegExp("^[0-9]+$");
            if(this.getRegisterRequest.password.length < 8 ||
                this.getRegisterRequest.password.length > 16 ||
                !lettersAndNumbersPattern.test(this.getRegisterRequest.password) ||
                onlyLettersPattern.test(this.getRegisterRequest.password) ||
                onlyNumbersPattern.test(this.getRegisterRequest.password))
            {
                return false;
            }
            return true;
        }
    },
    methods: {
        ...mapActions("authentication",[
            "register",
            "resendValidationEmail"
        ]),
        validate() {
            this.validationErrors =  {
                    userName: null,
                    password: null,
                    email: null
            };
            if(!this.getRegisterRequest.userName) {
                this.validationErrors.userName = "User name cannot be empty";
            }

            if(this.getRegisterRequest.email === null){
                this.validationErrors.email = "Email cannot be empty";
            } else if(!this.isValidEmail) {
                this.validationErrors.email = "Invalid Email";
            }

            if(!this.getRegisterRequest.password){
                this.validationErrors.password = "Password cannot be empty";
            } else if(!this.isValidPassowrd) {
                this.validationErrors.password = "Password should be a combination of letters and numbers and is 8-16 characters long";
            }

            if(this.validationErrors.userName || this.validationErrors.password || this.validationErrors.email) {
                return false;
            }
            return true;
        },
        sendRegisterRequest() {
            if(this.validate()) {
                this.register();
            }
        }

    }
}
</script>

<style scoped>

</style>