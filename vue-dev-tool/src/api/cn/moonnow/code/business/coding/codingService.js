import ToolUtil from '@/tool/ToolUtil'
import ToolAxios from '@/tool/ToolAxios'

const url = 'code/coding'

export default {
  extract: (dt) => {
    return ToolAxios.post(ToolUtil.getUrl(url, 'extract'), dt)
  },
  getConfig: (dt) => {
    return ToolAxios.post(ToolUtil.getUrl(url, 'get_config'), dt)
  },
  codingJdbcEntity: (configVo) => {
    return ToolAxios.post(ToolUtil.getUrl(url, 'coding_jdbc_entity'), configVo)
  },
  codingQuery: (configVo) => {
    return ToolAxios.post(ToolUtil.getUrl(url, 'coding_query'), configVo)
  },
  codingVo: (configVo) => {
    return ToolAxios.post(ToolUtil.getUrl(url, 'coding_vo'), configVo)
  },
  codingIPersistent: (configVo) => {
    return ToolAxios.post(ToolUtil.getUrl(url, 'coding_i_persistent'), configVo)
  },
  codingJdbcPersistentImpl: (configVo) => {
    return ToolAxios.post(ToolUtil.getUrl(url, 'coding_jdbc_persistent_impl'), configVo)
  },
  codingIService: (configVo) => {
    return ToolAxios.post(ToolUtil.getUrl(url, 'coding_i_service'), configVo)
  },
  codingServiceImpl: (configVo) => {
    return ToolAxios.post(ToolUtil.getUrl(url, 'coding_service_impl'), configVo)
  },
  codingController: (configVo) => {
    return ToolAxios.post(ToolUtil.getUrl(url, 'coding_controller'), configVo)
  },
  codingAll: (configVo) => {
    return ToolAxios.post(ToolUtil.getUrl(url, 'coding_all'), configVo)
  },
  codingProject: (configVo) => {
    return ToolAxios.post(ToolUtil.getUrl(url, 'coding_project'), configVo)
  },
  codingVueService: (configVo) => {
    return ToolAxios.post(ToolUtil.getUrl(url, 'coding_vue_service'), configVo)
  },
  codingVueStore: (configVo) => {
    return ToolAxios.post(ToolUtil.getUrl(url, 'coding_vue_store'), configVo)
  },
  codingVueEntity: (configVo) => {
    return ToolAxios.post(ToolUtil.getUrl(url, 'coding_vue_entity'), configVo)
  },
  codingVueQuery: (configVo) => {
    return ToolAxios.post(ToolUtil.getUrl(url, 'coding_vue_query'), configVo)
  },
  codingVueVo: (configVo) => {
    return ToolAxios.post(ToolUtil.getUrl(url, 'coding_vue_vo'), configVo)
  },
  codingVueView: (configVo) => {
    return ToolAxios.post(ToolUtil.getUrl(url, 'coding_vue_view'), configVo)
  },
  codingVueViewCss: (configVo) => {
    return ToolAxios.post(ToolUtil.getUrl(url, 'coding_vue_view_css'), configVo)
  }
}
