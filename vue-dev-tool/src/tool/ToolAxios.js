import axios from 'axios'
import ToolUtil from '@/tool/ToolUtil'

const ToolAxios = axios.create({
  headers: {
    'Content-Type': 'application/json;charset=UTF-8'
  },
  // 请求加密
  transformRequest: (data, headers) => {
    if (ToolUtil.httpRequestBodyEncode) {
      return ToolUtil.encodeAES(JSON.stringify(data), ToolUtil.AESKey)
    }
    return JSON.stringify(data)
  },
  // 响应解密
  transformResponse: (data) => {
    try {
      if (ToolUtil.httpResponseBodyDecode) {
        return JSON.parse(ToolUtil.decodeAES(JSON.parse(data), ToolUtil.AESKey))
      }
      return JSON.parse(data)
    } catch (error) {
      return data
    }
  }
})

// 请求拦截器
ToolAxios.interceptors.request.use(config => {
  if (ToolUtil.authorizationInterceptor) {
    return config
  } else {
    return config
  }
}, error => {
  if (ToolUtil.authorizationInterceptor) {
    return Promise.reject(error)
  } else {
    return Promise.reject(error)
  }
})

// 响应拦截器
ToolAxios.interceptors.response.use(res => {
  if (ToolUtil.authorizationInterceptor) {
    return res
  } else {
    return res
  }
  // let url = res.config.url
  // let is = true
  // let loginurl = '/pro/login'
  // if (url.startsWith(loginurl)) {
  //   is = false
  // }
  // if (is) {
  //   Cookies.set('pro-token', res.data.token, { expires: new Date(res.data.cookiesExpireTimes) })
  // }
}, error => {
  if (ToolUtil.authorizationInterceptor) {
    return Promise.reject(error)
  } else {
    return Promise.reject(error)
  }
  // if (error.response.data.message) {
  //   error.response.statusText = error.response.data.message
  //   Cookies.remove('pro-token')
  // }
})

export default ToolAxios
