import Cookies from 'js-cookie'
import CryptoJS from 'crypto-js'
import JsEncrypt from 'jsencrypt'

export default {
  privateKey: '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',
  publicKey: 'MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAlfk11GH4ZtsYADtzL8rUZaKo2EeEgnqpRmE2mHksTfnqQ26bz7vpAz0/rzbWg/T9BaC6kZqh9PkJFyV2X0eh/lg8tVgk1z2jN4K6Lr5S4XZT6VSckyDvSz9IyHa+nTLT0iSOQyHMCdY9rHLwotXaeoByfrOlQwWUS58JYj6w2uecZs9i9AjJ5cqwpXYPYL0wi2OM9QYLq+3fnbUu4ZOjHsuG/WgL39p0gLumHo+uk+NMYWyDQv2rU0Lv1mqdIHz7moNnXTXlbSGIM/D3xdK0cJCA5lgFBzcG9fdh0ZyPjU17J3ot524MMZLgekfo9Iv1Y5mfAfofMOlqBsLqxYDI0vlgmnXL9WTGGzvOXeEFavkzfuYGQdLBfr9rFCT+yrCCfweo7IvyVOYcY2Xfy116SZECu62oU46BsRJc5ccv5b5w5QRKdNuPAg3sHLIuwptbBrqJ3JSVmI0IRLDH36qochfDYfb6KDncY79gk6V8L5dcPQzLZbGN1oBylb7rqSyH2Uaf8In+ISF70iFZC3E2AmjVetSNhhqMg/4qY0ukaj9jsjpn67hsyd86J4A/jjK/U37a6G9yegA+6iUGZrIZyqI+9zpHtwT5cnnSR8qZTQPklejgVdGpcpDt8NCJ49WmSfAqW3vJ/Dp1W1OKEqSOvf19RMCTm/Uts0XYaVurnC8CAwEAAQ==',
  AESKey: 'Xy7qBgpcdXlL4j5hphpKwA==',
  authorizationInterceptor: true,
  httpRequestBodyEncode: false,
  httpResponseBodyDecode: false,
  save: 'save',
  batchSave: 'batch_save',
  update: 'update',
  batchUpdate: 'batch_update',
  remove: 'remove',
  batchRemove: 'batch_remove',
  getByPk: 'get_by_pk',
  getAll: 'get_all',
  paging: 'paging',
  getVOByPk: 'get_vo_by_pk',
  getAllVO: 'get_all_vo',
  pagingVO: 'paging_vo',
  getUrl: (url, action, rows, page) => {
    let token = Cookies.get(location.host + '-token')
    url = '/api/' + url + '?pro=' + +new Date()
    if (action && action !== '') {
      url = url + '&action=' + action
    }
    if (rows && rows != null) {
      url = url + '&rows=' + rows
    }
    if (page && page != null) {
      url = url + '&page=' + page
    }
    if (token && token !== '') {
      url = url + '&token=' + token
    }
    return url
  },
  encodeBase64: (str) => {
    return CryptoJS.enc.Base64.stringify(CryptoJS.enc.Utf8.parse(str))
  },
  decodeBase64: (str) => {
    return CryptoJS.enc.Base64.parse(str).toString(CryptoJS.enc.Utf8)
  },
  encodeAES: (str, key) => {
    return CryptoJS.AES.encrypt(str, CryptoJS.enc.Base64.parse(key), { mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7 }).toString()
  },
  decodeAES: (str, key) => {
    return CryptoJS.AES.decrypt(str, CryptoJS.enc.Base64.parse(key), { mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7 }).toString(CryptoJS.enc.Utf8)
  },
  encodeRSAByPublicKey: (str, key) => {
    let encrypt = new JsEncrypt()
    encrypt.setPublicKey(key)
    return encrypt.encrypt(str)
  },
  decodeRSAByPrivateKey: (str, key) => {
    let decrypt = new JsEncrypt()
    decrypt.setPrivateKey(key)
    return decrypt.decrypt(str)
  }
}
