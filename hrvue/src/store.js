import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user:{
        name: window.localStorage.getItem('user' || '[]') == null ? '未登录' : JSON.parse(window.localStorage.getItem('user' || '[]')).name,
        userface: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).userface,
        username: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).username,
        // roles: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).roles
    },
    token:null,
    sessionId:null,
  },
  mutations: {
    login(state, user){
        state.user = user;
        window.localStorage.setItem('user', JSON.stringify(user));
    },
    token(state, token){
        state.token = token;
        window.localStorage.setItem('token', token);
    },
    sessionId(state, sessionId){
        state.sessionId = sessionId;
        window.localStorage.setItem('sessionId', sessionId);
    },
    logout(state){
        window.localStorage.removeItem('user');
        window.localStorage.removeItem('token');
        window.localStorage.removeItem('sessionId');
        state.user = [];
        state.token = "";
        state.sessionId = "";
    },
  },
  actions: {
    
  }
})
