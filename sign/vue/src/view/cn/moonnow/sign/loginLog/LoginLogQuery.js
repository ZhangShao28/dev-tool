export default class LoginLogQuery {
  constructor () {
    this.logId = null // 登录日志编号
    this.loginKey = null // 登录标识
    this.accountId = null // 账号编号
    this.account = null // 账号
    this.mobilePhoneNumber = null // 手机号
    this.loginTimes = null // 登录时间
    this.logIdAndin = null // 登录日志编号Andin查询
    this.loginKeyAndKeyLike = null // 登录标识AndKeyLike查询
    this.accountIdOrKeyLike = null // 账号编号OrKeyLike查询
    this.accountOrKeyLike = null // 账号OrKeyLike查询
    this.mobilePhoneNumberOrKeyLike = null // 手机号OrKeyLike查询
  }
}
