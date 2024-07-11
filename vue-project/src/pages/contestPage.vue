<template>
  <div class="container">
    <div class="table-container">
      <h2>比赛列表</h2>
      <el-table :data="tableData" border >
        <el-table-column prop="id" label="id" width=230></el-table-column>
        <el-table-column prop="name" label="比赛名" width=230>
          <template #default="scope">
            <a :href="scope.row.link" target="_blank">{{ scope.row.name }}</a>
          </template>
        </el-table-column>
        <el-table-column prop="type" label="比赛类型" width=230></el-table-column>
        <el-table-column prop="startTime" label="开始时间" width=230></el-table-column>
        <el-table-column prop="duration" label="持续时间" width=230></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import axios from 'axios'

export default {
  setup() {
    const tableData = ref([])

    const fetchTableData = async () => {
      try {
        const response = await axios.get('http://172.20.10.2:8096/browse')
        tableData.value = response.data
      } catch (error) {
        console.error('Error fetching data:', error)
      }
    }

    onMounted(() => {
      fetchTableData()
    })

    return {
      tableData
    }
  }
}
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  height: 100vh;
  background-color: #f5f5f5;
  padding: 20px;
  box-sizing: border-box;
}

.table-container {
  background: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 1200px; /* Optionally set a maximum width */
}

h2 {
  text-align: center;
  margin-bottom: 20px;
  color: #333;
}
</style>
