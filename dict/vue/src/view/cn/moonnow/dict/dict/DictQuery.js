export default class DictQuery {
  constructor () {
    this.toolExists = null // 解决多对多关系预留字段之存在
    this.toolNotExists = null // 解决多对多关系预留字段之不存在
    this.dictId = null // 数据字典编号
    this.dictKey = null // 数据字典标识
    this.dictName = null // 数据字典名称
    this.notes = null // 说明
    this.weightOrder = null // 排序权重
    this.versions = null // 版本号
    this.createId = null // 创建人账号
    this.createName = null // 创建人名称
    this.createTimes = null // 创建时间
    this.modifyId = null // 修改人账号
    this.modifyName = null // 修改人名称
    this.modifyTimes = null // 修改时间
    this.dictIdAndin = null // 数据字典编号Andin查询
    this.dictKeyAndeq = null // 数据字典标识Andeq查询
    this.dictKeyAndKeyLike = null // 数据字典标识AndKeyLike查询
    this.dictNameOrKeyLike = null // 数据字典名称OrKeyLike查询
    this.notesOrKeyLike = null // 说明OrKeyLike查询
    this.createIdOrKeyLike = null // 创建人账号OrKeyLike查询
    this.createNameOrKeyLike = null // 创建人名称OrKeyLike查询
    this.modifyIdOrKeyLike = null // 修改人账号OrKeyLike查询
    this.modifyNameOrKeyLike = null // 修改人名称OrKeyLike查询
  }
}
