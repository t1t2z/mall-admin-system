<template>
  <div class="data-query-container">
    <!-- 页面标题 -->
    <el-card class="title-card">
      <h1 class="title">埋点管理</h1>
    </el-card>

    <!-- 筛选与添加区域 -->
    <el-card class="filter-card">
      <el-form :inline="true" :model="queryForm" class="demo-form-inline">
        <el-form-item label="事件名称">
          <el-input v-model="queryForm.eventName" placeholder="请输入事件名称" size="large"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="queryForm.description" placeholder="请输入描述" size="large"></el-input>
        </el-form-item>
        <el-button type="primary" @click="queryEvents" size="large">查询</el-button>
        <el-button type="success" @click="showAddDialog" size="large" style="margin-left: 10px">添加事件</el-button>
      </el-form>
    </el-card>

    <!-- 数据表格 -->
    <el-card class="table-card">
      <el-table :data="currentPageData" style="width: 100%" :stripe="true" :border="true" :fit="true">
        <el-table-column prop="eventName" label="事件名称" min-width="150"></el-table-column>
        <el-table-column prop="description" label="描述" min-width="200"></el-table-column>
        <el-table-column prop="location" label="触发位置" min-width="150"></el-table-column>
        <el-table-column prop="trigger" label="触发逻辑" min-width="120"></el-table-column>
        <el-table-column prop="creattime" label="创建时间" min-width="150"></el-table-column>
        <el-table-column label="操作" min-width="180">
          <template #default="scope">
            <el-button type="primary" size="small" @click="editEvent(scope.row)" style="margin-right: 10px">编辑</el-button>
            <el-button type="danger" size="small" @click="deleteEvent(scope.row.eventName)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页器 -->
      <el-pagination
        v-if="tableData.length > 0"
        class="pagination"
        background
        layout="total, prev, pager, next, jumper"
        :total="totalItems"
        :page-size="pageSize"
        :current-page="currentPage"
        @current-change="handlePageChange"
      />
    </el-card>

    <!-- 添加/编辑对话框 -->
    <el-dialog :title="dialogTitle" v-model="dialogVisible" width="40%">
      <el-form :model="formData" :rules="rules" ref="formRef">
        <el-form-item label="事件名称" prop="eventName">
          <el-input v-model="formData.eventName" placeholder="请输入事件名称" size="medium"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="formData.description" placeholder="请输入描述" size="medium" type="textarea"></el-input>
        </el-form-item>
        <el-form-item label="触发位置" prop="location">
          <el-input v-model="formData.location" placeholder="请输入触发位置（如 button#registerBtn）" size="medium"></el-input>
        </el-form-item>
        <el-form-item label="触发逻辑" prop="trigger">
          <el-select v-model="formData.trigger" placeholder="请选择触发逻辑" size="medium">
            <el-option label="点击（click）" value="click"></el-option>
            <el-option label="提交（submit）" value="submit"></el-option>
            <el-option label="加载（load）" value="load"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="创建时间" prop="creattime">
          <el-date-picker
            v-model="formData.creattime"
            type="datetime"
            placeholder="选择创建时间"
            size="medium"
            value-format="YYYY-MM-DD HH:mm:ss"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="submitEvent">确认</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'DataQuery',
  data() {
    return {
      queryForm: {
        eventName: '',
        description: '',
      },
      dialogVisible: false,
      dialogTitle: '添加事件',
      formData: {
        eventName: '',
        description: '',
        location: '',
        trigger: 'click', // 默认触发逻辑为点击
        creattime: new Date().toISOString().split('.')[0].replace('T', ' '), // 默认当前时间
      },
      rules: {
        eventName: [{ required: true, message: '请输入事件名称', trigger: 'blur' }],
        description: [{ required: true, message: '请输入描述', trigger: 'blur' }],
        location: [{ required: true, message: '请输入触发位置', trigger: 'blur' }],
        trigger: [{ required: true, message: '请选择触发逻辑', trigger: 'blur' }],
        creattime: [{ required: true, message: '请选择创建时间', trigger: 'blur' }],
      },
      tableData: [
        { eventName: 'event1', description: '描述1', location: 'button#btn1', trigger: 'click', creattime: '2023-10-01 10:00:00' },
        { eventName: 'event2', description: '描述2', location: 'button#btn2', trigger: 'submit', creattime: '2023-10-02 12:00:00' },
        { eventName: 'event3', description: '描述3', location: 'button#btn3', trigger: 'load', creattime: '2023-10-03 14:00:00' },
      ],
      pageSize: 10,
      currentPage: 1,
      totalItems: 0,
    };
  },
  computed: {
    currentPageData() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.tableData.slice(start, end);
    },
  },
  mounted() {
    this.queryEvents(); // 页面加载时默认查询
  },
  methods: {
    queryEvents() {
      this.currentPage = 1; // 重置到第一页
      let filteredData = this.tableData;
      if (this.queryForm.eventName) {
        filteredData = filteredData.filter(item => item.eventName.includes(this.queryForm.eventName));
      }
      if (this.queryForm.description) {
        filteredData = filteredData.filter(item => item.description.includes(this.queryForm.description));
      }
      this.tableData = filteredData;
      this.totalItems = this.tableData.length;
    },
    handlePageChange(page) {
      this.currentPage = page;
    },
    showAddDialog() {
      this.dialogTitle = '添加事件';
      this.formData = {
        eventName: '',
        description: '',
        location: '',
        trigger: 'click',
        creattime: new Date().toISOString().split('.')[0].replace('T', ' '),
      };
      this.dialogVisible = true;
    },
    editEvent(row) {
      this.dialogTitle = '编辑事件';
      this.formData = { ...row }; // 复制行数据到表单
      this.dialogVisible = true;
    },
    deleteEvent(eventName) {
      this.tableData = this.tableData.filter(item => item.eventName !== eventName);
      this.totalItems = this.tableData.length;
      console.log('删除成功');
    },
    submitEvent() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          const isEdit = this.dialogTitle === '编辑事件';
          if (isEdit) {
            const index = this.tableData.findIndex(item => item.eventName === this.formData.eventName);
            if (index !== -1) {
              this.tableData.splice(index, 1, { ...this.formData });
              console.log('更新成功');
            }
          } else {
            this.tableData.push({ ...this.formData });
            console.log('添加成功');
          }
          this.totalItems = this.tableData.length;
          this.dialogVisible = false;
        }
      });
    },
  },
};
</script>

<style scoped>
.data-query-container { padding: 20px; }
.title-card { margin-bottom: 20px; }
.filter-card { margin-bottom: 20px; }
.table-card { margin-bottom: 20px; }
.add-button { text-align: right; }
.pagination { margin-top: 20px; }
</style>