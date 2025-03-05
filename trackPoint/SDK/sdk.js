const logger = {
  info: (msg) => console.log(`INFO: ${msg}`),
  error: (msg) => console.error(`ERROR: ${msg}`),
};

let globalParams = {};

const initialize = (params = {}) => {
  const userId = params.userId || Math.random().toString(36).substring(2, 15);
  
  globalParams = {
    userId,
    browser: navigator.userAgent,
    timestamp: new Date().toISOString(),
    version: params.version || '1.0.0' // 设置默认值
  };

  logger.info('埋点 SDK 初始化完成，已获取用户环境信息');
};


const addCommonParams = (params) => {
  globalParams = { ...globalParams, ...params };
  logger.info('已添加通用参数');
};

const sendEvent = async (eventName, params) => {
  if (!globalParams.userId) {
    logger.error('SDK 未初始化，请先调用 initialize()');
    return;
  }

  const apiEndpoints = {
    error: "http://localhost:8081/track",
    form: "http://localhost:8081/track",
    button: "http://localhost:8081/track",
    page: "http://localhost:8081/track"
  };

  if (!apiEndpoints[eventName]) {
    logger.error(`未知事件类型: ${eventName}`);
    return;
  }

  // 初始化基本的事件数据
  let eventData = {
    eventName: eventName, // eventName 作为顶级属性
    param: { // 所有事件相关数据放在 'param' 中
      userId: globalParams.userId,
      browser: globalParams.browser,
      version: globalParams.version,
      timestamp: new Date().toISOString(),
      ...params // 扩展传入的 params 数据
    }
  };

  // 根据事件类型进行处理
  switch (eventName) {
    case "page":
      eventData.param = { 
        ...eventData.param, 
        pagePath: params.pagePath, 
        referrer: params.referrer 
      };
      break;
    case "button":
      eventData.param = { 
        ...eventData.param, 
        button: params.button, 
        positionX: params.positionX, 
        positionY: params.positionY 
      };
      break;
    case "error":
      eventData.param = { 
        ...eventData.param, 
        ErrorMessage: params.errorMessage,
        ErrorStack: params.errorStack 
      };
      break;
    case "form":
      eventData.param = { 
        ...eventData.param, 
        valid: params.valid === 1 ? 1 : 0, // 确保 valid 只能是 0 或 1
        form: params.form 
      };
      break;
    default:
      logger.error(`未知事件类型: ${eventName}`);
      return;
  }

  console.log('请求体:', JSON.stringify(eventData, null, 2));

  try {
    console.log('发送请求到:', apiEndpoints[eventName]);
    const response = await fetch(apiEndpoints[eventName], {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      //mode: 'no-cors', // 关闭 CORS 校验
      body: JSON.stringify(eventData),
    });
  
    console.log('响应状态:', response.status);
    const responseText = await response.text();
    console.log('响应数据:', responseText);
  
    if (!response.ok) {
      throw new Error(`HTTP 错误：${response.status}`);
    }
  
    let result;
   try {
   result = await response.json(); // 尝试解析 JSON
    } catch (e) {
   result = responseText; // 如果解析失败，直接使用文本
    }     
    logger.info(`事件 ${eventName} 上报成功:`, result);
    return result;

    logger.info(`事件 ${eventName} 上报成功:`, result);
    return result;
  } catch (error) {
    logger.error(`事件 ${eventName} 上报失败:`, error);
  }
};


// 暴露 SDK 到全局作用域
window.SDK = {
  initialize,
  sendEvent,
  addCommonParams
};
