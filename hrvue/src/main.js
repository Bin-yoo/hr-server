import Vue from 'vue'
import App from './App.vue'
import router from './router'
import VueRouter from 'vue-router';
import store from './store'
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
import VueAxios from 'vue-axios/dist/vue-axios.min';
import axios from 'axios/dist/axios.min';
import qs from 'qs/dist/qs';

import {postRequest} from "./utils/api";
import {putRequest} from "./utils/api";
import {deleteRequest} from "./utils/api";
import {getRequest} from "./utils/api";
Vue.prototype.getRequest = getRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.postRequest = postRequest;

Vue.use(VueRouter);
Vue.use(ViewUI);
Vue.use(VueAxios,axios);
Vue.use(qs);

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
