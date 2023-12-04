import {openAlert} from "./store/uiSlice";
import store from "./store/store";
export default {
    api(method, url, body=null) {
        if (method === "GET" || method === "DELETE") {
            return fetch("http://localhost:3000"+url, {
                method: method,
                headers: {
                    'accept': "application/json",
                    'Content-Type': "application/json",
                    'Authorization': 'Bearer ' + localStorage.getItem('jwt')
                }

            }).then((response) =>
            {
                if (response.ok) {
                    return response.json();
                } else {
                    response.json().then((error) => {
                        store.dispatch(openAlert({message:error.error,color:'red'}))
                    });
                }
            });
        } else if (method === "POST" || method === "PUT") {
            return fetch("http://localhost:3000"+url, {
                method: method,
                headers: {
                    'accept': "application/json",
                    'Content-Type': "application/json",
                    'Authorization': 'Bearer ' + localStorage.getItem('jwt')
                },
                body: JSON.stringify(body)
            }).then((response) =>
            {
                if (response.ok) {
                    return response.json();
                } else {
                    return response.json().then((error) => {
                        store.dispatch(openAlert({message:error.error,color:'red'}))
                        return error;
                    });
                }
            });
        }
    }
}