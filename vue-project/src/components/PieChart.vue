<template>
    <div ref="chart" class="chart"></div>
  </template>
  
  <script>
  import * as echarts from 'echarts';
  
  export default {
    name: 'PieChart',
    props: {
      options: {
        type: Object,
        required: true
      }
    },
    mounted() {
      this.chart = echarts.init(this.$refs.chart);
      this.chart.setOption(this.options);
  
      window.addEventListener('resize', this.resizeChart);
    },
    beforeUnmount() {
      window.removeEventListener('resize', this.resizeChart);
      if (this.chart) {
        this.chart.dispose();
      }
    },
    methods: {
      resizeChart() {
        if (this.chart) {
          this.chart.resize();
        }
      }
    }
  }
  </script>
  
  <style scoped>
  .chart {
    width: 100%;
    height: 400px;
  }
  </style>
  