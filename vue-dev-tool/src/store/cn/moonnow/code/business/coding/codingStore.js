import codingService from '@/api/cn/moonnow/code/business/coding/codingService'

export default {
  namespaced: true,
  state: {
  },
  mutations: {
  },
  actions: {
    extract ({ commit, state }, dt) {
      return new Promise((resolve, reject) => {
        codingService.extract(dt).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getConfig ({ commit, state }, dt) {
      return new Promise((resolve, reject) => {
        codingService.getConfig(dt).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    codingJdbcEntity ({ commit, state }, configVo) {
      return new Promise((resolve, reject) => {
        codingService.codingJdbcEntity(configVo).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    codingQuery ({ commit, state }, configVo) {
      return new Promise((resolve, reject) => {
        codingService.codingQuery(configVo).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    codingVo ({ commit, state }, configVo) {
      return new Promise((resolve, reject) => {
        codingService.codingVo(configVo).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    codingIPersistent ({ commit, state }, configVo) {
      return new Promise((resolve, reject) => {
        codingService.codingIPersistent(configVo).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    codingJdbcPersistentImpl ({ commit, state }, configVo) {
      return new Promise((resolve, reject) => {
        codingService.codingJdbcPersistentImpl(configVo).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    codingIService ({ commit, state }, configVo) {
      return new Promise((resolve, reject) => {
        codingService.codingIService(configVo).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    codingServiceImpl ({ commit, state }, configVo) {
      return new Promise((resolve, reject) => {
        codingService.codingServiceImpl(configVo).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    codingController ({ commit, state }, configVo) {
      return new Promise((resolve, reject) => {
        codingService.codingController(configVo).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    codingAll ({ commit, state }, configVo) {
      return new Promise((resolve, reject) => {
        codingService.codingAll(configVo).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    codingProject ({ commit, state }, configVo) {
      return new Promise((resolve, reject) => {
        codingService.codingProject(configVo).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    codingVueService ({ commit, state }, configVo) {
      return new Promise((resolve, reject) => {
        codingService.codingVueService(configVo).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    codingVueStore ({ commit, state }, configVo) {
      return new Promise((resolve, reject) => {
        codingService.codingVueStore(configVo).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    codingVueEntity ({ commit, state }, configVo) {
      return new Promise((resolve, reject) => {
        codingService.codingVueEntity(configVo).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    codingVueQuery ({ commit, state }, configVo) {
      return new Promise((resolve, reject) => {
        codingService.codingVueQuery(configVo).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    codingVueVo ({ commit, state }, configVo) {
      return new Promise((resolve, reject) => {
        codingService.codingVueVo(configVo).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    codingVueView ({ commit, state }, configVo) {
      return new Promise((resolve, reject) => {
        codingService.codingVueView(configVo).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    codingVueViewCss ({ commit, state }, configVo) {
      return new Promise((resolve, reject) => {
        codingService.codingVueViewCss(configVo).then(res => {
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
