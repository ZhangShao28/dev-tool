import dictValueService from '@/api/cn/moonnow/dict/dictValue/dictValueService'

export default {
  namespaced: true,
  state: {
  },
  mutations: {
  },
  actions: {
    saveDictValue ({ commit, state }, dictValue) {
      return new Promise((resolve, reject) => {
        dictValueService.save(dictValue).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchSaveDictValue ({ commit, state }, dictValues) {
      return new Promise((resolve, reject) => {
        dictValueService.batchSave(dictValues).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateDictValue ({ commit, state }, dictValue) {
      return new Promise((resolve, reject) => {
        dictValueService.update(dictValue).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchUpdateDictValue ({ commit, state }, dictValues) {
      return new Promise((resolve, reject) => {
        dictValueService.batchUpdate(dictValues).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeDictValue ({ commit, state }, dictValue) {
      return new Promise((resolve, reject) => {
        dictValueService.remove(dictValue).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchRemoveDictValue ({ commit, state }, dictValues) {
      return new Promise((resolve, reject) => {
        dictValueService.batchRemove(dictValues).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getDictValueByPk ({ commit, state }, dictValue) {
      return new Promise((resolve, reject) => {
        dictValueService.getByPk(dictValue).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllDictValue ({ commit, state }) {
      return new Promise((resolve, reject) => {
        dictValueService.getAll({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryDictValue ({ commit, state }, dictValueQuery) {
      return new Promise((resolve, reject) => {
        dictValueService.getAll(dictValueQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryDictValue ({ commit, state }, { dictValueQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        dictValueService.paging(dictValueQuery, rows, page).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getDictValueVOByPk ({ commit, state }, dictValue) {
      return new Promise((resolve, reject) => {
        dictValueService.getVOByPk(dictValue).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllDictValueVO ({ commit, state }) {
      return new Promise((resolve, reject) => {
        dictValueService.getAllVO({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryDictValueVO ({ commit, state }, dictValueQuery) {
      return new Promise((resolve, reject) => {
        dictValueService.getAllVO(dictValueQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryDictValueVO ({ commit, state }, { dictValueQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        dictValueService.pagingVO(dictValueQuery, rows, page).then(res => {
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
