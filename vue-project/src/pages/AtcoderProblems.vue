<template>
  <div class="container">
    <div class="table-container">
      <h2>Problem List</h2>
      <div class="control-container">
        <el-select v-model="pageSize" placeholder="每页数量" style="width: 200px;" @change="handlePageSizeChange">
          <el-option label="5" :value="5"></el-option>
          <el-option label="10" :value="10"></el-option>
          <el-option label="20" :value="20"></el-option>
          <el-option label="50" :value="50"></el-option>
        </el-select>
        <div class="search-container">
          <el-input v-model="nameQuery" placeholder="请输入题目名称" class="search-input" clearable />
          <el-button type="primary" @click="handleSearch">搜索</el-button>
        </div>
      </div>
      <el-table :data="tableData" border stripe style="width: 100%; margin-top: 20px;">
        <el-table-column prop="problemId" label="ID" width="auto" />
        <el-table-column prop="problemName" label="Problem Name" width="auto" />
        <el-table-column prop="problemRate" label="Problem Rate" width="auto" />
        <el-table-column label="Action" width="auto">
          <template #default="scope">
            <el-button link type="primary" size="small" @click="navigateToProblem(scope.row.url)">
              Detail
            </el-button>
            <el-button type="success" size="small" @click="handleSubmit(scope.row)">
              提交
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination-container">
        <el-pagination background layout="prev, pager, next, jumper, ->, total" :total="total" :page-size="pageSize"
          :current-page="currentPage" @current-change="handleCurrentChange" @size-change="handlePageSizeChange" />
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted, watch } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
// import { useAuthStore } from '@/store';
export default {
  setup() {
    const authStore = useAuthStore();
    const tableData = ref([])
    const currentPage = ref(1)
    const pageSize = ref(10)
    const total = ref(0)
    const nameQuery = ref('')
    const tagQuery = ref('')
    const stuNo = authStore.stuNo;
    const fetchTableData = async () => {
      try {
        const response = await axios.get('/api/atcoder/problems', {
          params: {
            page: currentPage.value,
            pageSize: pageSize.value,
            problemName: nameQuery.value,
            tag: tagQuery.value
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

    const handleSearch = () => {
      currentPage.value = 1 // reset to the first page on new search
      fetchTableData()
    }

    const navigateToProblem = (url) => {
      window.open(url, '_blank')
    }

    const handleSubmit = async (row) => {
      try {
        const response = await axios.put('/api/codeforces/problems', {

          stuNo: stuNo.value,
          // score: row.problemRate // 假设使用 problemRate 作为分数
        })
        ElMessage.success('提交成功')
        row.submitted = true
      } catch (error) {
        console.error('Error submitting data:', error)
        ElMessage.error('提交失败')
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
      nameQuery,
      tagQuery,
      handleCurrentChange,
      handlePageSizeChange,
      handleSearch,
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

.control-container {
  display: flex;
  align-items: center;
  /* justify-content: space-between; */
  margin-bottom: 20px;
}

.search-container {
  display: flex;
}

.search-input {
  width: 200px;
  margin-left: 10px;
}

.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}
</style>
