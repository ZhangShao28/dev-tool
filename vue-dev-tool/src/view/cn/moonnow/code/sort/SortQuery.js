export default class SortQuery {
  constructor () {
    this.sortId = null // 排序编号
    this.sortRule = null // 排序规则
    this.dtId = null // 数据库表编号
    this.columnsId = null // 列编号
    this.sortIdAndin = null // 排序编号Andin查询
    this.dtIdAndeq = null // 数据库表编号Andeq查询
    this.columnsIdAndeq = null // 列编号Andeq查询
    this.sortRuleAndKeyLike = null // 排序规则AndKeyLike查询
    this.dtIdOrKeyLike = null // 数据库表编号OrKeyLike查询
    this.columnsIdOrKeyLike = null // 列编号OrKeyLike查询
  }
}
