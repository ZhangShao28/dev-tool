import ToolUtil from '@/tool/ToolUtil'
import ToolAxios from '@/tool/ToolAxios'

const url = 'code/query'

export default {
  save: (query) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.save), query)
  },
  batchSave: (querys) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchSave), querys)
  },
  update: (query) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.update), query)
  },
  batchUpdate: (querys) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchUpdate), querys)
  },
  remove: (query) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.remove), query)
  },
  batchRemove: (querys) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchRemove), querys)
  },
  getByPk: (query) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getByPk), query)
  },
  getAll: (queryQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAll), queryQuery)
  },
  paging: (queryQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.paging, rows, page), queryQuery)
  },
  getVOByPk: (query) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getVOByPk), query)
  },
  getAllVO: (queryQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAllVO), queryQuery)
  },
  pagingVO: (queryQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.pagingVO, rows, page), queryQuery)
  }
}
