
import { createRouter, createWebHistory } from 'vue-router'
import RecipeList from "../views/RecipeList.vue";
import RecipeDetail from "../views/RecipeDetail.vue";
const routes = [
    {
        path: '/',
        name: 'recipe-list',
        component: RecipeList
    }, {
        path: '/:recipeId',
        name: 'recipe-detail',
        component: RecipeDetail

    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router