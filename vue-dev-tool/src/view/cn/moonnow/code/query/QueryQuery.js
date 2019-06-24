export default class QueryQuery {
  constructor () {
    this.queryId = null // 查询编号
    this.queryType = null // 查询类型
    this.dtId = null // 数据库表编号
    this.columnsId = null // 列编号
    this.weightOrder = null // 排序权重
    this.queryIdAndin = null // 查询编号Andin查询
    this.queryTypeAndeq = null // 查询类型Andeq查询
    this.dtIdAndeq = null // 数据库表编号Andeq查询
    this.columnsIdAndeq = null // 列编号Andeq查询
    this.queryTypeAndKeyLike = null // 查询类型AndKeyLike查询
    this.dtIdOrKeyLike = null // 数据库表编号OrKeyLike查询
    this.columnsIdOrKeyLike = null // 列编号OrKeyLike查询
  }
}
