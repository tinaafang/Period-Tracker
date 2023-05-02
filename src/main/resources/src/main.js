
import { createApp } from 'vue'
import App from '../App.vue'
import store from "../store/store";
import router from "../routers/routers";
import "bootstrap/dist/css/bootstrap.min.css"
import 'bootstrap'
import "../dist/css/app.78735ac5.css"


const app = createApp(App)
app.use(store)
app.use(router)
app.mount('#app')