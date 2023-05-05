
import { createRouter, createWebHistory } from 'vue-router'
import RecipeList from "../views/RecipeList.vue";
import RecipeDetail from "../views/RecipeDetail.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
const routes = [
    {
        path: '/recipes',
        name: 'recipe-list',
        component: RecipeList
    }, {
        path: '/:recipeId',
        name: 'recipe-detail',
        component: RecipeDetail

    }, {
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