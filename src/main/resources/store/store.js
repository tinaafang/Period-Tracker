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
            fetch('http://localhost:8090', {
                method: 'GET',
                headers: {
                    'accept': "application/json",
                    'Content-Type': "application/json"
                }
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