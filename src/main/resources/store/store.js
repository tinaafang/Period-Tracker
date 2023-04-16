import { createStore } from 'vuex'

const store = createStore({
    state() {
        return {
            count: 0
        }
    },
    mutations: {
    },
    actions: {
        test: () => {
            fetch('http://localhost:8080/api/student', {
                method: 'GET',
                headers: {
                    'accept': "application/json",
                    'Content-Type': "application/json"
                }
                // body: JSON.stringify({
                //     firstName:"a",
                //     lastName:'bao',
                //     email:'tinafang114@gmail.com'
                // })
            })
                .then(response => response.text())
                .then(data => console.log(data))
                .catch(error => console.error(error));
        }

    },
    getters: {
        getCount(state) {
            return state.count
        }
    }
})

export default store