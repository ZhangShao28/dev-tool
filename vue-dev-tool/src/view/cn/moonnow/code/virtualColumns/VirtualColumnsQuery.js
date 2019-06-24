export default class VirtualColumnsQuery {
  constructor () {
    this.virtualColumnsId = null // 虚拟列编号
    this.sourceDtId = null // 源表数据库表编号
    this.targetDtId = null // 目标表数据库表编号
    this.sourceColumnsId = null // 源表列编号
    this.targetColumnsId = null // 目标表列编号
    this.targetDisplayColumnsId = null // 目标表显示字段列编号
    this.displayColumnsAlias = null // 显示字段别名
    this.virtualColumnsSql = null // 虚拟列sql
    this.weightOrder = null // 排序权重
    this.virtualColumnsIdAndin = null // 虚拟列编号Andin查询
    this.sourceDtIdAndKeyLike = null // 源表数据库表编号AndKeyLike查询
    this.targetDtIdOrKeyLike = null // 目标表数据库表编号OrKeyLike查询
    this.sourceColumnsIdOrKeyLike = null // 源表列编号OrKeyLike查询
    this.targetColumnsIdOrKeyLike = null // 目标表列编号OrKeyLike查询
    this.targetDisplayColumnsIdOrKeyLike = null // 目标表显示字段列编号OrKeyLike查询
    this.displayColumnsAliasOrKeyLike = null // 显示字段别名OrKeyLike查询
    this.virtualColumnsSqlOrKeyLike = null // 虚拟列sqlOrKeyLike查询
  }
}
