import sortService from '@/api/cn/moonnow/code/sort/sortService'

export default {
  namespaced: true,
  state: {
  },
  mutations: {
  },
  actions: {
    saveSort ({ commit, state }, sort) {
      return new Promise((resolve, reject) => {
        sortService.save(sort).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchSaveSort ({ commit, state }, sorts) {
      return new Promise((resolve, reject) => {
        sortService.batchSave(sorts).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateSort ({ commit, state }, sort) {
      return new Promise((resolve, reject) => {
        sortService.update(sort).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchUpdateSort ({ commit, state }, sorts) {
      return new Promise((resolve, reject) => {
        sortService.batchUpdate(sorts).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeSort ({ commit, state }, sort) {
      return new Promise((resolve, reject) => {
        sortService.remove(sort).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchRemoveSort ({ commit, state }, sorts) {
      return new Promise((resolve, reject) => {
        sortService.batchRemove(sorts).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getSortByPk ({ commit, state }, sort) {
      return new Promise((resolve, reject) => {
        sortService.getByPk(sort).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllSort ({ commit, state }) {
      return new Promise((resolve, reject) => {
        sortService.getAll({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    querySort ({ commit, state }, sortQuery) {
      return new Promise((resolve, reject) => {
        sortService.getAll(sortQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQuerySort ({ commit, state }, { sortQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        sortService.paging(sortQuery, rows, page).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getSortVOByPk ({ commit, state }, sort) {
      return new Promise((resolve, reject) => {
        sortService.getVOByPk(sort).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllSortVO ({ commit, state }) {
      return new Promise((resolve, reject) => {
        sortService.getAllVO({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    querySortVO ({ commit, state }, sortQuery) {
      return new Promise((resolve, reject) => {
        sortService.getAllVO(sortQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQuerySortVO ({ commit, state }, { sortQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        sortService.pagingVO(sortQuery, rows, page).then(res => {
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
