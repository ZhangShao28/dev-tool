import ToolUtil from '@/tool/ToolUtil'
import ToolAxios from '@/tool/ToolAxios'

const url = 'sign/account'

export default {
  save: (account) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.save), account)
  },
  batchSave: (accounts) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchSave), accounts)
  },
  update: (account) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.update), account)
  },
  batchUpdate: (accounts) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchUpdate), accounts)
  },
  remove: (account) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.remove), account)
  },
  batchRemove: (accounts) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchRemove), accounts)
  },
  getByPk: (account) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getByPk), account)
  },
  getAll: (accountQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAll), accountQuery)
  },
  paging: (accountQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.paging, rows, page), accountQuery)
  },
  getVOByPk: (account) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getVOByPk), account)
  },
  getAllVO: (accountQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAllVO), accountQuery)
  },
  pagingVO: (accountQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.pagingVO, rows, page), accountQuery)
  }
}
