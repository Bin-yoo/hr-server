import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: window.sessionStorage.getItem('user') == null ? null : JSON.parse(window.sessionStorage.getItem('user')),
    token: window.sessionStorage.getItem('token') == null ? '' : window.sessionStorage.getItem('token'),
    sessionId: window.sessionStorage.getItem('sessionId') == null ? '' : window.sessionStorage.getItem('sessionId'),
    routes: [],
  },
  mutations: {
    login(state, user){
        state.user = user;
        window.sessionStorage.setItem('user', JSON.stringify(user));
    },
    token(state, token){
        state.token = token;
        window.sessionStorage.setItem('token', token);
    },
    sessionId(state, sessionId){
        state.sessionId = sessionId;
        window.sessionStorage.setItem('sessionId', sessionId);
    },
    logout(state){
        window.sessionStorage.removeItem('user');
        window.sessionStorage.removeItem('token');
        window.sessionStorage.removeItem('sessionId');
        state.user = null;
        state.token = "";
        state.sessionId = "";
        state.routes = [];
    },
    initMenu(state, menus){
        console.log("正在添加菜单到状态")
        state.routes = menus;
    },
  },
  actions: {
    
  }
})
