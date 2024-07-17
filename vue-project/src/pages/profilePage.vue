<template>
  <div class="profile">
    <h1>分数变化</h1>
    <div class="summary">
      <div class="summary-item">
        <div ref="chart" :style="{ width: '100%', height: '400px' }"></div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { useAuthStore } from '@/store';
import { ref, onMounted } from 'vue';
import * as echarts from 'echarts';

export default {
  name: 'Profile',
  setup() {
    const authStore = useAuthStore();
    const stuNo = authStore.stuNo;
    const chart = ref(null);
    let chartInstance = null;

    const lineChartOptions = {
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
          type: 'line',
        },
      ],
    };

    const fetchData = async () => {
      try {
        const url = '/api/score/' + stuNo;
        const responseLineChart = await axios.get(url);
        const scores = responseLineChart.data.data;
        const days = generateLabels(scores.length);

        lineChartOptions.series[0].data = scores;
        lineChartOptions.xAxis.data = days;

        if (chartInstance) {
          chartInstance.setOption(lineChartOptions);
        }
      } catch (error) {
        console.error('请求数据失败:', error);
      }
    };

    onMounted(() => {
      chartInstance = echarts.init(chart.value);
      chartInstance.setOption(lineChartOptions);
      fetchData();
    });

    return {
      chart,
      lineChartOptions,
    };
  },
};

// 生成对应数量的标签
function generateLabels(length) {
  const days = [];
  const date = new Date();

  for (let i = 0; i < length; i++) {
    days.unshift(`${date.getMonth() + 1}/${date.getDate()}`);
    date.setDate(date.getDate() - 1);
  }

  return days;
}
</script>

<style scoped>
.profile {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  font-family: Arial, sans-serif;
  color: #333;
}

h1 {
  text-align: center;
  margin-bottom: 40px;
  font-size: 2.5em;
  color: #1f78b4;
}

.summary {
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin-bottom: 40px;
}

.summary-item {
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 20px;
}

.summary-item.stats {
  text-align: center;
  font-size: 1.2em;
}

.summary-item h2 {
  font-size: 1.5em;
  margin-bottom: 20px;
  color: #1f78b4;
}

.charts {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.chart-item {
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 20px;
}

.chart-item h2 {
  font-size: 1.5em;
  margin-bottom: 20px;
  color: #1f78b4;
}

.chart {
  width: 100%;
  height: 400px;
}
</style>
