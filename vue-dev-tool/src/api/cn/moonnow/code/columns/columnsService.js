import ToolUtil from '@/tool/ToolUtil'
import ToolAxios from '@/tool/ToolAxios'

const url = 'code/columns'

export default {
  save: (columns) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.save), columns)
  },
  batchSave: (columnss) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchSave), columnss)
  },
  update: (columns) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.update), columns)
  },
  batchUpdate: (columnss) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchUpdate), columnss)
  },
  remove: (columns) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.remove), columns)
  },
  batchRemove: (columnss) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchRemove), columnss)
  },
  getByPk: (columns) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getByPk), columns)
  },
  getAll: (columnsQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAll), columnsQuery)
  },
  paging: (columnsQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.paging, rows, page), columnsQuery)
  },
  getVOByPk: (columns) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getVOByPk), columns)
  },
  getAllVO: (columnsQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAllVO), columnsQuery)
  },
  pagingVO: (columnsQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.pagingVO, rows, page), columnsQuery)
  }
}
