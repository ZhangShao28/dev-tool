import axios from 'axios'

const url = '/api/sign/usr/cs'

export default {
  cs: () => {
    return axios.post(url, {})
  }
}
