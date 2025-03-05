<template>
  <div class="product-container">
    <!-- 页面标题 -->
    <el-card class="title-card">
      <h1 class="title">商品数据</h1>
    </el-card>

    <!-- 商品选择和维度筛选区域 -->
    <el-card class="filter-card">
      <el-form :inline="true" :model="form" class="demo-form-inline">
        <el-form-item label="选择商品">
          <el-select
            v-model="selectedProduct"
            placeholder="请选择商品"
            size="large"
            @change="queryProductData"
            style="width: 120px;"  
          >
            <el-option label="商品A" value="A"></el-option>
            <el-option label="商品B" value="B"></el-option>
            <el-option label="商品C" value="C"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="查询维度">
          <el-select
            v-model="selectedDimension"
            placeholder="请选择维度"
            size="large"
            @change="queryProductData"
            style="width: 120px;"  
          >
            <el-option label="营业额趋势" value="revenue_trend"></el-option>
            <el-option label="成交次数趋势" value="purchase_count_trend"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="选择时间区间">
          <el-date-picker
            v-model="selectedTimeRange"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            size="large"
            @change="fetchChartData"
            :picker-options="pickerOptions"
            style="width: 300px;"
          ></el-date-picker>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- 折线图展示区域 -->
    <el-card class="chart-card">
      <template #header>
        <div class="card-header">
          <span>{{ selectedDimension === 'revenue_trend' ? '营业额趋势' : '成交次数趋势' }} - {{ selectedProductLabel }}</span>
        </div>
      </template>
      <div id="mainChart1" style="width: 100%; height: 400px;"></div>
    </el-card>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import { ref, onMounted, onUnmounted, watch, computed } from 'vue';

export default {
  name: 'ProductPage',
  setup() {
    const selectedProduct = ref('A'); // 默认选择商品A
    const selectedDimension = ref('revenue_trend'); // 默认查询维度为营业额趋势
    const selectedTimeRange = ref([]); // 使用数组存储时间区间
    const mainChart1 = ref(null);
    const tableData = ref([
      { date: '2023-10-01', buttonType: '1', count: 10 },
      { date: '2023-10-02', buttonType: '1', count: 15 },
      { date: '2023-10-03', buttonType: '1', count: 20 },
      { date: '2023-10-01', buttonType: '2', count: 8 },
      { date: '2023-10-02', buttonType: '2', count: 12 },
      { date: '2023-10-03', buttonType: '2', count: 18 },
      { date: '2023-10-01', buttonType: '3', count: 5 },
      { date: '2023-10-02', buttonType: '3', count: 10 },
      { date: '2023-10-03', buttonType: '3', count: 15 },
    ]);

    const selectedProductLabel = computed(() => `商品${selectedProduct.value}`);

    // 时间选择器的配置
    const pickerOptions = {
      disabledDate(time) {
        // 限制只能选择近五天内的日期
        const today = new Date();
        const fiveDaysAgo = new Date(today);
        fiveDaysAgo.setDate(today.getDate() - 5);
        return time.getTime() > today.getTime() || time.getTime() < fiveDaysAgo.getTime();
      },
    };

    // 根据选择的时间区间获取图表数据
    const fetchChartData = () => {
      updateCharts();
    };

    const queryProductData = () => {
      if (!selectedProduct.value) {
        console.warn('请选择商品');
        return;
      }
      fetchChartData();
    };

    const updateCharts = () => {
      const productData = tableData.value.filter(item => {
        const buttonType = item.buttonType;
        return (buttonType === '1' && selectedProduct.value === 'A') ||
               (buttonType === '2' && selectedProduct.value === 'B') ||
               (buttonType === '3' && selectedProduct.value === 'C');
      });

      const chartData = productData.map(item => ({
        date: item.date,
        value: selectedDimension.value === 'revenue_trend' ? item.count * 100 : item.count, // 营业额（每件100元）或成交次数
      })).sort((a, b) => new Date(a.date) - new Date(b.date));

      if (mainChart1.value) {
        mainChart1.value.setOption({
          title: { text: `${selectedDimension.value === 'revenue_trend' ? '营业额趋势' : '成交次数趋势'} - ${selectedProductLabel.value}` },
          tooltip: { trigger: 'axis' },
          xAxis: { type: 'category', data: [...new Set(chartData.map(item => item.date))], axisLabel: { rotate: 45 } },
          yAxis: { type: 'value', name: selectedDimension.value === 'revenue_trend' ? '营业额 (元)' : '成交次数' },
          series: [{ type: 'line', data: chartData.map(item => item.value), smooth: true, areaStyle: {} }],
        }, true); // 确保流畅更新
      }
    };

    watch([selectedProduct, selectedDimension, selectedTimeRange], () => {
      queryProductData();
    });

    onMounted(() => {
      mainChart1.value = echarts.init(document.getElementById('mainChart1'));
      // 默认选择最近一天的时间区间
      const today = new Date();
      const yesterday = new Date(today);
      yesterday.setDate(today.getDate() - 1);
      selectedTimeRange.value = [yesterday, today];
      fetchChartData(); // 初始加载默认时间区间的数据
    });

    onUnmounted(() => {
      if (mainChart1.value) mainChart1.value.dispose();
    });

    return {
      selectedProduct,
      selectedDimension,
      selectedTimeRange,
      pickerOptions,
      selectedProductLabel,
      fetchChartData,
    };
  },
};
</script>

<style scoped>
.product-container { padding: 20px; }
.title-card { margin-bottom: 20px; }
.filter-card { margin-bottom: 20px; }
.chart-card { margin-bottom: 20px; }
</style>