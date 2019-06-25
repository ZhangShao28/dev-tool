import ToolUtil from '@/tool/ToolUtil'
import ToolAxios from '@/tool/ToolAxios'

const url = 'dict/dictValue'

export default {
  save: (dictValue) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.save), dictValue)
  },
  batchSave: (dictValues) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchSave), dictValues)
  },
  update: (dictValue) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.update), dictValue)
  },
  batchUpdate: (dictValues) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchUpdate), dictValues)
  },
  remove: (dictValue) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.remove), dictValue)
  },
  batchRemove: (dictValues) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchRemove), dictValues)
  },
  getByPk: (dictValue) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getByPk), dictValue)
  },
  getAll: (dictValueQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAll), dictValueQuery)
  },
  paging: (dictValueQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.paging, rows, page), dictValueQuery)
  },
  getVOByPk: (dictValue) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getVOByPk), dictValue)
  },
  getAllVO: (dictValueQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAllVO), dictValueQuery)
  },
  pagingVO: (dictValueQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.pagingVO, rows, page), dictValueQuery)
  }
}
