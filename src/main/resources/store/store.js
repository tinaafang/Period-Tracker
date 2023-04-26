import { createStore } from 'vuex'


const store = createStore({
    state() {
        return {
            activeModal: {}
        };
    },
    mutations: {
        OPEN_MODAL(state,why) {
            state.activeModal = why;
        },
        CLOSE_MODAL(state) {
            state.activeModal = {};
        }
    },
    actions: {
        test: () => {
            fetch('http://localhost:8090/api/registration/register', {
                method: 'POST',
                headers: {
                    'accept': "application/json",
                    'Content-Type': "application/json"
                },
                body: JSON.stringify({
                    password:'cptbtptp',
                    email:'tinafang114@gmail.com'
                })
            })
                .then(response => response.text())
                .then(data => console.log(data))
                .catch(error => console.error(error));
        }

    },
    getters: {
        getActiveModal: (state) => {return state.activeModal}
    }
})

export default store