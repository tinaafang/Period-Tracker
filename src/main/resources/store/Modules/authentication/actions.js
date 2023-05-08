import helper from "../../../helper";
import router from "../../../routers/routers";
import {debug} from "webpack";

export default {
    login({state, commit}) {
        return helper.api("POST","/login",state.loginRequest)
            .then((response) => {
                if(response) {
                        localStorage.removeItem('jwtToken');
                        const token = response.token;
                        localStorage.setItem('jwtToken',token.toString());
                        commit("ui/OPEN_ALERT",{message:"You are now logged in",color:'green'},{root:true})
                }
            });
    },
    test({state, commit}) {
        return helper.api("GET","/api/test",state.loginRequest)
            .then((response) => {
                if(response) {
                    console.log(response);
                }
            })
    },
    register({state, commit}) {
        return helper.api("POST","/register",state.registerRequest)
            .then((response) => {
                if(response) {
                    debugger
                    commit("authentication/SEND_ACCOUNT_REGISTRATION_EMAIL_SUCCESS",response.token,{root:true});
                    const message = "A validation email has been sent and will be expired in 15 minutes. If you don't see it, it's probably in the spam folder."
                    commit("ui/OPEN_ALERT",{message:message,color:'yellow'},{root:true});
                }
            })
    },
    resendValidationEmail({state, commit}) {
        return helper.api("POST","/register/resend?email="+state.registerRequest.email)
            .then((response) => {
                if (response) {
                    commit("authentication/SEND_RESET_PASSWORD_EMAIL_SUCCESS", response.token, {root: true});
                    const message = "A validation email has been resent."
                    commit("ui/OPEN_ALERT", {message: message, color: 'yellow'}, {root: true})
                }
            });
    },
    logout() {
        localStorage.removeItem("jwtToken");
        router.push('/');
    },
    sendResetPasswordEmail({state, commit}) {
        return helper.api("GET","/reset-password/send-email?email="+state.resetPasswordRequest.email)
            .then((response) => {
                if(response) {
                    commit("authentication/SEND_RESET_PASSWORD_EMAIL_SUCCESS", response.token, {root: true});
                }
            })
    },
    resetPassword({state, commit}) {
        return helper.api("POST","/reset-password/reset",state.resetPasswordRequest)
            .then((response) => {
                if(response) {
                    console.log(response)
                }
            })
    },
}