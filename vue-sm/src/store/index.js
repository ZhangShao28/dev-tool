import Vue from 'vue'
import Vuex from 'vuex'

import user from './module/user'
import app from './module/app'

import usrStore from './cn/moonnow/sign/usr/usrStore'

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

store.registerModule('usrStore', usrStore)

export default store
