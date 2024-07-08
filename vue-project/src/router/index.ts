import { createRouter, createWebHistory } from "vue-router";
import mainPage from "@/pages/mainPage.vue";
import contestPage from "@/pages/contestPage.vue";
import profilePage from "@/pages/profilePage.vue";
import trainPage from "@/pages/trainPage.vue";

const router = createRouter({
  history: createWebHistory(),
  routes:[
    {
      path: '/main',
      component: mainPage
    },
    {
      path: '/contest',
      component: contestPage
    },
    {
      path: '/profile',
      component: profilePage
    },
    {
      path: '/train',
      component: trainPage
    }
  ]
})

export default router