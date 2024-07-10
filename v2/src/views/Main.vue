<template>
  <div class="container">
    <el-table :data="pagedData" border style="width: 100%">
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="name" label="姓名" width="180" />
      <el-table-column prop="role" label="角色" width="180" />
      <el-table-column prop="sex" label="性别" width="100" />
      <el-table-column prop="age" label="年龄" width="100" />
      <el-table-column prop="address" label="地址" />
    </el-table>
    <div class="pagination-container">
      <el-pagination
        background
        layout="prev, pager, next, jumper, ->, total"
        :total="total"
        :page-size="pageSize"
        :current-page.sync="currentPage"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [
        { id: 1, name: '张三', role: '开发', sex: '男', age: 28, address: '北京' },
        { id: 2, name: '李四', role: '测试', sex: '女', age: 22, address: '上海' },
        { id: 3, name: '王五', role: '产品', sex: '男', age: 32, address: '广州' },
        { id: 4, name: '赵六', role: '设计', sex: '女', age: 24, address: '深圳' },
        { id: 5, name: '钱七', role: '开发', sex: '男', age: 28, address: '成都' },
        { id: 6, name: '孙八', role: '测试', sex: '女', age: 22, address: '武汉' },
        { id: 7, name: '周九', role: '产品', sex: '男', age: 32, address: '重庆' },
        { id: 8, name: '吴十', role: '设计', sex: '女', age: 24, address: '杭州' },
        { id: 9, name: '郑十一', role: '开发', sex: '男', age: 28, address: '西安' },
        { id: 10, name: '王十二', role: '测试', sex: '女', age: 22, address: '天津' },
        { id: 11, name: '冯十三', role: '产品', sex: '男', age: 32, address: '苏州' },
        { id: 12, name: '陈十四', role: '设计', sex: '女', age: 24, address: '宁波' },
        // 更多数据...
      ],
      currentPage: 1,
      pageSize: 5,
    }
  },
  computed: {
    total() {
      return this.tableData.length
    },
    pagedData() {
      const start = (this.currentPage - 1) * this.pageSize
      const end = start + this.pageSize
      return this.tableData.slice(start, end)
    }
  },
  methods: {
    handleCurrentChange(val) {
      this.currentPage = val
    }
  }
}
</script>

<style scoped>
.container {
  padding: 20px;
}
.pagination-container {
  text-align: right;
  margin: 20px 0;
}
</style>
