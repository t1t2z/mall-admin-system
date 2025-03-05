import { createStore } from 'vuex';

export default createStore({
  state: {
    // 定义状态，例如用户信息
    userId: 'admin123'
  },
  mutations: {
    // 定义状态修改方法
    setUserId(state, id) {
      state.userId = id;
    }
  },
  actions: {
    // 定义异步操作
  },
  modules: {
    // 模块化状态管理（可选）
  }
});