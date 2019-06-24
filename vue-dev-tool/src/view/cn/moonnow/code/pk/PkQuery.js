export default class PkQuery {
  constructor () {
    this.pkId = null // 主键编号
    this.dtId = null // 数据库表编号
    this.columnsId = null // 列编号
    this.pkIdAndin = null // 主键编号Andin查询
    this.dtIdAndeq = null // 数据库表编号Andeq查询
    this.columnsIdAndeq = null // 列编号Andeq查询
    this.dtIdAndKeyLike = null // 数据库表编号AndKeyLike查询
    this.columnsIdOrKeyLike = null // 列编号OrKeyLike查询
  }
}
