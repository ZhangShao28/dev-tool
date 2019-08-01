import grpService from '@/api/cn/moonnow/sign/grp/grpService'

export default {
  namespaced: true,
  state: {
  },
  mutations: {
  },
  actions: {
    saveGrp ({ commit, state }, grp) {
      return new Promise((resolve, reject) => {
        grpService.save(grp).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchSaveGrp ({ commit, state }, grps) {
      return new Promise((resolve, reject) => {
        grpService.batchSave(grps).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateGrp ({ commit, state }, grp) {
      return new Promise((resolve, reject) => {
        grpService.update(grp).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchUpdateGrp ({ commit, state }, grps) {
      return new Promise((resolve, reject) => {
        grpService.batchUpdate(grps).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeGrp ({ commit, state }, grp) {
      return new Promise((resolve, reject) => {
        grpService.remove(grp).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchRemoveGrp ({ commit, state }, grps) {
      return new Promise((resolve, reject) => {
        grpService.batchRemove(grps).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getGrpByPk ({ commit, state }, grp) {
      return new Promise((resolve, reject) => {
        grpService.getByPk(grp).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllGrp ({ commit, state }) {
      return new Promise((resolve, reject) => {
        grpService.getAll({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryGrp ({ commit, state }, grpQuery) {
      return new Promise((resolve, reject) => {
        grpService.getAll(grpQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryGrp ({ commit, state }, { grpQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        grpService.paging(grpQuery, rows, page).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getGrpVOByPk ({ commit, state }, grp) {
      return new Promise((resolve, reject) => {
        grpService.getVOByPk(grp).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllGrpVO ({ commit, state }) {
      return new Promise((resolve, reject) => {
        grpService.getAllVO({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryGrpVO ({ commit, state }, grpQuery) {
      return new Promise((resolve, reject) => {
        grpService.getAllVO(grpQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryGrpVO ({ commit, state }, { grpQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        grpService.pagingVO(grpQuery, rows, page).then(res => {
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
