import accountService from '@/api/cn/moonnow/sign/account/accountService'

export default {
  namespaced: true,
  state: {
  },
  mutations: {
  },
  actions: {
    saveAccount ({ commit, state }, account) {
      return new Promise((resolve, reject) => {
        accountService.save(account).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchSaveAccount ({ commit, state }, accounts) {
      return new Promise((resolve, reject) => {
        accountService.batchSave(accounts).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateAccount ({ commit, state }, account) {
      return new Promise((resolve, reject) => {
        accountService.update(account).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchUpdateAccount ({ commit, state }, accounts) {
      return new Promise((resolve, reject) => {
        accountService.batchUpdate(accounts).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeAccount ({ commit, state }, account) {
      return new Promise((resolve, reject) => {
        accountService.remove(account).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchRemoveAccount ({ commit, state }, accounts) {
      return new Promise((resolve, reject) => {
        accountService.batchRemove(accounts).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAccountByPk ({ commit, state }, account) {
      return new Promise((resolve, reject) => {
        accountService.getByPk(account).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllAccount ({ commit, state }) {
      return new Promise((resolve, reject) => {
        accountService.getAll({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryAccount ({ commit, state }, accountQuery) {
      return new Promise((resolve, reject) => {
        accountService.getAll(accountQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryAccount ({ commit, state }, { accountQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        accountService.paging(accountQuery, rows, page).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAccountVOByPk ({ commit, state }, account) {
      return new Promise((resolve, reject) => {
        accountService.getVOByPk(account).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllAccountVO ({ commit, state }) {
      return new Promise((resolve, reject) => {
        accountService.getAllVO({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryAccountVO ({ commit, state }, accountQuery) {
      return new Promise((resolve, reject) => {
        accountService.getAllVO(accountQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryAccountVO ({ commit, state }, { accountQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        accountService.pagingVO(accountQuery, rows, page).then(res => {
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
