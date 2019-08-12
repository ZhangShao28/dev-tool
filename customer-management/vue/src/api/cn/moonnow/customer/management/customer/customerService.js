import ToolUtil from '@/tool/ToolUtil'
import ToolAxios from '@/tool/ToolAxios'

const url = 'customer/management/customer'

export default {
  save: (customer) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.save), customer)
  },
  batchSave: (customers) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchSave), customers)
  },
  update: (customer) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.update), customer)
  },
  batchUpdate: (customers) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchUpdate), customers)
  },
  remove: (customer) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.remove), customer)
  },
  batchRemove: (customers) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchRemove), customers)
  },
  getByPk: (customer) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getByPk), customer)
  },
  getAll: (customerQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAll), customerQuery)
  },
  paging: (customerQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.paging, rows, page), customerQuery)
  },
  getVOByPk: (customer) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getVOByPk), customer)
  },
  getAllVO: (customerQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAllVO), customerQuery)
  },
  pagingVO: (customerQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.pagingVO, rows, page), customerQuery)
  }
}
