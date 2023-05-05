import helper from "../../../helper";

export default {
    login({state, commit}) {
        return helper.api("POST","/login",state.loginRequest)
            .then(response => {
                debugger
            console.log("success")
            })
            .catch(error => {
                debugger
                commit("OPEN_ALERT",{message:"sth went wrong",color:'red'})
                console.log("error")
            })
    },
    test({state, commit}) {
        return helper.api("POST","/login",state.loginRequest)
            .then(response => {
                debugger
                console.log("success")
            })
            .catch(error => {
                debugger
                commit("OPEN_ALERT",{message:"sth went wrong",color:'red'})
                console.log("error")
            })
    },
    register({state, commit}) {
        return helper.api("POST","/register",state.registerRequest)
            .then(response => {
                debugger
                console.log("success")
            })
            .catch(error => {
                debugger
                commit("OPEN_ALERT",{message:"sth went wrong",color:'red'})
                console.log("error")
            })
    }
}