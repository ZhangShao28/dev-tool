import usrService from '@/api/cn/moonnow/sign/usr/usrService'

export default {
  namespaced: true,
  state: {
    aa: 'qwer'
  },
  mutations: {
    setAa (state) {
      state.aa = 'asdf'
    }
  },
  actions: {
    cs ({ commit, state }) {
      commit('setAa')
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
