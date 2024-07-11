<template>
  <div class="container">
    <div class="main-content">
      <div class="controls">
        <el-select v-model="pageSize" placeholder="每页显示条数" style="width: 200px;">
          <el-option label="5条/页" :value="5"></el-option>
          <el-option label="10条/页" :value="10"></el-option>
          <el-option label="20条/页" :value="20"></el-option>
          <el-option label="50条/页" :value="50"></el-option>
        </el-select>
      </div>
      <el-table :data="pagedData" border stripe style="width: 100%; margin-top: 20px;">
        <el-table-column prop="stuNo" label="学号" width="80" />
        <el-table-column prop="name" label="姓名" width="180" />
        <el-table-column prop="class" label="班级" width="180" />
        <el-table-column prop="sex" label="性别" width="100" />
        <el-table-column prop="school" label="学校" />
      </el-table>
      <div class="pagination-container">
        <el-pagination
          background
          layout="prev, pager, next, jumper, ->, total"
          :total="total"
          :page-size="pageSize"
          :current-page="currentPage"
          @current-change="handleCurrentChange"
        />
      </div>
    </div>
    <div class="leaderboard">
      <h3>排行榜</h3>
      <el-table :data="rankedData" border stripe style="width: 100%">
        <el-table-column prop="name" label="姓名" width="180" />
        <el-table-column prop="points" label="积分" width="100" />
      </el-table>
    </div>
  </div>
</template>

<script>
import { ref, computed, onMounted } from 'vue'
import axios from 'axios'

export default {
  setup() {
    const tableData = ref([])
    const currentPage = ref(1)
    const pageSize = ref(5)
    const error = ref(null)

    const total = computed(() => tableData.value.length)

    const pagedData = computed(() => {
      const start = (currentPage.value - 1) * pageSize.value
      const end = start + pageSize.value
      return tableData.value.slice(start, end)
    })

    const rankedData = computed(() => {
      return [...tableData.value].sort((a, b) => b.points - a.points).slice(0, 10)
    })

    const handleCurrentChange = (val) => {
      currentPage.value = val
    }

    const fetchTableData = async () => {
      try {
        const response = await axios.get('/api')
        tableData.value = response.data
        console.log(response.data)
      } catch (err) {
        error.value = 'Error fetching data: ' + (err.response ? err.response.data : err.message)
        console.error('Error fetching data:', err)
      }
    }

    onMounted(() => {
      fetchTableData()
    })

    return {
      tableData,
      currentPage,
      pageSize,
      total,
      pagedData,
      rankedData,
      handleCurrentChange,
      fetchTableData,
      error
    }
  }
}
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: row;
  gap: 20px;
  padding: 20px;
  background-color: #f5f5f5;
}
.main-content {
  flex: 3;
  background: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}
.controls {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.leaderboard {
  flex: 1;
  background: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}
.pagination-container {
  text-align: right;
  margin-top: 20px;
}
h3 {
  margin-bottom: 20px;
}
</style>
