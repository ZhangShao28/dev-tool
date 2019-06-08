import dtService from '@/api/cn/moonnow/code/dt/dtService'

export default {
  namespaced: true,
  state: {
  },
  mutations: {
  },
  actions: {
    saveDt ({ commit, state }, dt) {
      return new Promise((resolve, reject) => {
        dtService.save(dt).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchSaveDt ({ commit, state }, dts) {
      return new Promise((resolve, reject) => {
        dtService.batchSave(dts).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateDt ({ commit, state }, dt) {
      return new Promise((resolve, reject) => {
        dtService.update(dt).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchUpdateDt ({ commit, state }, dts) {
      return new Promise((resolve, reject) => {
        dtService.batchUpdate(dts).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeDt ({ commit, state }, dt) {
      return new Promise((resolve, reject) => {
        dtService.remove(dt).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchRemoveDt ({ commit, state }, dts) {
      return new Promise((resolve, reject) => {
        dtService.batchRemove(dts).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getDtByPk ({ commit, state }, dt) {
      return new Promise((resolve, reject) => {
        dtService.getByPk(dt).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllDt ({ commit, state }) {
      return new Promise((resolve, reject) => {
        dtService.getAll({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryDt ({ commit, state }, dtQuery) {
      return new Promise((resolve, reject) => {
        dtService.getAll(dtQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryDt ({ commit, state }, { dtQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        dtService.paging(dtQuery, rows, page).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getDtVOByPk ({ commit, state }, dt) {
      return new Promise((resolve, reject) => {
        dtService.getVOByPk(dt).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllDtVO ({ commit, state }) {
      return new Promise((resolve, reject) => {
        dtService.getAllVO({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryDtVO ({ commit, state }, dtQuery) {
      return new Promise((resolve, reject) => {
        dtService.getAllVO(dtQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryDtVO ({ commit, state }, { dtQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        dtService.pagingVO(dtQuery, rows, page).then(res => {
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
