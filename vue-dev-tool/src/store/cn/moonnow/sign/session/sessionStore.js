import sessionService from '@/api/cn/moonnow/sign/session/sessionService'

export default {
  namespaced: true,
  state: {
  },
  mutations: {
  },
  actions: {
    saveSession ({ commit, state }, session) {
      return new Promise((resolve, reject) => {
        sessionService.save(session).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchSaveSession ({ commit, state }, sessions) {
      return new Promise((resolve, reject) => {
        sessionService.batchSave(sessions).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateSession ({ commit, state }, session) {
      return new Promise((resolve, reject) => {
        sessionService.update(session).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchUpdateSession ({ commit, state }, sessions) {
      return new Promise((resolve, reject) => {
        sessionService.batchUpdate(sessions).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeSession ({ commit, state }, session) {
      return new Promise((resolve, reject) => {
        sessionService.remove(session).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchRemoveSession ({ commit, state }, sessions) {
      return new Promise((resolve, reject) => {
        sessionService.batchRemove(sessions).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getSessionByPk ({ commit, state }, session) {
      return new Promise((resolve, reject) => {
        sessionService.getByPk(session).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllSession ({ commit, state }) {
      return new Promise((resolve, reject) => {
        sessionService.getAll({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    querySession ({ commit, state }, sessionQuery) {
      return new Promise((resolve, reject) => {
        sessionService.getAll(sessionQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQuerySession ({ commit, state }, { sessionQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        sessionService.paging(sessionQuery, rows, page).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getSessionVOByPk ({ commit, state }, session) {
      return new Promise((resolve, reject) => {
        sessionService.getVOByPk(session).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllSessionVO ({ commit, state }) {
      return new Promise((resolve, reject) => {
        sessionService.getAllVO({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    querySessionVO ({ commit, state }, sessionQuery) {
      return new Promise((resolve, reject) => {
        sessionService.getAllVO(sessionQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQuerySessionVO ({ commit, state }, { sessionQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        sessionService.pagingVO(sessionQuery, rows, page).then(res => {
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
