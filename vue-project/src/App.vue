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
      <el-menu-item index="6">
        <a href="/login" target="_blank">登录</a>
      </el-menu-item>
    </el-menu>
    <router-view></router-view>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const activeIndex = ref('1')
const router = useRouter()

const handleSelect = (key: string) => {
  console.log('Selected key:', key)
}

const navigate = (path: string) => {
  router.push(path)
}
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
