import ToolUtil from '@/tool/ToolUtil'
import ToolAxios from '@/tool/ToolAxios'

const url = 'code/dt'

export default {
  save: (dt) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.save), dt)
  },
  batchSave: (dts) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchSave), dts)
  },
  update: (dt) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.update), dt)
  },
  batchUpdate: (dts) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchUpdate), dts)
  },
  remove: (dt) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.remove), dt)
  },
  batchRemove: (dts) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchRemove), dts)
  },
  getByPk: (dt) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getByPk), dt)
  },
  getAll: (dtQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAll), dtQuery)
  },
  paging: (dtQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.paging, rows, page), dtQuery)
  },
  getVOByPk: (dt) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getVOByPk), dt)
  },
  getAllVO: (dtQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAllVO), dtQuery)
  },
  pagingVO: (dtQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.pagingVO, rows, page), dtQuery)
  }
}
