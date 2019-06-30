import ToolUtil from '@/tool/ToolUtil'
import ToolAxios from '@/tool/ToolAxios'

const url = 'sign/accountGrpNexus'

export default {
  save: (accountGrpNexus) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.save), accountGrpNexus)
  },
  batchSave: (accountGrpNexuss) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchSave), accountGrpNexuss)
  },
  update: (accountGrpNexus) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.update), accountGrpNexus)
  },
  batchUpdate: (accountGrpNexuss) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchUpdate), accountGrpNexuss)
  },
  remove: (accountGrpNexus) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.remove), accountGrpNexus)
  },
  batchRemove: (accountGrpNexuss) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.batchRemove), accountGrpNexuss)
  },
  getByPk: (accountGrpNexus) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getByPk), accountGrpNexus)
  },
  getAll: (accountGrpNexusQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAll), accountGrpNexusQuery)
  },
  paging: (accountGrpNexusQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.paging, rows, page), accountGrpNexusQuery)
  },
  getVOByPk: (accountGrpNexus) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getVOByPk), accountGrpNexus)
  },
  getAllVO: (accountGrpNexusQuery) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.getAllVO), accountGrpNexusQuery)
  },
  pagingVO: (accountGrpNexusQuery, rows, page) => {
    return ToolAxios.post(ToolUtil.getUrl(url, ToolUtil.pagingVO, rows, page), accountGrpNexusQuery)
  }
}
