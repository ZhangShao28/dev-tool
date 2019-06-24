import virtualColumnsService from '@/api/cn/moonnow/code/virtualColumns/virtualColumnsService'

export default {
  namespaced: true,
  state: {
  },
  mutations: {
  },
  actions: {
    saveVirtualColumns ({ commit, state }, virtualColumns) {
      return new Promise((resolve, reject) => {
        virtualColumnsService.save(virtualColumns).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchSaveVirtualColumns ({ commit, state }, virtualColumnss) {
      return new Promise((resolve, reject) => {
        virtualColumnsService.batchSave(virtualColumnss).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateVirtualColumns ({ commit, state }, virtualColumns) {
      return new Promise((resolve, reject) => {
        virtualColumnsService.update(virtualColumns).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchUpdateVirtualColumns ({ commit, state }, virtualColumnss) {
      return new Promise((resolve, reject) => {
        virtualColumnsService.batchUpdate(virtualColumnss).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeVirtualColumns ({ commit, state }, virtualColumns) {
      return new Promise((resolve, reject) => {
        virtualColumnsService.remove(virtualColumns).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchRemoveVirtualColumns ({ commit, state }, virtualColumnss) {
      return new Promise((resolve, reject) => {
        virtualColumnsService.batchRemove(virtualColumnss).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getVirtualColumnsByPk ({ commit, state }, virtualColumns) {
      return new Promise((resolve, reject) => {
        virtualColumnsService.getByPk(virtualColumns).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllVirtualColumns ({ commit, state }) {
      return new Promise((resolve, reject) => {
        virtualColumnsService.getAll({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryVirtualColumns ({ commit, state }, virtualColumnsQuery) {
      return new Promise((resolve, reject) => {
        virtualColumnsService.getAll(virtualColumnsQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryVirtualColumns ({ commit, state }, { virtualColumnsQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        virtualColumnsService.paging(virtualColumnsQuery, rows, page).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getVirtualColumnsVOByPk ({ commit, state }, virtualColumns) {
      return new Promise((resolve, reject) => {
        virtualColumnsService.getVOByPk(virtualColumns).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllVirtualColumnsVO ({ commit, state }) {
      return new Promise((resolve, reject) => {
        virtualColumnsService.getAllVO({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryVirtualColumnsVO ({ commit, state }, virtualColumnsQuery) {
      return new Promise((resolve, reject) => {
        virtualColumnsService.getAllVO(virtualColumnsQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryVirtualColumnsVO ({ commit, state }, { virtualColumnsQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        virtualColumnsService.pagingVO(virtualColumnsQuery, rows, page).then(res => {
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
