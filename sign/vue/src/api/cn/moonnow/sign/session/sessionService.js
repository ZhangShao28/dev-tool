import ToolUtil from '@/tool/ToolUtil'
import ToolAxios from '@/tool/ToolAxios'

const url = 'sign/session'

export default {
  save: (session) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.save), session)
  },
  batchSave: (sessions) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchSave), sessions)
  },
  update: (session) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.update), session)
  },
  batchUpdate: (sessions) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchUpdate), sessions)
  },
  remove: (session) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.remove), session)
  },
  batchRemove: (sessions) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchRemove), sessions)
  },
  getByPk: (session) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getByPk), session)
  },
  getAll: (sessionQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAll), sessionQuery)
  },
  paging: (sessionQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.paging, rows, page), sessionQuery)
  },
  getVOByPk: (session) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getVOByPk), session)
  },
  getAllVO: (sessionQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAllVO), sessionQuery)
  },
  pagingVO: (sessionQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.pagingVO, rows, page), sessionQuery)
  }
}
