
import { createRouter, createWebHistory } from 'vue-router'
import App from "../App.vue";
import CoverLetterTemplate from "../views/CoverLetterTemplate.vue";

const routes = [
    {
        path: '/',
        name: 'home',
        component: App
    },
    {
        path: '/template/:templateId',
        name: 'template-detail',
        component: CoverLetterTemplate}
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router