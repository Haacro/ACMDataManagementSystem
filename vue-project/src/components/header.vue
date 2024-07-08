<template>
    <div class="header">
        <!-- 折叠按钮 -->
        <div class="header-left">
            <img class="logo" src="../assets/img/logo.jpg" alt="" />
            <div class="web-title">ACDC</div>
        </div>
        <div class="header-right">
            <div class = "navigate">
                <RouterLink to="/main" active-class="active">首页</RouterLink>
                <RouterLink to="/contest" active-class="active">比赛</RouterLink>
                <RouterLink to="/profile" active-class="active">个人</RouterLink>
                <RouterLink to="/train" active-class="active">训练</RouterLink>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';

// const username: string | null = localStorage.getItem('vuems_name');


onMounted(() => {
});

// 用户名下拉菜单选择事件
const router = useRouter();

</script>
<style scoped>
.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    box-sizing: border-box;
    width: 100%;
    height: 70px;
    color: var(--header-text-color);
    background-color: var(--header-bg-color);
    border-bottom: 1px solid #ddd;
}

.header-left {
    display: flex;
    align-items: center;
    padding-left: 20px;
    height: 100%;
}

.logo {
    width: 35px;
}

.header-right {
    float: right;
    padding-right: 50px;
}

.navigate {
    display: flex;
    gap: 20px;
}

.navigate > * {
    text-decoration: none;
    color: var(--header-text-color);
    font-weight: bold;
}

.navigate .active {
    color: var(--active-link-color);
}

</style>