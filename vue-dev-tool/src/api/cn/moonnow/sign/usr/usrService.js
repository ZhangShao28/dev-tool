import axios from 'axios'
import ToolUtil from '@/tool/ToolUtil'

const url = '/api/sign/usr/cs'

export default {
  cs: () => {
    // let aa = ToolUtil.save
    // ToolUtil.save = 'qwerasdf'
    // let bb = ToolUtil.save
    // debugger
    alert(location.host + '-token')
    alert(ToolUtil.getUrl(url, ToolUtil.save))
    return axios.post(url, {})
  }
}
