import accountGrpNexusService from '@/api/cn/moonnow/sign/accountGrpNexus/accountGrpNexusService'

export default {
  namespaced: true,
  state: {
  },
  mutations: {
  },
  actions: {
    saveAccountGrpNexus ({ commit, state }, accountGrpNexus) {
      return new Promise((resolve, reject) => {
        accountGrpNexusService.save(accountGrpNexus).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchSaveAccountGrpNexus ({ commit, state }, accountGrpNexuss) {
      return new Promise((resolve, reject) => {
        accountGrpNexusService.batchSave(accountGrpNexuss).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateAccountGrpNexus ({ commit, state }, accountGrpNexus) {
      return new Promise((resolve, reject) => {
        accountGrpNexusService.update(accountGrpNexus).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchUpdateAccountGrpNexus ({ commit, state }, accountGrpNexuss) {
      return new Promise((resolve, reject) => {
        accountGrpNexusService.batchUpdate(accountGrpNexuss).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeAccountGrpNexus ({ commit, state }, accountGrpNexus) {
      return new Promise((resolve, reject) => {
        accountGrpNexusService.remove(accountGrpNexus).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchRemoveAccountGrpNexus ({ commit, state }, accountGrpNexuss) {
      return new Promise((resolve, reject) => {
        accountGrpNexusService.batchRemove(accountGrpNexuss).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAccountGrpNexusByPk ({ commit, state }, accountGrpNexus) {
      return new Promise((resolve, reject) => {
        accountGrpNexusService.getByPk(accountGrpNexus).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllAccountGrpNexus ({ commit, state }) {
      return new Promise((resolve, reject) => {
        accountGrpNexusService.getAll({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryAccountGrpNexus ({ commit, state }, accountGrpNexusQuery) {
      return new Promise((resolve, reject) => {
        accountGrpNexusService.getAll(accountGrpNexusQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryAccountGrpNexus ({ commit, state }, { accountGrpNexusQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        accountGrpNexusService.paging(accountGrpNexusQuery, rows, page).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAccountGrpNexusVOByPk ({ commit, state }, accountGrpNexus) {
      return new Promise((resolve, reject) => {
        accountGrpNexusService.getVOByPk(accountGrpNexus).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllAccountGrpNexusVO ({ commit, state }) {
      return new Promise((resolve, reject) => {
        accountGrpNexusService.getAllVO({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryAccountGrpNexusVO ({ commit, state }, accountGrpNexusQuery) {
      return new Promise((resolve, reject) => {
        accountGrpNexusService.getAllVO(accountGrpNexusQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryAccountGrpNexusVO ({ commit, state }, { accountGrpNexusQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        accountGrpNexusService.pagingVO(accountGrpNexusQuery, rows, page).then(res => {
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
