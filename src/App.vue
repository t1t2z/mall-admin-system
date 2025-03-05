<template>
  <div class="app-container">
    <!-- 顶部 Header -->
    <HeaderBar />

    <!-- 主体内容布局，使用 Flex 进行布局 -->
    <div class="main-content">
      <!-- 左侧导航栏 -->
      <SidebarNav />

      <!-- 页面内容（路由视图） -->
      <router-view class="content-view" />
    </div>
  </div>
</template>

<script>
import HeaderBar from './components/HeaderBar.vue'; // 假设已改为多单词名称 HeaderBar
import SidebarNav from './components/SidebarNav.vue'; // 假设已改为多单词名称 SidebarNav

export default {
  name: 'AppLayout', // 确保为多单词名称，符合 ESLint 规则
  components: {
    HeaderBar,
    SidebarNav
  }
};
</script>

<style scoped>
.app-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
}

.main-content {
  display: flex;
  flex: 1;
}

.content-view {
  flex: 1;
  padding: 20px;
  background-color: #f5f5f5;
}

/* 确保内容区域占据剩余空间 */
html, body {
  margin: 0;
  padding: 0;
  height: 100%;
}

#app {
  height: 100%;
}
</style>