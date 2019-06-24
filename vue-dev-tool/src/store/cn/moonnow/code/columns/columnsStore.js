import columnsService from '@/api/cn/moonnow/code/columns/columnsService'

export default {
  namespaced: true,
  state: {
  },
  mutations: {
  },
  actions: {
    saveColumns ({ commit, state }, columns) {
      return new Promise((resolve, reject) => {
        columnsService.save(columns).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchSaveColumns ({ commit, state }, columnss) {
      return new Promise((resolve, reject) => {
        columnsService.batchSave(columnss).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateColumns ({ commit, state }, columns) {
      return new Promise((resolve, reject) => {
        columnsService.update(columns).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchUpdateColumns ({ commit, state }, columnss) {
      return new Promise((resolve, reject) => {
        columnsService.batchUpdate(columnss).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeColumns ({ commit, state }, columns) {
      return new Promise((resolve, reject) => {
        columnsService.remove(columns).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchRemoveColumns ({ commit, state }, columnss) {
      return new Promise((resolve, reject) => {
        columnsService.batchRemove(columnss).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getColumnsByPk ({ commit, state }, columns) {
      return new Promise((resolve, reject) => {
        columnsService.getByPk(columns).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllColumns ({ commit, state }) {
      return new Promise((resolve, reject) => {
        columnsService.getAll({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryColumns ({ commit, state }, columnsQuery) {
      return new Promise((resolve, reject) => {
        columnsService.getAll(columnsQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryColumns ({ commit, state }, { columnsQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        columnsService.paging(columnsQuery, rows, page).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getColumnsVOByPk ({ commit, state }, columns) {
      return new Promise((resolve, reject) => {
        columnsService.getVOByPk(columns).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllColumnsVO ({ commit, state }) {
      return new Promise((resolve, reject) => {
        columnsService.getAllVO({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryColumnsVO ({ commit, state }, columnsQuery) {
      return new Promise((resolve, reject) => {
        columnsService.getAllVO(columnsQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryColumnsVO ({ commit, state }, { columnsQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        columnsService.pagingVO(columnsQuery, rows, page).then(res => {
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
