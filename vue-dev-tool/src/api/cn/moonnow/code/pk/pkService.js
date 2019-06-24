import ToolUtil from '@/tool/ToolUtil'
import ToolAxios from '@/tool/ToolAxios'

const url = 'code/pk'

export default {
  save: (pk) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.save), pk)
  },
  batchSave: (pks) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchSave), pks)
  },
  update: (pk) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.update), pk)
  },
  batchUpdate: (pks) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchUpdate), pks)
  },
  remove: (pk) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.remove), pk)
  },
  batchRemove: (pks) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchRemove), pks)
  },
  getByPk: (pk) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getByPk), pk)
  },
  getAll: (pkQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAll), pkQuery)
  },
  paging: (pkQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.paging, rows, page), pkQuery)
  },
  getVOByPk: (pk) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getVOByPk), pk)
  },
  getAllVO: (pkQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAllVO), pkQuery)
  },
  pagingVO: (pkQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.pagingVO, rows, page), pkQuery)
  }
}
