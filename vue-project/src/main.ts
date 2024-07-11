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
const app = createApp(App)

app.component('v-chart', ECharts)
app.use(router)
app.use(VxeUI)
app.use(VxeTable)
app.use(ElementPlus)
app.mount('#app')