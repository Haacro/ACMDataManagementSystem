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
        <el-table-column prop="stuName" label="姓名" width="180" />
        <el-table-column prop="className" label="班级" width="180" />
        <el-table-column prop="gender" label="性别" width="100" />
        <el-table-column prop="school" label="学校" />
        <el-table-column prop="stuScore" label="积分" />
        
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
      <el-table :data="tableData" border stripe style="width: 100%">
        <el-table-column prop="stuName" label="姓名" width="auto" />
        <el-table-column prop="stuScore" label="积分" width="auto" />
      </el-table>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import axios from 'axios'

const tableData = ref([])
const currentPage = ref(1)
const pageSize = ref(5)
const error = ref(null)

const total = ref(0)

const pagedData = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value
  const end = start + pageSize.value
  return tableData.value.slice(start, end)
})



const handleCurrentChange = (val) => {
  currentPage.value = val
}

const fetchTableData = async () => {
  try {
    const response = await axios.get('/api/stus')
    if (response.data && response.data.data && Array.isArray(response.data.data.rows)) {
      tableData.value = response.data.data.rows
      total.value = response.data.data.total
    } else {
      console.error('Unexpected response structure:', response.data)
    }
  } catch (err) {
    error.value = 'Error fetching data: ' + (err.response ? err.response.data : err.message)
    console.error('Error fetching data:', err)
  }
}

onMounted(() => {
  fetchTableData()
})

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