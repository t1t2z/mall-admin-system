<template>
  <div class="websocket-status">WebSocket 功能已屏蔽，使用模拟数据</div>
</template>

<script>
export default {
  name: 'WebSocketClient',
  data() {
    return {
      totalVisitors: 1000, // 模拟访客总数
      salesData: [
        { buttonType: '1', count: 50 }, // 模拟商品A数据
        { buttonType: '2', count: 30 }, // 模拟商品B数据
        { buttonType: '3', count: 20 }, // 模拟商品C数据
      ],
    };
  },
  mounted() {
    // 模拟 WebSocket 实时更新数据
    setInterval(() => {
      this.totalVisitors = Math.floor(Math.random() * 2000) + 800; // 随机模拟访客总数
      this.salesData = [
        { buttonType: '1', count: Math.floor(Math.random() * 60) + 40 },
        { buttonType: '2', count: Math.floor(Math.random() * 40) + 20 },
        { buttonType: '3', count: Math.floor(Math.random() * 30) + 10 },
      ];
      this.$emit('update-home-data', {
        totalVisitors: this.totalVisitors,
        salesData: this.salesData,
      });
    }, 5000); // 每5秒模拟更新一次
  },
};
</script>

<style scoped>
.websocket-status { color: #666; font-size: 12px; margin-bottom: 10px; }
</style>