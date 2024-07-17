<template>
  <div>
    <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
    >
      <el-menu-item index="1" @click="navigate('/')">首页</el-menu-item>
      <el-menu-item index="2" @click="navigate('/user-list')">用户列表</el-menu-item>
      <el-sub-menu index="3">
        <template #title>codeforces</template>
        <el-menu-item index="3-1" @click="navigate('/codeforces-contests')">比赛列表</el-menu-item>
        <el-menu-item index="3-2" @click="navigate('/codeforces-problems')">题目列表</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="4">
        <template #title>atcoder</template>
        <el-menu-item index="4-1" @click="navigate('/atcoder-contests')">比赛列表</el-menu-item>
        <el-menu-item index="4-2" @click="navigate('/atcoder-problems')">题目列表</el-menu-item>
      </el-sub-menu>
      <el-menu-item index="5" @click="navigate('/smart-training')">智能训练</el-menu-item>
      <el-menu-item v-if="!authStore.isAuthenticated" index="6-1" @click="navigate('/login')">登录</el-menu-item>
      <el-menu-item v-if="!authStore.isAuthenticated" index="6-2" @click="navigate('/register')">注册</el-menu-item>
      <el-sub-menu v-if="authStore.isAuthenticated" index="7">
        <template #title>登陆状态</template>
        <el-menu-item index="7-1" @click="navigate('/profile')">个人</el-menu-item>
        <el-menu-item index="7-2" @click="logout">退出登录</el-menu-item>
      </el-sub-menu>
    </el-menu>
    <router-view></router-view>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';
import { useAuthStore } from './store';
import { ref } from 'vue';

const activeIndex = ref('1');
const router = useRouter();
const authStore = ref(useAuthStore()); // 使用 Pinia 的 store

const handleSelect = (key: string) => {
  console.log('Selected key:', key);
};

const navigate = (path: string) => {
  router.push(path);
};

const logout = () => {
  authStore.value.logout();
  // 如果需要刷新页面来反映未登录状态，可以调用以下方法
  // location.reload();
};
</script>

<style scoped>
.el-menu-demo {
  line-height: 60px;
}

a {
  color: inherit;
  text-decoration: none;
}
</style>
