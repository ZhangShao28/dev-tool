import customerService from '@/api/cn/moonnow/customer/management/customer/customerService'

export default {
  namespaced: true,
  state: {
  },
  mutations: {
  },
  actions: {
    saveCustomer ({ commit, state }, customer) {
      return new Promise((resolve, reject) => {
        customerService.save(customer).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchSaveCustomer ({ commit, state }, customers) {
      return new Promise((resolve, reject) => {
        customerService.batchSave(customers).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateCustomer ({ commit, state }, customer) {
      return new Promise((resolve, reject) => {
        customerService.update(customer).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchUpdateCustomer ({ commit, state }, customers) {
      return new Promise((resolve, reject) => {
        customerService.batchUpdate(customers).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    removeCustomer ({ commit, state }, customer) {
      return new Promise((resolve, reject) => {
        customerService.remove(customer).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchRemoveCustomer ({ commit, state }, customers) {
      return new Promise((resolve, reject) => {
        customerService.batchRemove(customers).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getCustomerByPk ({ commit, state }, customer) {
      return new Promise((resolve, reject) => {
        customerService.getByPk(customer).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllCustomer ({ commit, state }) {
      return new Promise((resolve, reject) => {
        customerService.getAll({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryCustomer ({ commit, state }, customerQuery) {
      return new Promise((resolve, reject) => {
        customerService.getAll(customerQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryCustomer ({ commit, state }, { customerQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        customerService.paging(customerQuery, rows, page).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getCustomerVOByPk ({ commit, state }, customer) {
      return new Promise((resolve, reject) => {
        customerService.getVOByPk(customer).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllCustomerVO ({ commit, state }) {
      return new Promise((resolve, reject) => {
        customerService.getAllVO({}).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    queryCustomerVO ({ commit, state }, customerQuery) {
      return new Promise((resolve, reject) => {
        customerService.getAllVO(customerQuery).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    pagingQueryCustomerVO ({ commit, state }, { customerQuery, rows, page }) {
      return new Promise((resolve, reject) => {
        customerService.pagingVO(customerQuery, rows, page).then(res => {
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
