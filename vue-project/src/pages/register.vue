<template>
    <div class="register-container">
      <div class="register-box">
        <h2>注册</h2>
        <form @submit.prevent="handleRegister">
          <div class="form-group">
            <label for="school">学校</label>
            <input v-model="registerForm.school" type="text" id="school" required />
          </div>
          <div class="form-group">
            <label for="class">班级</label>
            <input v-model="registerForm.class" type="text" id="class" required />
          </div>
          <div class="form-group">
            <label for="stuNo">学号</label>
            <input v-model="registerForm.stuNo" type="text" id="stuNo" required />
          </div>
          <div class="form-group">
            <label for="name">姓名</label>
            <input v-model="registerForm.name" type="text" id="name" required />
          </div>
          <div class="form-group">
            <label for="gender">性别</label>
            <select v-model="registerForm.gender" id="gender" required>
              <option value="" disabled>请选择性别</option>
              <option value="1">男</option>
              <option value="0">女</option>
            </select>
          </div>
          <button type="submit">注册</button>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  import { ref } from 'vue';
  import axios from 'axios';
  import { useRouter } from 'vue-router';
  
  export default {
    name: 'RegisterComponent',
    setup() {
      const registerForm = ref({
        school: '',
        class: '',
        stuNo: '',
        name: '',
        gender: '',
      });
      const router = useRouter();
  
      const handleRegister = () => {
        if (registerForm.value.password !== registerForm.value.confirmPassword) {
          alert('密码和确认密码不匹配');
          return;
        }
        axios.post('/api/stus', {
            school: registerForm.value.school,
            className: registerForm.value.class,
            stuNo: registerForm.value.stuNo,
            stuName: registerForm.value.name,
            gender: registerForm.value.gender,
        })
          .then(response => {
            console.log(response.data.code + "??")
            if (response.data.code === 1) {
              alert('注册成功');
              router.push('/login');
            } else {
              alert(response.data.msg || '注册失败');
            }
          })
          .catch(error => {
            alert(error.response.data.msg || '请求失败');
          });
      };
  
      return {
        registerForm,
        handleRegister
      };
    }
  };
  </script>
  
  <style scoped>
  .register-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f0f2f5;
  }
  
  .register-box {
    background: white;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    width: 300px;
    text-align: center;
  }
  
  .register-box h2 {
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
  
  .form-group input, .form-group select {
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
  