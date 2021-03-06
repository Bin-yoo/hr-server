import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/Login.vue'
import Home from './views/Home.vue'
import store from './store'
import {initMenu} from './utils/utils'
import Welcome from './components/Welcome'

Vue.use(Router)

const router = new Router({
    routes: [
        {
            path: '/',
            name: 'login',
            component: Login,
            meta: {
                title: '人事管理系统'
            },
        },
        {
            path: '/home',
            // name: 'home',
            component: Home,
            meta: {
                requireAuth: true,
            },
            children: [
                {
                    path: '',
                    component: Welcome,
                    meta: {
                        requireAuth: true,
                    },
                },
            ]
        },
    ]
})

router.beforeEach((to, from, next) => {
    if (to.meta.title) {
        document.title = to.meta.title
    }
    if (to.name == 'login') {
        next();
        return;
    }
    // console.log(store.state.user);
    // console.log(to.meta);
    if (store.state.user == null) { // 通过vuex state获取当前的token是否存在
        // console.log("user为空");
        if (to.meta.requireAuth) { // 判断该路由是否需要登录权限
            // console.log("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            next({
                path: '/',
                query: {
                    redirect: to.fullPath
                } // 将跳转的路由path作为参数，登录成功后跳转到该路由
            })
        } else {
            next();
        }
    } else {
        initMenu(router, store);
        next();
    }
})

export default router;