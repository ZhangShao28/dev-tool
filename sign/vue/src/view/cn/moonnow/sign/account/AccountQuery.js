export default class AccountQuery {
  constructor () {
    this.accountId = null // 账号编号
    this.account = null // 账号
    this.password = null // 密码
    this.mobilePhoneNumber = null // 手机号
    this.nexusType = null // 关联类型
    this.nexusObjectId = null // 对象编号
    this.versions = null // 版本号
    this.accountIdAndin = null // 账号编号Andin查询
    this.accountAndeq = null // 账号Andeq查询
    this.accountAndKeyLike = null // 账号AndKeyLike查询
    this.passwordOrKeyLike = null // 密码OrKeyLike查询
    this.mobilePhoneNumberOrKeyLike = null // 手机号OrKeyLike查询
    this.nexusTypeOrKeyLike = null // 关联类型OrKeyLike查询
    this.nexusObjectIdOrKeyLike = null // 对象编号OrKeyLike查询
  }
}
