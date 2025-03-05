<template>
  <div class="home-container">
    <!-- 页面标题 -->
    <el-card class="title-card">
      <h1 class="title">埋点平台</h1>
    </el-card>

    <!-- 数据概览区域 -->
    <el-row :gutter="20" class="data-overview">
      <el-col :span="12">
        <el-card class="data-card">
          <template #header>
            <div class="card-header">
              <span>访客总数</span>
            </div>
          </template>
          <div class="data-value">{{ totalVisitors }}</div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="data-card">
          <template #header>
            <div class="card-header">
              <span>营业额总数</span>
            </div>
          </template>
          <div class="data-value">{{ totalRevenue }} 元</div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 图表区域 -->
    <el-row :gutter="20" class="charts-row">
      <el-col :span="12">
        <el-card class="chart-card">
          <template #header>
            <div class="card-header">
              <span>商品营业额比例</span>
              <el-form :inline="true" class="time-filter" style="float: right;">
                <el-form-item label="选择时间区间">
                  <el-date-picker
                    v-model="selectedTimeRange"
                    type="daterange"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                    size="small"
                    @change="fetchPieChartData"
                    :picker-options="pickerOptions"
                    style="width: 300px;"
                  ></el-date-picker>
                </el-form-item>
              </el-form>
            </div>
          </template>
          <div id="pieChart" class="chart" style="width: 100%; height: 400px;"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="chart-card">
          <template #header>
            <div class="card-header">
              <span>每天营业额趋势</span>
              <el-form :inline="true" class="time-filter" style="float: right;">
                <el-form-item label="选择时间区间">
                  <el-date-picker
                    v-model="selectedTimeRange"
                    type="daterange"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                    size="small"
                    @change="fetchLineChartData"
                    :picker-options="pickerOptions"
                    style="width: 300px;"
                  ></el-date-picker>
                </el-form-item>
              </el-form>
            </div>
          </template>
          <div id="revenueChart" class="chart" style="width: 100%; height: 400px;"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: 'HomePage',
  data() {
    return {
      pieChart: null,
      revenueChart: null,
      totalRevenue: 0,
      totalVisitors: 0,
      selectedTimeRange: [], // 使用数组存储时间区间
      pickerOptions: {
        disabledDate(time) {
          // 限制只能选择近五天内的日期
          const today = new Date();
          const fiveDaysAgo = new Date(today);
          fiveDaysAgo.setDate(today.getDate() - 5);
          return time.getTime() > today.getTime() || time.getTime() < fiveDaysAgo.getTime();
        },
      },
      // 模拟数据
      salesData: [
        { buttonType: '1', count: 50 },
        { buttonType: '2', count: 30 },
        { buttonType: '3', count: 20 },
      ],
      dailyCounts: [
        { date: '2023-10-01', count: 10 },
        { date: '2023-10-02', count: 15 },
        { date: '2023-10-03', count: 20 },
        { date: '2023-10-04', count: 25 },
        { date: '2023-10-05', count: 30 },
      ],
    };
  },
  mounted() {
    this.initCharts();
    // 默认选择最近一天的时间区间
    const today = new Date();
    const yesterday = new Date(today);
    yesterday.setDate(today.getDate() - 1);
    this.selectedTimeRange = [yesterday, today];
    this.fetchPieChartData();
    this.fetchLineChartData();
    this.fetchHomeStats();
  },
  methods: {
    initCharts() {
      this.pieChart = echarts.init(document.getElementById('pieChart'));
      this.revenueChart = echarts.init(document.getElementById('revenueChart'));
    },
    fetchPieChartData() {
      const pricePerItem = 100;
      const pieData = this.salesData.map(item => ({
        name: `商品${String.fromCharCode(64 + parseInt(item.buttonType))}`,
        value: item.count * pricePerItem,
      }));
      if (this.pieChart) {
        this.pieChart.setOption({
          tooltip: { trigger: 'item' },
          legend: { top: '5%', left: 'center' },
          series: [{
            name: '营业额比例',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            label: { show: false, position: 'center' },
            emphasis: { label: { show: true, fontSize: 16, fontWeight: 'bold' } },
            labelLine: { show: false },
            data: pieData,
          }],
        }, true);
      }
    },
    fetchLineChartData() {
      this.updateLineChart(this.dailyCounts);
    },
    fetchHomeStats() {
      this.totalVisitors = 1000;
      this.totalRevenue = this.salesData.reduce((sum, item) => sum + item.count * 100, 0);
    },
    updateLineChart(data) {
      const pricePerItem = 100;
      const revenueData = data.reduce((acc, item) => {
        const date = item.date;
        const existing = acc.find(d => d.date === date);
        if (existing) existing.amount += item.count * pricePerItem;
        else acc.push({ date, amount: item.count * pricePerItem });
        return acc;
      }, []).sort((a, b) => new Date(a.date) - new Date(b.date));
      this.revenueChart.setOption({
        tooltip: { trigger: 'axis' },
        xAxis: { type: 'category', data: revenueData.map(item => item.date), axisLabel: { rotate: 45 } },
        yAxis: { type: 'value' },
        series: [{
          name: '营业额',
          type: 'line',
          data: revenueData.map(item => item.amount),
          smooth: true,
          areaStyle: {},
        }],
      }, true);
    },
  },
};
</script>

<style scoped>
.home-container { padding: 20px; }
.title-card { margin-bottom: 20px; }
.data-overview { margin-bottom: 20px; }
.data-card { height: 120px; }
.data-value { font-size: 24px; text-align: center; }
.charts-row { margin-bottom: 20px; }
.chart-card { height: 480px; }
.time-filter { margin-top: 10px; }
</style>