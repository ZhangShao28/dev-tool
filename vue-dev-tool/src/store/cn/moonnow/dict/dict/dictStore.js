import dictService from '@/api/cn/moonnow/dict/dict/dictService'

export default {
  namespaced: true,
  state: {
  },
  mutations: {
  },
  actions: {
    saveDict ({ commit, state }, dict) {
      return new Promise((resolve, reject) => {
        dictService.save(dict).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchSaveDict ({ commit, state }, dicts) {
      return new Promise((resolve, reject) => {
        dictService.batchSave(dicts).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateDict ({ commit, state }, dict) {
      return new Promise((resolve, reject) => {
        dictService.update(dict).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchUpdateDict ({ commit, state }, dicts) {
      return new Promise((resolve, reject) => {
        dictService.batchUpdate(dicts).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeDict ({ commit, state }, dict) {
      return new Promise((resolve, reject) => {
        dictService.remove(dict).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchRemoveDict ({ commit, state }, dicts) {
      return new Promise((resolve, reject) => {
        dictService.batchRemove(dicts).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getDictByPk ({ commit, state }, dict) {
      return new Promise((resolve, reject) => {
        dictService.getByPk(dict).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllDict ({ commit, state }) {
      return new Promise((resolve, reject) => {
        dictService.getAll({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryDict ({ commit, state }, dictQuery) {
      return new Promise((resolve, reject) => {
        dictService.getAll(dictQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryDict ({ commit, state }, { dictQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        dictService.paging(dictQuery, rows, page).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getDictVOByPk ({ commit, state }, dict) {
      return new Promise((resolve, reject) => {
        dictService.getVOByPk(dict).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllDictVO ({ commit, state }) {
      return new Promise((resolve, reject) => {
        dictService.getAllVO({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryDictVO ({ commit, state }, dictQuery) {
      return new Promise((resolve, reject) => {
        dictService.getAllVO(dictQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryDictVO ({ commit, state }, { dictQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        dictService.pagingVO(dictQuery, rows, page).then(res => {
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
