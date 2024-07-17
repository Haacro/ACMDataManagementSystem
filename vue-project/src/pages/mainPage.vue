<template>
  <div class="container">
    <div class="main-content">
      <h3>学生提交次数统计</h3>
      <div ref="chart" :style="{ width: '100%', height: '500px' }"></div>
    </div>
    <div class="leaderboard">
      <h3>排行榜</h3>
      <el-table :data="tableData" border stripe style="width: 100%">
        <el-table-column prop="stuName" label="姓名" width="auto" />
        <el-table-column prop="stuScore" label="积分" width="auto" />
      </el-table>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import axios from 'axios'
import * as echarts from 'echarts'

const chart = ref(null)
let chartInstance = null

const chartOptions = {
  grid: {
    top: '10%',    // 调整上边距
    bottom: '10%', // 调整下边距
    left: '5%',    // 调整左边距
    right: '5%'    // 调整右边距
  },
  xAxis: {
    type: 'category',
    data: [], // 动态生成
  },
  yAxis: {
    type: 'value',
  },
  series: [
    {
      data: [],
      type: 'bar',
      barWidth: '50%', // 调整柱子的宽度
    },
  ],
}

const tableData = ref([])
const error = ref(null)

const fetchChartData = async () => {
  try {
    const response = await axios.get('/api/sub')
    if (response.data && response.data.code === 1 && Array.isArray(response.data.data)) {
      return response.data.data
    } else {
      console.error('Unexpected response structure:', response.data)
      return []
    }
  } catch (err) {
    error.value = 'Error fetching data: ' + (err.response ? err.response.data : err.message)
    console.error('Error fetching data:', err)
    return []
  }
}

const fetchTableData = async () => {
  try {
    const response = await axios.get('/api/stus')
    if (response.data && response.data.data && Array.isArray(response.data.data.rows)) {
      tableData.value = response.data.data.rows
    } else {
      console.error('Unexpected response structure:', response.data)
    }
  } catch (err) {
    error.value = 'Error fetching data: ' + (err.response ? err.response.data : err.message)
    console.error('Error fetching data:', err)
  }
}

onMounted(async () => {
  const chartData = await fetchChartData()

  const studentNames = chartData.map(item => item.stuName)
  const submissionCounts = chartData.map(item => item.subNum)

  chartOptions.xAxis.data = studentNames
  chartOptions.series[0].data = submissionCounts

  chartInstance = echarts.init(chart.value)
  chartInstance.setOption(chartOptions)

  fetchTableData()
})

</script>

<style scoped>
.container {
  display: flex;
  align-items: stretch; /* 顶部和底部对齐 */
  gap: 20px;
  padding: 20px;
  background-color: #f5f5f5;
}
.main-content, .leaderboard {
  display: flex;
  flex-direction: column;
  justify-content: space-between; /* 确保内容在垂直方向上均匀分布 */
  background: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}
.main-content {
  flex: 3;
}
.leaderboard {
  flex: 1;
}
h3 {
  margin-bottom: 20px;
}
</style>
