export default class VirtualColumnsVO {
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
    this.dtName = null // 表名
    this.columnName = null // 列名
  }
}
