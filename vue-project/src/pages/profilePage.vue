<template>
  <div class="profile">
    <h1>个人数据概览</h1>
    <div class="summary">
      <div class="summary-item">
        <h2>本周做题情况</h2>
        <bar-chart :options="barChartOptionsThisWeek"></bar-chart>
      </div>
      <div class="summary-item">
        <h2>上周做题情况</h2>
        <bar-chart :options="barChartOptionsLastWeek"></bar-chart>
      </div>
      <div class="summary-item stats">
        <p>本周做题总数：<strong>{{ totalThisWeek }}</strong></p>
        <p>比上周<strong>{{ comparisonText }}</strong>了<strong>{{ diff }}</strong>题。</p>
      </div>
    </div>
    <div class="charts">
      <div class="chart-item">
        <h2>分数变化情况</h2>
        <line-chart :options="lineChartOptions"></line-chart>
      </div>
      <div class="chart-item">
        <h2>做题类型分布</h2>
        <pie-chart :options="pieChartOptions"></pie-chart>
      </div>
    </div>
  </div>
</template>

<script>
import BarChart from '@/components/BarChart.vue'
import LineChart from '@/components/LineChart.vue'
import PieChart from '@/components/PieChart.vue'

export default {
  name: 'Profile',
  components: {
    BarChart,
    LineChart,
    PieChart
  },
  data() {
    return {
      totalThisWeek: 70,
      totalLastWeek: 50,
      barChartOptionsThisWeek: {
        xAxis: {
          type: 'category',
          data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [10, 15, 20, 10, 5, 5, 5],
            type: 'bar'
          }
        ]
      },
      barChartOptionsLastWeek: {
        xAxis: {
          type: 'category',
          data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [5, 10, 15, 10, 5, 5, 0],
            type: 'bar'
          }
        ]
      },
      lineChartOptions: {
        xAxis: {
          type: 'category',
          data: ['第一次', '第二次', '第三次', '第四次', '第五次']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [80, 85, 90, 75, 95],
            type: 'line'
          }
        ]
      },
      pieChartOptions: {
        series: [
          {
            name: '做题类型',
            type: 'pie',
            radius: '50%',
            data: [
              { value: 40, name: '选择题' },
              { value: 30, name: '填空题' },
              { value: 20, name: '判断题' },
              { value: 10, name: '简答题' }
            ]
          }
        ]
      }
    }
  },
  computed: {
    diff() {
      return this.totalThisWeek - this.totalLastWeek;
    },
    comparisonText() {
      return this.diff > 0 ? '增加' : '减少';
    }
  }
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
