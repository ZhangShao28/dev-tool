export default class SessionVO {
  constructor () {
    this.sessionId = null // 会话编号
    this.accountId = null // 账号编号
    this.loginKey = null // 登录标识
    this.currentTimes = null // 当前时间
    this.aesKey = null // AES密钥
  }
}
