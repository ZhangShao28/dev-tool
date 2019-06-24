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
import usrStore from './cn/moonnow/sign/usr/usrStore'
import dictStore from './cn/moonnow/dict/dict/dictStore'
import dictValueStore from './cn/moonnow/dict/dictValue/dictValueStore'

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
store.registerModule('usrStore', usrStore)
store.registerModule('dictStore', dictStore)
store.registerModule('dictValueStore', dictValueStore)

export default store
