import { createStore } from 'vuex'
import ui from "./Modules/ui";
import authentication from "./Modules/authentication";



const store = createStore({
        modules: {
            ui,
            authentication
        }
})

export default store