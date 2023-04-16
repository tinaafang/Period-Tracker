
import { createRouter, createWebHistory } from 'vue-router'
import Menu from "../views/Menu.vue";
import Cart from "../views/Cart.vue";

const routes = [
    {
        path: '/',
        name: 'menu',
        component: Menu
    },
    {
        path: '/about',
        name: 'cart',
        component: Cart
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router