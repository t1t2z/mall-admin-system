const { defineConfig } = require('@vue/cli-service');
const fs = require('fs'); // 用于读取证书文件

module.exports = defineConfig({
  transpileDependencies: true,
  configureWebpack: {
    resolve: {
      fallback: {
        net: false, // 忽略 net 模块
        fs: false,  // 忽略 fs 模块
        tls: false, // 忽略 tls 模块
      },
    },
    module: {
      rules: [
        {
          test: /stompjs\/lib\/stomp-node\.js$/, // 排除 Node.js 专用的文件
          use: 'null-loader',
        },
      ],
    },
  },
  devServer: {
    // 启用 HTTPS 开发服务器
    https: {
      key: fs.readFileSync('./certs/localhost.key'), // 本地私钥文件路径
      cert: fs.readFileSync('./certs/localhost.crt'), // 本地证书文件路径
    },
    // 允许 WebSocket 连接（如果有跨域问题，可以配置代理）
    proxy: {
      '/socket': {
        target: 'wss://ecc2-183-6-90-100.ngrok-free.app', // 后端 WebSocket 地址
        ws: true, // 启用 WebSocket 代理
        changeOrigin: true, // 改变请求的源地址
        secure: false, // 接受非安全的证书（开发环境）
      },
    },
    // 允许混合内容（开发环境临时解决方案）
    allowedHosts: 'all',
  },
});