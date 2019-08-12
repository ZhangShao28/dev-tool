import Vue from 'vue'
import Vuex from 'vuex'

import user from './module/user'
import app from './module/app'

import dtStore from './cn/moonnow/code/dt/dtStore'
import columnsStore from './cn/moonnow/code/columns/columnsStore'
import pkStore from './cn/moonnow/code/pk/pkStore'
import queryStore from './cn/moonnow/code/query/queryStore'
import sortStore from './cn/moonnow/code/sort/sortStore'
import virtualColumnsStore from './cn/moonnow/code/virtualColumns/virtualColumnsStore'
import codingStore from './cn/moonnow/code/business/coding/codingStore'
import dictStore from './cn/moonnow/dict/dict/dictStore'
import dictValueStore from './cn/moonnow/dict/dictValue/dictValueStore'
import customerStore from './cn/moonnow/customer/management/customer/customerStore'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    user,
    app
  }
})

store.registerModule('dtStore', dtStore)
store.registerModule('columnsStore', columnsStore)
store.registerModule('pkStore', pkStore)
store.registerModule('queryStore', queryStore)
store.registerModule('sortStore', sortStore)
store.registerModule('virtualColumnsStore', virtualColumnsStore)
store.registerModule('codingStore', codingStore)
store.registerModule('dictStore', dictStore)
store.registerModule('dictValueStore', dictValueStore)
store.registerModule('customerStore', customerStore)

export default store
