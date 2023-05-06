import helper from "../../../helper";
import router from "../../../routers/routers";

export default {
    login({state, commit}) {
        return helper.api("POST","/login",state.loginRequest)
            .then(function(response) {
                if(!response.ok) {
                    let messagge = "Oops, something went wrong";
                    if(response.status === 401) {
                        messagge = "Unauthorized, please log in and try again"
                    }
                    commit("ui/OPEN_ALERT",{message:messagge,color:'red'},{root:true})
                } else {
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
            .then(function(response) {
                if(!response.ok) {
                    let messagge = "Oops, something went wrong";
                    if(response.status === 401) {
                        messagge = "Unauthorized, please log in and try again"
                    }
                    commit("ui/OPEN_ALERT",{message:messagge,color:'red'},{root:true})
                } else {
                    response.json().then(function(data) {
                        console.log(data);
                    })
                }

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
    },
    logout() {
        localStorage.removeItem("jwtToken");
        router.push('/');
    }
}