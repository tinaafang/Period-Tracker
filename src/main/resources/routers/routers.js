
import { createRouter, createWebHistory } from 'vue-router'
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
const routes = [{
        path: '/',
        name: 'login',
        component: Login

    }, {
        path: '/register',
        name: 'register',
        component: Register

    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router