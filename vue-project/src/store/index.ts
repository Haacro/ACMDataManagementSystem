import { defineStore } from 'pinia'

export const useAuthStore = defineStore('auth', {
  state: () => ({
    token : '',
    stuNo : '',
    isAuthenticated: !!localStorage.getItem('token'), // ͨ�����ش洢��ʼ����¼״̬
    // isAuthenticated: false
  }),
  actions: {
    login(stuNo : string) {
      this.isAuthenticated = true
      // this.token = token;
     
      this.stuNo = stuNo;  
      console.log(this.stuNo + "??")
    },
    logout() {
      this.isAuthenticated = false
      localStorage.removeItem('token')
      // this.token = '';
      this.stuNo = '';
    }
  }
})
