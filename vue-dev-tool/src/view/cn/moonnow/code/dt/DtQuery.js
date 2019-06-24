export default class DtQuery {
  constructor () {
    this.dtId = null // 数据库表编号
    this.dtSql = null // 数据库表sql
    this.dtName = null // 表名
    this.dtNameAnnotation = null // 表名注释
    this.dtPrefix = null // 数据库表前缀
    this.initialCaseEntityName = null // 首字母大写实体类名
    this.initialLowercaseEntityName = null // 首字母小写实体类名
    this.proPath = null // 项目路径
    this.proAllName = null // 项目全称
    this.dtIdAndin = null // 数据库表编号Andin查询
    this.dtNameAndeq = null // 表名Andeq查询
    this.dtNameAndKeyLike = null // 表名AndKeyLike查询
    this.dtNameAnnotationOrKeyLike = null // 表名注释OrKeyLike查询
    this.dtPrefixOrKeyLike = null // 数据库表前缀OrKeyLike查询
    this.initialCaseEntityNameOrKeyLike = null // 首字母大写实体类名OrKeyLike查询
    this.initialLowercaseEntityNameOrKeyLike = null // 首字母小写实体类名OrKeyLike查询
    this.proPathOrKeyLike = null // 项目路径OrKeyLike查询
    this.proAllNameOrKeyLike = null // 项目全称OrKeyLike查询
  }
}
