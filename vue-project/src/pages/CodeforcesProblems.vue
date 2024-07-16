<template>
  <div class="container">
    <div class="table-container">
      <h2>Problem List</h2>
      <el-select v-model="pageSize" placeholder="page number" style="width: 200px;" @change="handlePageSizeChange">
        <el-option label="5" :value="5"></el-option>
        <el-option label="10" :value="10"></el-option>
        <el-option label="20" :value="20"></el-option>
        <el-option label="50" :value="50"></el-option>
      </el-select>
      <el-table :data="tableData" border stripe style="width: 100%; margin-top: 20px;">
        <el-table-column prop="problemId" label="ID" width="auto" />
        <el-table-column prop="problemName" label="Problem Name" width="auto" />
        <el-table-column prop="problemRate" label="Problem Rate" width="auto" />
        <el-table-column prop="tag" label="标签" width="auto" />
        <el-table-column label="Action" width="auto">
          <template #default="scope">
            <el-button link type="primary" size="small" @click="navigateToProblem(scope.row.url)">
              Detail
            </el-button>
            <el-button
              :type="scope.row.submitted ? 'info' : 'success'"
              size="small"
              @click="handleSubmit(scope.row)"
            >
              {{ scope.row.submitted ? '已完成' : '未完成' }}
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination-container">
        <el-pagination
          background
          layout="prev, pager, next, jumper, ->, total"
          :total="total"
          :page-size="pageSize"
          :current-page="currentPage"
          @current-change="handleCurrentChange"
          @size-change="handlePageSizeChange"
        />
      </div>
    </div>
  </div>
</template>
<script>
import { ref, onMounted, watch } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

export default {
  setup() {
    const tableData = ref([])
    const currentPage = ref(1)
    const pageSize = ref(10)
    const total = ref(0)
    const router = useRouter()

    const fetchTableData = async () => {
      try {
        const response = await axios.get('/api/codeforces/problems', {
          params: {
            page: currentPage.value,
            pageSize: pageSize.value
          }
        })
        tableData.value = response.data.data.rows.map(row => ({
          ...row,
          submitted: false
        }))
        total.value = response.data.data.total
      } catch (error) {
        console.error('Error fetching data:', error)
      }
    }

    const handleCurrentChange = (page) => {
      currentPage.value = page
    }

    const handlePageSizeChange = (size) => {
      pageSize.value = size
      currentPage.value = 1 // reset to the first page whenever page size changes
    }

    const navigateToProblem = (url) => {
      window.open(url, '_blank')
    }

    const handleSubmit = async (row) => {
      try {
        if (!row.submitted) {
          // 提交数据到后端
          const response = await axios.post('/api/submit', {
            problemId: row.problemId,
            score: row.problemRate // 假设使用 problemRate 作为分数
          })
      
            row.submitted = true
          
        } else {
          // 取消提交数据到后端
          const response = await axios.post('/api/cancelSubmit', {
            problemId: row.problemId
          })
        
            row.submitted = false
          
        }
      } catch (error) {
        console.error('Error submitting or cancelling data:', error)
      }
    }

    watch([currentPage, pageSize], fetchTableData)

    onMounted(() => {
      fetchTableData()
    })

    return {
      tableData,
      currentPage,
      pageSize,
      total,
      handleCurrentChange,
      handlePageSizeChange,
      navigateToProblem,
      handleSubmit
    }
  }
}
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
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
  max-width: 1200px;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
  color: #333;
}

.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}
</style>