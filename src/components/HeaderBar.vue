<template>
    <el-header>
      <span>用户ID: {{ userId }}</span>
    </el-header>
  </template>
  
  <script>
  export default {
    data() {
      return {
        userId: 'admin123' // 假设从后端获取
      };
    }
  };
  </script>
  
  <style scoped>
  el-header {
    display: flex;
    align-items: center;
    justify-content: flex-end;
    padding: 0 20px;
    height: 60px;
    background-color: #fff;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  </style>