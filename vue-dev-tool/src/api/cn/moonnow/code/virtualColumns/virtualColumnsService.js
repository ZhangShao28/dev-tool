import ToolUtil from '@/tool/ToolUtil'
import ToolAxios from '@/tool/ToolAxios'

const url = 'code/virtualColumns'

export default {
  save: (virtualColumns) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.save), virtualColumns)
  },
  batchSave: (virtualColumnss) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchSave), virtualColumnss)
  },
  update: (virtualColumns) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.update), virtualColumns)
  },
  batchUpdate: (virtualColumnss) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchUpdate), virtualColumnss)
  },
  remove: (virtualColumns) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.remove), virtualColumns)
  },
  batchRemove: (virtualColumnss) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchRemove), virtualColumnss)
  },
  getByPk: (virtualColumns) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getByPk), virtualColumns)
  },
  getAll: (virtualColumnsQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAll), virtualColumnsQuery)
  },
  paging: (virtualColumnsQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.paging, rows, page), virtualColumnsQuery)
  },
  getVOByPk: (virtualColumns) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getVOByPk), virtualColumns)
  },
  getAllVO: (virtualColumnsQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAllVO), virtualColumnsQuery)
  },
  pagingVO: (virtualColumnsQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.pagingVO, rows, page), virtualColumnsQuery)
  }
}
