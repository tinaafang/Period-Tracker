

export default {
    api(method, url, body=null) {
        if(method === "GET" || method === "DELETE" ) {
            return fetch('http://localhost:8090'+url, {
                method: method,
                headers: {
                        'accept': "application/json",
                        'Content-Type': "application/json",
                        'Authorization': 'Bearer '+localStorage.getItem('jwtToken')
                    }

            })
        } else if(method === "POST" || method === "PUT" )
            return fetch('http://localhost:8090'+url, {
                method: method,
                headers: {
                    'accept': "application/json",
                    'Content-Type': "application/json",
                    'Authentication': 'Bearer '+localStorage.getItem('jwtToken')

                },
                body: JSON.stringify(body)
            })
    }
}