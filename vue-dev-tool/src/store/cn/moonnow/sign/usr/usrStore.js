import usrService from '@/api/cn/moonnow/sign/usr/usrService'

export default {
  namespaced: true,
  state: {
  },
  mutations: {
  },
  actions: {
    cs ({ commit, state }) {
      return new Promise((resolve, reject) => {
        usrService.cs().then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    }
  },
  getters: {
  }
}
