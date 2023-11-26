export default {
    api(method, url, body=null) {
        if (method === "GET" || method === "DELETE") {
            return fetch("http://localhost:3000"+url, {
                method: method,
                headers: {
                    'accept': "application/json",
                    'Content-Type': "application/json",
                    'Authorization': 'Bearer ' + localStorage.getItem('jwtToken')
                }

            }).then((response) =>
            {
                if (response.ok) {
                    return response.json();
                } else {
                    response.json().then((error) => {
                        // store.commit("ui/OPEN_ALERT",{message:error.error,color:'red'},{root:true})
                    });
                }
            });
        } else if (method === "POST" || method === "PUT") {
            return fetch("http://localhost:3000"+url, {
                method: method,
                headers: {
                    'accept': "application/json",
                    'Content-Type': "application/json",
                    'Authentication': 'Bearer ' + localStorage.getItem('jwtToken')
                },
                body: JSON.stringify(body)
            }).then((response) =>
            {
                if (response.ok) {
                    return response.json();
                } else {
                    response.json().then((error) => {
                        // store.commit("ui/OPEN_ALERT",{message:error.error,color:'red'},{root:true})
                    });
                }
            });
        }
    }
}