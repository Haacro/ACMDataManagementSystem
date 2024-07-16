import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '@/store'
import mainPage from '@/pages/mainPage.vue'
import userList from '@/pages/userList.vue'
import CodeforcesContests from '@/pages/CodeforcesContests.vue'
import CodeforcesProblems from '@/pages/CodeforcesProblems.vue'
import AtcoderContests from '@/pages/AtcoderContests.vue'
import AtcoderProblems from '@/pages/AtcoderProblems.vue'
import trainPage from '@/pages/trainPage.vue'
import Login from '@/pages/Login.vue'
import profilePage from '@/pages/profilePage.vue'



const routes = [
  { path: '/', component: mainPage },
  { path: '/user-list', component: userList },
  { path: '/codeforces-contests', component: CodeforcesContests },
  { path: '/codeforces-problems', component: CodeforcesProblems },
  { path: '/atcoder-contests', component: AtcoderContests },
  { path: '/atcoder-problems', component: AtcoderProblems },
  { path: '/smart-training', component: trainPage, meta: { requiresAuth: true } },
  { path: '/login', component: Login },
  { path: '/profile', component: profilePage }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

// È«¾Öµ¼º½ÊØÎÀ
router.beforeEach((to, from, next) => {
  const authStore = useAuthStore()

  if (to.meta.requiresAuth && !authStore.isAuthenticated) {
    next({ path: '/login' })
  } else {
    next()
  }
})

export default router

