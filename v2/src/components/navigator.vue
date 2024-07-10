<template>
  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
    <el-menu-item index="title" disabled class="menu-title">
      ACDC
    </el-menu-item>
    <div class="flex-space"></div>
    <el-menu-item index="main" @click="navigateTo('main')">Main</el-menu-item>
    <el-menu-item index="profile" @click="navigateTo('profile')">Profile</el-menu-item>
    <el-menu-item index="contest" @click="navigateTo('contest')">Contest</el-menu-item>
    <el-menu-item index="train" @click="navigateTo('train')">Train</el-menu-item>
    
  </el-menu>
</template>

<script>
export default {
  name: 'Navbar',
  data() {
    return {
      activeIndex: 'main'
    }
  },
  methods: {
    navigateTo(route) {
      this.activeIndex = route
      this.$router.push({ name: route })
    }
  }
}
</script>

<style scoped>
.el-menu-demo {
  background-color: #545c64;
  color: #fff;
  display: flex;
  
  /* justify-content: space-between; */
}

.el-menu-item {
  margin: 0;
}

.menu-title {
  margin-left: auto;
  padding-left: 20px;
  font-size: 18px;
  font-weight: bold;
  color: #fff;
}
.flex-space {
  flex-grow: 1;
}
</style>
