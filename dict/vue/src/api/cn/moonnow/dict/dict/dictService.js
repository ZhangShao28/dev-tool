import ToolUtil from '@/tool/ToolUtil'
import ToolAxios from '@/tool/ToolAxios'

const url = 'dict/dict'

export default {
  save: (dict) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.save), dict)
  },
  batchSave: (dicts) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchSave), dicts)
  },
  update: (dict) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.update), dict)
  },
  batchUpdate: (dicts) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchUpdate), dicts)
  },
  remove: (dict) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.remove), dict)
  },
  batchRemove: (dicts) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchRemove), dicts)
  },
  getByPk: (dict) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getByPk), dict)
  },
  getAll: (dictQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAll), dictQuery)
  },
  paging: (dictQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.paging, rows, page), dictQuery)
  },
  getVOByPk: (dict) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getVOByPk), dict)
  },
  getAllVO: (dictQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAllVO), dictQuery)
  },
  pagingVO: (dictQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.pagingVO, rows, page), dictQuery)
  }
}
