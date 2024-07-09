import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus';
import VxeTable from 'vxe-table'
import 'vxe-table/lib/style.css'
const app = createApp(App)
app.use(router)
app.use(VxeTable)
app.use(ElementPlus)
app.mount('#app')