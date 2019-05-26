// import axios from 'axios'
// import Cookies from 'js-cookie'
import CryptoJS from 'crypto-js'
import JsEncrypt from 'jsencrypt'

// 请求拦截器
// axios.interceptors.request.use(config => {
//   return config
// }, error => {
//   return Promise.reject(error)
// })

// 响应拦截器
// axios.interceptors.response.use(res => {
//   let url = res.config.url
//   let is = true
//   let loginurl = '/pro/login'
//   if (url.startsWith(loginurl)) {
//     is = false
//   }
//   if (is) {
//     Cookies.set('pro-token', res.data.token, { expires: new Date(res.data.cookiesExpireTimes) })
//   }
//   return res
// }, error => {
//   if (error.response.data.message) {
//     error.response.statusText = error.response.data.message
//     Cookies.remove('pro-token')
//   }
//   return Promise.reject(error)
// })

export default class ToolUtil {
  constructor () {
    this.save = 'save'
    this.batchSave = 'batch_save'
    this.update = 'update'
    this.batchUpdate = 'batch_update'
    this.remove = 'remove'
    this.batchRemove = 'batch_remove'
    this.getByPk = 'get_by_pk'
    this.getAll = 'get_all'
    this.paging = 'paging'
    this.getVOByPk = 'get_vo_by_pk'
    this.getAllVO = 'get_all_vo'
    this.pagingVO = 'paging_vo'
  }
  getUrl (parameter) {
    let url = '?pro=' + parameter.pro
    if (parameter.action && parameter.action !== '') {
      url = url + '&action=' + parameter.action
    }
    if (parameter.primaryKey && parameter.primaryKey !== '') {
      url = url + '&primaryKey=' + parameter.primaryKey
    }
    if (parameter.rows && parameter.rows != null) {
      url = url + '&rows=' + parameter.rows
    }
    if (parameter.page && parameter.page != null) {
      url = url + '&page=' + parameter.page
    }
    if (parameter.token && parameter.token !== '') {
      url = url + '&token=' + parameter.token
    }
    return url
  }
  encodeBase64 (str) {
    return CryptoJS.enc.Base64.stringify(CryptoJS.enc.Utf8.parse(str))
  }
  decodeBase64 (str) {
    return CryptoJS.enc.Base64.parse(str).toString(CryptoJS.enc.Utf8)
  }
  encodeAES (str, key) {
    return CryptoJS.AES.encrypt(str, CryptoJS.enc.Base64.parse(key), { mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7 }).toString()
  }
  decodeAES (str, key) {
    return CryptoJS.AES.decrypt(str, CryptoJS.enc.Base64.parse(key), { mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7 }).toString(CryptoJS.enc.Utf8)
  }
  encodeRSAByPublicKey (str, key) {
    let encrypt = new JsEncrypt()
    encrypt.setPublicKey(key)
    return encrypt.encrypt(str)
  }
  decodeRSAByPrivateKey (str, key) {
    let decrypt = new JsEncrypt()
    decrypt.setPrivateKey(key)
    return decrypt.decrypt(str)
  }
}
