export default class GrpQuery {
  constructor () {
    this.grpId = null // 组编号
    this.grpKey = null // 组标识
    this.grpName = null // 组名称
    this.notes = null // 说明
    this.versions = null // 版本号
    this.grpIdAndin = null // 组编号Andin查询
    this.grpKeyAndeq = null // 组标识Andeq查询
    this.grpKeyAndKeyLike = null // 组标识AndKeyLike查询
    this.grpNameOrKeyLike = null // 组名称OrKeyLike查询
    this.notesOrKeyLike = null // 说明OrKeyLike查询
  }
}
