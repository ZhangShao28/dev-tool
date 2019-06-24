import pkService from '@/api/cn/moonnow/code/pk/pkService'

export default {
  namespaced: true,
  state: {
  },
  mutations: {
  },
  actions: {
    savePk ({ commit, state }, pk) {
      return new Promise((resolve, reject) => {
        pkService.save(pk).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchSavePk ({ commit, state }, pks) {
      return new Promise((resolve, reject) => {
        pkService.batchSave(pks).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updatePk ({ commit, state }, pk) {
      return new Promise((resolve, reject) => {
        pkService.update(pk).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchUpdatePk ({ commit, state }, pks) {
      return new Promise((resolve, reject) => {
        pkService.batchUpdate(pks).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removePk ({ commit, state }, pk) {
      return new Promise((resolve, reject) => {
        pkService.remove(pk).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchRemovePk ({ commit, state }, pks) {
      return new Promise((resolve, reject) => {
        pkService.batchRemove(pks).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getPkByPk ({ commit, state }, pk) {
      return new Promise((resolve, reject) => {
        pkService.getByPk(pk).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllPk ({ commit, state }) {
      return new Promise((resolve, reject) => {
        pkService.getAll({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryPk ({ commit, state }, pkQuery) {
      return new Promise((resolve, reject) => {
        pkService.getAll(pkQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryPk ({ commit, state }, { pkQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        pkService.paging(pkQuery, rows, page).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getPkVOByPk ({ commit, state }, pk) {
      return new Promise((resolve, reject) => {
        pkService.getVOByPk(pk).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllPkVO ({ commit, state }) {
      return new Promise((resolve, reject) => {
        pkService.getAllVO({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryPkVO ({ commit, state }, pkQuery) {
      return new Promise((resolve, reject) => {
        pkService.getAllVO(pkQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryPkVO ({ commit, state }, { pkQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        pkService.pagingVO(pkQuery, rows, page).then(res => {
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
