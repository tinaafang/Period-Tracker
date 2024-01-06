import {openAlert} from "./store/uiSlice";
import store from "./store/store";

const headersWithAuth = {
    'accept': "application/json",
    'Content-Type': "application/json",
    'Authorization': 'Bearer ' + localStorage.getItem('jwt')
}

const headers = {
    'accept': "application/json",
    'Content-Type': "application/json"
}

export default {
    api(method, url, body=null) {
        if (method === "GET" || method === "DELETE") {
            return fetch("http://localhost:3000"+url, {
                method: method,
                headers: url.includes("auth") ? headers : headersWithAuth
            }).then((response) =>
            {
                if (response.ok) {
                    return response.json().catch(() => {
                        return response;
                    });
                } else {
                    return response.json().then((error) => {
                        store.dispatch(openAlert({message:error.error,color:'red'}))
                        return error;
                    });
                }
            });
        } else if (method === "POST" || method === "PUT") {
            return fetch("http://localhost:3000"+url, {
                method: method,
                headers: url.includes("auth") ? headers : headersWithAuth,
                body: JSON.stringify(body)
            }).then((response) =>
            {
                if (response.ok) {
                        return response.json().catch(() => {
                        return response;
                    });
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