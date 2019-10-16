import axios from 'axios'
import {Message} from 'view-design'
import store from '../store'

axios.interceptors.request.use(config => {
  return config;
}, err => {
  Message.error({message: '请求超时!'});
  // return Promise.resolve(err);
})
axios.interceptors.response.use(data => {
  if (data.status && data.status == 200 && data.data.status == 500) {
    Message.error({message: data.data.message});
    return;
  }
  if (data.data.msg) {
    Message.success({message: data.data.message});
  }
  return data;
}, err => {
  if (err.response.status == 504 || err.response.status == 404) {
    Message.error({message: '服务器被吃了⊙﹏⊙∥'});
  } else if (err.response.status == 403) {
    Message.error({message: '权限不足,请联系管理员!'});
  } else if (err.response.status == 401) {
    Message.error({message: err.response.data.message});
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
export const postRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    headers: {
        'token': store.state.token,
        'sessionId': store.state.sessionId,
    }
  });
}
export const uploadFileRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
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
    }
  });
}
export const deleteRequest = (url) => {
  return axios({
    method: 'delete',
    url: `${base}${url}`,
    headers: {
        'token': store.state.token,
        'sessionId': store.state.sessionId,
    }
  });
}
export const getRequest = (url, params) => {
  return axios({
    method: 'get',
    url: `${base}${url}`,
    data: params,
    headers: {
        'token': store.state.token,
        'sessionId': store.state.sessionId,
    }
  });
}
