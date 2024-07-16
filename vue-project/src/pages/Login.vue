<template>
  <div class="login-container">
    <div class="login-box">
      <h2>登录</h2>
      <form @submit.prevent="handleLogin">
        <div class="form-group">
          <label for="stuNo">用户名</label>
          <input v-model="loginForm.stuNo" type="text" id="stuNo" required />
        </div>
        <div class="form-group">
          <label for="password">密码</label>
          <input v-model="loginForm.password" type="password" id="password" required />
        </div>
        <button type="submit">登录</button>
      </form>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/store'; // 确保路径正确

export default {
  name: 'LoginComponent',
  setup() {
    const loginForm = ref({
      stuNo: '',
      password: ''
    });
    const router = useRouter();
    const authStore = useAuthStore(); // 使用 Pinia store

    const handleLogin = () => {
      axios.post('/api/login', loginForm.value)
        .then(response => {
          if (response.data.code === 1) {
            alert('登录成功');
            localStorage.setItem('token', response.data.data);
            authStore.login(); // 更新登录状态
            router.push({ path: '/' });
          } else {
            alert(response.data.msg || '登录失败');
          }
        })
        .catch(error => {
          alert(error.response.data.msg || '请求失败');
        });
    };

    return {
      loginForm,
      handleLogin
    };
  }
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5;
}

.login-box {
  background: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 300px;
  text-align: center;
}

.login-box h2 {
  margin-bottom: 20px;
  color: #333;
}

.form-group {
  margin-bottom: 15px;
  text-align: left;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  color: #555;
}

.form-group input {
  width: 100%;
  padding: 10px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #42b983;
  border: none;
  border-radius: 5px;
  color: white;
  font-size: 16px;
  cursor: pointer;
}

button:hover {
  background-color: #359d74;
}
</style>
