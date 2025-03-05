<template>
    <el-menu :default-active="activeIndex" class="el-menu-vertical-demo">
      <el-menu-item index="1" @click="$router.push('/')">首页</el-menu-item>
      <el-menu-item index="2" @click="$router.push('/data-query')">数据查询</el-menu-item>
      <el-menu-item index="3" @click="$router.push('/product')">商品</el-menu-item>
      <el-menu-item index="4" @click="logout">退出</el-menu-item>
    </el-menu>
  </template>
  
  <script>
  export default {
    data() {
      return {
        activeIndex: '1'
      };
    },
    methods: {
      logout() {
        // 实现退出逻辑，例如清除token并跳转到登录页
        this.$router.push('/login');
      }
    }
  };
  </script>
  
  <style scoped>
  .el-menu-vertical-demo {
    height: 100vh;
  }
  </style>