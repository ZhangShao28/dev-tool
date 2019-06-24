import queryService from '@/api/cn/moonnow/code/query/queryService'

export default {
  namespaced: true,
  state: {
  },
  mutations: {
  },
  actions: {
    saveQuery ({ commit, state }, query) {
      return new Promise((resolve, reject) => {
        queryService.save(query).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchSaveQuery ({ commit, state }, querys) {
      return new Promise((resolve, reject) => {
        queryService.batchSave(querys).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateQuery ({ commit, state }, query) {
      return new Promise((resolve, reject) => {
        queryService.update(query).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchUpdateQuery ({ commit, state }, querys) {
      return new Promise((resolve, reject) => {
        queryService.batchUpdate(querys).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeQuery ({ commit, state }, query) {
      return new Promise((resolve, reject) => {
        queryService.remove(query).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchRemoveQuery ({ commit, state }, querys) {
      return new Promise((resolve, reject) => {
        queryService.batchRemove(querys).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getQueryByPk ({ commit, state }, query) {
      return new Promise((resolve, reject) => {
        queryService.getByPk(query).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllQuery ({ commit, state }) {
      return new Promise((resolve, reject) => {
        queryService.getAll({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryQuery ({ commit, state }, queryQuery) {
      return new Promise((resolve, reject) => {
        queryService.getAll(queryQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryQuery ({ commit, state }, { queryQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        queryService.paging(queryQuery, rows, page).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getQueryVOByPk ({ commit, state }, query) {
      return new Promise((resolve, reject) => {
        queryService.getVOByPk(query).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllQueryVO ({ commit, state }) {
      return new Promise((resolve, reject) => {
        queryService.getAllVO({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryQueryVO ({ commit, state }, queryQuery) {
      return new Promise((resolve, reject) => {
        queryService.getAllVO(queryQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryQueryVO ({ commit, state }, { queryQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        queryService.pagingVO(queryQuery, rows, page).then(res => {
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
