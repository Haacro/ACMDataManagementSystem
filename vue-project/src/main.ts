import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus';
import VxeTable from 'vxe-table'
import 'vxe-table/lib/style.css'
import VxeUI from 'vxe-pc-ui'
import 'vxe-pc-ui/lib/style.css'
import ECharts from 'vue-echarts'
import 'echarts'
import 'element-plus/dist/index.css'
import { createPinia } from 'pinia';
const app = createApp(App)
const pinia = createPinia();
app.use(pinia)
app.component('v-chart', ECharts)
app.use(router)
app.use(VxeUI)
app.use(VxeTable)
app.use(ElementPlus)
app.mount('#app')