import ToolUtil from '@/tool/ToolUtil'
import ToolAxios from '@/tool/ToolAxios'

const url = 'sign/loginLog'

export default {
  save: (loginLog) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.save), loginLog)
  },
  batchSave: (loginLogs) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchSave), loginLogs)
  },
  update: (loginLog) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.update), loginLog)
  },
  batchUpdate: (loginLogs) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchUpdate), loginLogs)
  },
  remove: (loginLog) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.remove), loginLog)
  },
  batchRemove: (loginLogs) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchRemove), loginLogs)
  },
  getByPk: (loginLog) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getByPk), loginLog)
  },
  getAll: (loginLogQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAll), loginLogQuery)
  },
  paging: (loginLogQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.paging, rows, page), loginLogQuery)
  },
  getVOByPk: (loginLog) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getVOByPk), loginLog)
  },
  getAllVO: (loginLogQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAllVO), loginLogQuery)
  },
  pagingVO: (loginLogQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.pagingVO, rows, page), loginLogQuery)
  }
}
