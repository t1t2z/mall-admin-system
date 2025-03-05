const logger = {
  info: (msg) => console.log(`INFO: ${msg}`),
  error: (msg) => console.error(`ERROR: ${msg}`),
};

let globalParams = {};
let availableEvents = []; // 存储前端创建的埋点事件

const initialize = () => {
  const userId = Math.random().toString(36).substring(2, 15);
  
  globalParams = {
    userId,
    browser: navigator.userAgent,
    timestamp: new Date().toISOString(),
    version: '1.0.0'
  };

  // 从 localStorage 或 window 事件加载可用事件
  const storedEvents = localStorage.getItem('eventDefinitions');
  if (storedEvents) {
    availableEvents = JSON.parse(storedEvents).map(item => item.eventName);
  }

  // 监听事件定义更新
  window.addEventListener('eventDefinitionsUpdated', (event) => {
    availableEvents = event.detail.map(item => item.eventName);
    localStorage.setItem('eventDefinitions', JSON.stringify(event.detail));
    logger.info('新事件定义已加载:', availableEvents);
  });

  logger.info('埋点 SDK 初始化完成，已获取用户环境信息和事件定义');
};

const sendEvent = async (eventName, params) => {
  if (!globalParams.userId) {
      logger.error('SDK 未初始化，请先调用 initialize()');
      return;
  }

  if (!availableEvents.includes(eventName)) {
      logger.error(`事件 ${eventName} 未定义，请联系管理员或在后台创建`);
      return;
  }

  const apiEndpoint = "https://ecc2-183-6-90-100.ngrok-free.app/api/events";

  let eventData = {
      eventId: params.eventId || Date.now().toString(),
      userId: globalParams.userId,
      browser: globalParams.browser,
      version: globalParams.version,
      timestamp: new Date().toISOString(),
      type: eventName
  };

  switch (eventName) {
      case "page":
          eventData = { ...eventData, pagePath: params.pagePath, referrer: params.referrer };
          break;
      case "button_click":
          eventData = { ...eventData, button: params.button, positionX: params.positionX, positionY: params.positionY };
          break;
      case "purchase_complete":
          eventData = { ...eventData, productId: params.productId, amount: params.amount };
          break;
      // 可扩展其他动态事件
      default:
          logger.warn(`未实现的事件参数处理: ${eventName}`);
  }

  try {
      const response = await fetch(apiEndpoint, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(eventData),
      });

      if (!response.ok) {
          throw new Error(`HTTP 错误：${response.status}`);
      }

      const result = await response.json();
      logger.info(`事件 ${eventName} 上报成功:`, result);
      return result;
  } catch (error) {
      logger.error(`事件 ${eventName} 上报失败:`, error);
  }
};

const reset = () => {
  globalParams = {};
  availableEvents = [];
  localStorage.removeItem('eventDefinitions');
};

// 暴露全局函数
window.initialize = initialize;
window.sendEvent = sendEvent;
window.getGlobalParams = () => globalParams;
window.reset = reset;