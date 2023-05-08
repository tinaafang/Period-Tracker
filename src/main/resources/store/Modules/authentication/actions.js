import helper from "../../../helper";
import router from "../../../routers/routers";

export default {
    login({state, commit}) {
        return helper.api("POST","/login",state.loginRequest)
            .then((response) => {
                if(response) {
                    response.json().then(function(data) {
                        localStorage.removeItem('jwtToken');
                        const token = data.token;
                        localStorage.setItem('jwtToken',token.toString());
                        commit("ui/OPEN_ALERT",{message:"You are now logged in",color:'green'},{root:true})
                    })
                }
            });
    },
    test({state, commit}) {
        return helper.api("GET","/api/test",state.loginRequest)
            .then((response) => {
                if(response) {
                    response.json().then(function(data) {
                        console.log(data);
                    })
                }
            })
    },
    register({state, commit}) {
        return helper.api("POST","/register",state.registerRequest)
            .then((response) => {
                if(response) {
                    commit("authentication/SEND_RESET_PASSWORD_EMAIL_SUCCESS",data.token,{root:true});
                    const message = "A validation email has been sent and will be expired in 15 minutes, please follow instructions in the email to finish the registration."
                    commit("ui/OPEN_ALERT",{message:message,color:'yellow'},{root:true});
                }
            })
    },
    resendValidationEmail({state, commit}) {
        return helper.api("POST","/register/resend?email="+state.registerRequest.email)
            .then((response) => {
                if(response) {
                    response.json().then(function(data) {
                        commit("authentication/SEND_RESET_PASSWORD_EMAIL_SUCCESS",data.token,{root:true});
                        const message = "A validation email has been resent."
                        commit("ui/OPEN_ALERT",{message:message,color:'yellow'},{root:true})
                    })
                }
            })
    },
    logout() {
        localStorage.removeItem("jwtToken");
        router.push('/');
    },
    sendResetPasswordEmail({state, commit}) {
        return helper.api("GET","/reset-password/send-email?email="+state.resetPasswordRequest.email)
            .then((response) => {
                if(response) {
                    response.json().then(function(data) {
                        commit("authentication/SEND_RESET_PASSWORD_EMAIL_SUCCESS",data.token,{root:true});
                    })
                }
            })
    },
    resetPassword({state, commit}) {
        return helper.api("POST","/reset-password/reset",state.resetPasswordRequest)
            .then((response) => {
                if(response) {
                    response.json().then(function(data) {
                        console.log(data)
                    })
                }
            })
    },
}