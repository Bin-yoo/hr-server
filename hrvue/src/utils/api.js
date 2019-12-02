import axios from 'axios'
import {Message} from 'iview'
import store from '../store'
import Router from '../router'


axios.interceptors.request.use(config => {
  return config;
}, err => {
  Message.error({message: '请求超时!'});
  // return Promise.resolve(err);
})
axios.interceptors.response.use(resp => {
  if (resp.data.code == 401) {
    Message.error(resp.data.message);
    store.commit("logout");
    Router.replace({name: 'login'})
    // return;
  }
  if (resp.data.code == 403) {
    Message.error(resp.data.message);
    // return;
  }
  return resp;
}, err => {
  if (err.response.status == 504 || err.response.status == 404) {
    Message.error({message: '服务器被吃了⊙﹏⊙∥'});
  } else if (err.response.status == 403) {
    Message.error({message: '权限不足,请联系管理员!'});
  } else if (err.response.status == 401) {
    Message.error({message: 'err.response.data.message'});
  } else {
    if (err.response.data.msg) {
      Message.error({message: err.response.data.message});
    }else{
      Message.error({message: '未知错误!'});
    }
  }
  // return Promise.resolve(err);
})
let base = 'http://localhost:8080/hrserver';
//let base = 'http://111.230.141.100:8080/hrserver';
export const postRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    headers: {
        'token': store.state.token,
        'sessionId': store.state.sessionId,
    },
  });
}
export const uploadFileRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    headers: {
      'Content-Type': 'multipart/form-data'
    },
  });
}
export const putRequest = (url, params) => {
  return axios({
    method: 'put',
    url: `${base}${url}`,
    data: params,
    headers: {
        'token': store.state.token,
        'sessionId': store.state.sessionId,
    },
  });
}
export const deleteRequest = (url) => {
  return axios({
    method: 'delete',
    url: `${base}${url}`,
    headers: {
        'token': store.state.token,
        'sessionId': store.state.sessionId,
    },
  });
}
export const getRequest = (url, params) => {
  return axios({
    method: 'get',
    url: `${base}${url}`,
    params: params,
    headers: {
        'token': store.state.token,
        'sessionId': store.state.sessionId,
    },
  });
}
