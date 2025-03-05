// src/main.js
import { createApp } from 'vue';
import { createStore } from 'vuex';
import App from './App.vue';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import router from './router';

const store = createStore({
  state: {
    eventDefinitions: [], // 存储埋点事件定义
    tableData: [], // 存储商品或埋点数据（为 HomePage 和 ProductPage 提供数据）
  },
  mutations: {
    setEventDefinitions(state, data) {
      state.eventDefinitions = data;
    },
    updateEventDefinition(state, newItem) {
      const index = state.eventDefinitions.findIndex(item => item.id === newItem.id);
      if (index !== -1) {
        state.eventDefinitions.splice(index, 1, newItem);
      } else {
        state.eventDefinitions.push(newItem);
      }
    },
    setTableData(state, data) {
      state.tableData = data;
    },
    updateTableData(state, newItem) {
      const index = state.tableData.findIndex(item => item.productName === newItem.productName);
      if (index !== -1) {
        state.tableData.splice(index, 1, newItem);
      } else {
        state.tableData.push(newItem);
      }
    },
  },
  actions: {
    syncEventsToFrontend({ state }) {
      localStorage.setItem('eventDefinitions', JSON.stringify(state.eventDefinitions));
      window.dispatchEvent(new CustomEvent('eventDefinitionsUpdated', {
        detail: state.eventDefinitions
      }));
    },
  },
});

const app = createApp(App);
app.use(ElementPlus);
app.use(router);
app.use(store);
app.mount('#app');