import axios from 'axios'
import ToolUtil from '@/tool/ToolUtil'

const url = 'code/dt'

export default {
  save: (dt) => {
    return axios.post(ToolUtil.getUrl(url, ToolUtil.save), dt)
  },
  batchSave: (dts) => {
    return axios.post(ToolUtil.getUrl(url, ToolUtil.batchSave), dts)
  },
  update: (dt) => {
    return axios.post(ToolUtil.getUrl(url, ToolUtil.update), dt)
  },
  batchUpdate: (dts) => {
    return axios.post(ToolUtil.getUrl(url, ToolUtil.batchUpdate), dts)
  },
  remove: (dt) => {
    return axios.post(ToolUtil.getUrl(url, ToolUtil.remove), dt)
  },
  batchRemove: (dts) => {
    return axios.post(ToolUtil.getUrl(url, ToolUtil.batchRemove), dts)
  },
  getByPk: (dt) => {
    return axios.post(ToolUtil.getUrl(url, ToolUtil.getByPk), dt)
  },
  getAll: (dtQuery) => {
    return axios.post(ToolUtil.getUrl(url, ToolUtil.getAll), dtQuery)
  },
  paging: (dtQuery, rows, page) => {
    return axios.post(ToolUtil.getUrl(url, ToolUtil.paging, rows, page), dtQuery)
  },
  getVOByPk: (dt) => {
    return axios.post(ToolUtil.getUrl(url, ToolUtil.getVOByPk), dt)
  },
  getAllVO: (dtQuery) => {
    return axios.post(ToolUtil.getUrl(url, ToolUtil.getAllVO), dtQuery)
  },
  pagingVO: (dtQuery, rows, page) => {
    return axios.post(ToolUtil.getUrl(url, ToolUtil.pagingVO, rows, page), dtQuery)
  }
}
