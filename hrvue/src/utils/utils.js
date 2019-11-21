import {getRequest} from './api'

export const isNotNullORBlank = (...args)=> {
  for (var i = 0; i < args.length; i++) {
    var argument = args[i];
    if (argument == null || argument == '' || argument == undefined) {
    //   Message.warning({message: '数据不能为空!'})
      return false;
    }
  }
  return true;
}

export const initMenu = (router, store)=> {
  if (store.state.routes.length > 0) {
    return;
  }
  getRequest("/sysmenu").then(resp=> {
      var fmtRoutes = formatRoutes(resp.data.data);
      console.log(fmtRoutes);
      console.log(resp.data.data);
      router.addRoutes(fmtRoutes);
      store.commit('initMenu', fmtRoutes);
  })
}
export const formatRoutes = (routes)=> {
  let fmRoutes = [];
  routes.forEach(router=> {
    let {
      path,
      component,
      name,
      meta,
      iconCls,
      children
    } = router;
    if (children && children instanceof Array) {
      children = formatRoutes(children);
    }
    let fmRouter = {
      path: path,
      component(resolve){
        if (path.startsWith("/home")) {
          require(['../views/' + component + '.vue'], resolve)
        } else if (path.startsWith("/employee")) {
          require(['../components/emp/' + component + '.vue'], resolve)
        } else if (path.startsWith("/per")) {
          require(['../components/personnel/' + component + '.vue'], resolve)
        } else if (path.startsWith("/statistics")) {
          require(['../components/statistics/' + component + '.vue'], resolve)
        } else if (path.startsWith("/system")) {
          require(['../components/system/' + component + '.vue'], resolve)
        }
      },
      name: name,
      iconCls: iconCls,
      meta: meta,
      children: children
    };
    fmRoutes.push(fmRouter);
  })
  return fmRoutes;
}
