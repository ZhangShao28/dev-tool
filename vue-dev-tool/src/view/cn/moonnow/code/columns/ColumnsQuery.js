export default class ColumnsQuery {
  constructor () {
    this.columnsId = null // 列编号
    this.columnName = null // 列名
    this.columnNameAnnotation = null // 列名注释
    this.dataType = null // 数据类型
    this.isNull = null // 是否为空
    this.initialCaseColumnName = null // 首字母大写列名
    this.initialLowercaseColumnName = null // 首字母小写列名
    this.weightOrder = null // 排序权重
    this.dtId = null // 数据库表编号
    this.columnsIdAndin = null // 列编号Andin查询
    this.columnNameAndeq = null // 列名Andeq查询
    this.dtIdAndeq = null // 数据库表编号Andeq查询
    this.columnNameAndKeyLike = null // 列名AndKeyLike查询
    this.columnNameAnnotationOrKeyLike = null // 列名注释OrKeyLike查询
    this.dataTypeOrKeyLike = null // 数据类型OrKeyLike查询
    this.isNullOrKeyLike = null // 是否为空OrKeyLike查询
    this.initialCaseColumnNameOrKeyLike = null // 首字母大写列名OrKeyLike查询
    this.initialLowercaseColumnNameOrKeyLike = null // 首字母小写列名OrKeyLike查询
    this.dtIdOrKeyLike = null // 数据库表编号OrKeyLike查询
  }
}
