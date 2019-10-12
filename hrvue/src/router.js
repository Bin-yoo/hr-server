import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/Login.vue'
import Home from './views/Home.vue'
import store from './store'

Vue.use(Router)

const router = new Router({
    routes: [
        {
            path: '/',
            name: 'login',
            component: Login
        },
        {
            path: '/home',
            name: 'home',
            component: Home,
            meta: {
                requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
            },
        },
    ]
})

router.beforeEach((to, from, next) => {
    if (to.meta.requireAuth) { // 判断该路由是否需要登录权限
        if (store.state.user.name == "未登录") { // 通过vuex state获取当前的token是否存在
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
        next();
    }
})

export default router;