import loginLogService from '@/api/cn/moonnow/sign/loginLog/loginLogService'

export default {
  namespaced: true,
  state: {
  },
  mutations: {
  },
  actions: {
    saveLoginLog ({ commit, state }, loginLog) {
      return new Promise((resolve, reject) => {
        loginLogService.save(loginLog).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchSaveLoginLog ({ commit, state }, loginLogs) {
      return new Promise((resolve, reject) => {
        loginLogService.batchSave(loginLogs).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateLoginLog ({ commit, state }, loginLog) {
      return new Promise((resolve, reject) => {
        loginLogService.update(loginLog).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchUpdateLoginLog ({ commit, state }, loginLogs) {
      return new Promise((resolve, reject) => {
        loginLogService.batchUpdate(loginLogs).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeLoginLog ({ commit, state }, loginLog) {
      return new Promise((resolve, reject) => {
        loginLogService.remove(loginLog).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchRemoveLoginLog ({ commit, state }, loginLogs) {
      return new Promise((resolve, reject) => {
        loginLogService.batchRemove(loginLogs).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getLoginLogByPk ({ commit, state }, loginLog) {
      return new Promise((resolve, reject) => {
        loginLogService.getByPk(loginLog).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllLoginLog ({ commit, state }) {
      return new Promise((resolve, reject) => {
        loginLogService.getAll({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryLoginLog ({ commit, state }, loginLogQuery) {
      return new Promise((resolve, reject) => {
        loginLogService.getAll(loginLogQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryLoginLog ({ commit, state }, { loginLogQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        loginLogService.paging(loginLogQuery, rows, page).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getLoginLogVOByPk ({ commit, state }, loginLog) {
      return new Promise((resolve, reject) => {
        loginLogService.getVOByPk(loginLog).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllLoginLogVO ({ commit, state }) {
      return new Promise((resolve, reject) => {
        loginLogService.getAllVO({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryLoginLogVO ({ commit, state }, loginLogQuery) {
      return new Promise((resolve, reject) => {
        loginLogService.getAllVO(loginLogQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryLoginLogVO ({ commit, state }, { loginLogQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        loginLogService.pagingVO(loginLogQuery, rows, page).then(res => {
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
