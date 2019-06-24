export default class DtVO {
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
  }
}
