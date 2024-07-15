import { createRouter, createWebHistory } from 'vue-router'
import mainPage from '@/pages/mainPage.vue'
import userList from '@/pages/userList.vue'
import CodeforcesContests from '@/pages/CodeforcesContests.vue'
import CodeforcesProblems from '@/pages/CodeforcesProblems.vue'
import AtcoderContests from '@/pages/AtcoderContests.vue'
import AtcoderProblems from '@/pages/AtcoderProblems.vue'
import trainPage from '@/pages/trainPage.vue'
import Login from '@/pages/Login.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

const routes = [
  { path: '/', component: mainPage },
  { path: '/user-list', component: userList },
  { path: '/codeforces-contests', component: CodeforcesContests },
  { path: '/codeforces-problems', component: CodeforcesProblems },
  { path: '/atcoder-contests', component: AtcoderContests },
  { path: '/atcoder-problems', component: AtcoderProblems },
  { path: '/smart-training', component: trainPage },
  { path: '/login', component: Login },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
