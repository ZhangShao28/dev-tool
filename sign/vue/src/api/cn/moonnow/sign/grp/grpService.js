import ToolUtil from '@/tool/ToolUtil'
import ToolAxios from '@/tool/ToolAxios'

const url = 'sign/grp'

export default {
  save: (grp) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.save), grp)
  },
  batchSave: (grps) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchSave), grps)
  },
  update: (grp) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.update), grp)
  },
  batchUpdate: (grps) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchUpdate), grps)
  },
  remove: (grp) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.remove), grp)
  },
  batchRemove: (grps) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchRemove), grps)
  },
  getByPk: (grp) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getByPk), grp)
  },
  getAll: (grpQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAll), grpQuery)
  },
  paging: (grpQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.paging, rows, page), grpQuery)
  },
  getVOByPk: (grp) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getVOByPk), grp)
  },
  getAllVO: (grpQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAllVO), grpQuery)
  },
  pagingVO: (grpQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.pagingVO, rows, page), grpQuery)
  }
}
