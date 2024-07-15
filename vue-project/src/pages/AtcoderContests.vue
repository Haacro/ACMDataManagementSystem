<template>
  <div class="container">
    <div class="table-container">
      <h2>比赛列表</h2>
      <el-select v-model="pageSize" placeholder="page number" style="width: 200px;" @change="handlePageSizeChange">
        <el-option label="5" :value="5"></el-option>
        <el-option label="10" :value="10"></el-option>
        <el-option label="20" :value="20"></el-option>
        <el-option label="50" :value="50"></el-option>
      </el-select>
      <el-table :data="tableData" border stripe style="width: 100%; margin-top: 20px;">
        <el-table-column prop="contestId" label="id" width="auto" />
        <el-table-column prop="contestName" label="contest name" width="auto">
          <template #default="scope">
            <a :href="scope.row.link" target="_blank">{{ scope.row.contestName }}</a>
          </template>
        </el-table-column>
        <el-table-column prop="beginTime" label="begin time" width="auto" />
        <el-table-column prop="length" label="length" width="auto" />
        <el-table-column label="Action" width="auto">
          <template #default="scope">
            <el-button link type="primary" size="small" @click="navigateToContest(scope.row.url)">
              Detail
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

export default {
  setup() {
    const tableData = ref([])
    const currentPage = ref(1)
    const pageSize = ref(10)
    const total = ref(0)

    const fetchTableData = async () => {
      try {
        const response = await axios.get('/api/atcoder/contests', {
          params: {
            page: currentPage.value,
            pageSize: pageSize.value
          }
        })
        tableData.value = response.data.data.rows
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

    const navigateToContest = (url) => {
      window.open(url, '_blank')
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
      navigateToContest
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
