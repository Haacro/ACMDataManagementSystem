import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/views/Main.vue'
import Profile from '@/views/Profile.vue'
import Train from '@/views/Train.vue'
import Contest from '@/views/Contest.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'main',
      component: Main
    },
    {
      path: '/profile',
      name: 'profile',
      component: Profile
    },
    {
      path: '/contest',
      name: 'contest',
      component: Contest
    },
    {
      path: '/train',
      name: 'train',
      component: Train
    }
  ]
})
