// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import DataQuery from '../views/DataQuery.vue';
import HomePage from '../views/HomePage.vue';
import ProductPage from '../views/ProductPage.vue';

const routes = [
  { path: '/', name: 'Home', component: HomePage },
  { path: '/data-query', name: 'DataQuery', component: DataQuery },
  { path: '/product', name: 'ProductPage', component: ProductPage },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;