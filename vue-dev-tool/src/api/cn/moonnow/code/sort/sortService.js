import ToolUtil from '@/tool/ToolUtil'
import ToolAxios from '@/tool/ToolAxios'

const url = 'code/sort'

export default {
  save: (sort) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.save), sort)
  },
  batchSave: (sorts) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchSave), sorts)
  },
  update: (sort) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.update), sort)
  },
  batchUpdate: (sorts) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchUpdate), sorts)
  },
  remove: (sort) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.remove), sort)
  },
  batchRemove: (sorts) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchRemove), sorts)
  },
  getByPk: (sort) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getByPk), sort)
  },
  getAll: (sortQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAll), sortQuery)
  },
  paging: (sortQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.paging, rows, page), sortQuery)
  },
  getVOByPk: (sort) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getVOByPk), sort)
  },
  getAllVO: (sortQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAllVO), sortQuery)
  },
  pagingVO: (sortQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.pagingVO, rows, page), sortQuery)
  }
}
