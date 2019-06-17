import Vue from 'vue'
import Vuex from 'vuex'

import user from './module/user'
import app from './module/app'

import dtStore from './cn/moonnow/code/dt/dtStore'
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
store.registerModule('usrStore', usrStore)
store.registerModule('dictStore', dictStore)
store.registerModule('dictValueStore', dictValueStore)

export default store
