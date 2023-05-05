

export default {
    api(method, url, body=null) {
        if(method === "GET" || method === "DELETE" ) {
            return fetch('http://localhost:8090'+url, {
                method: method,
                headers: {
                    'accept': "application/json",
                    'Content-Type': "application/json"
                }
            })
        } else if(method === "POST" || method === "PUT" )
            return fetch('http://localhost:8090'+url, {
                method: method,
                headers: {
                    'accept': "application/json",
                    'Content-Type': "application/json"
                },
                body: JSON.stringify(body)
            })
    }
}