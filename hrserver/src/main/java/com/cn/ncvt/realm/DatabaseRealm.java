package com.cn.ncvt.realm;

import com.cn.ncvt.biz.UserBiz;
import com.cn.ncvt.entity.User;
import com.cn.ncvt.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseRealm extends AuthorizingRealm {

	@Autowired
    private UserMapper userMapper;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		/*// 能进入到这里，表示账号已经通过验证了
		String userName = (String) principalCollection.getPrimaryPrincipal();
		// 通过service获取角色和权限
		Set<String> permissions = permissionService.listPermissions(userName);
		Set<String> roles = roleService.listRoleNames(userName);

		System.out.println(permissions);
		System.out.println(roles);

		// 授权对象
		SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
		// 把通过service获取到的角色和权限放进去
		s.setStringPermissions(permissions);
		s.setRoles(roles);*/
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取账号密码
		UsernamePasswordToken t = (UsernamePasswordToken) token;
		String userName = token.getPrincipal().toString();
		// 获取数据库中的密码
        User user = userMapper.selectByUserName(userName);
		if (user == null){
			return null;
		} else {
            String passwordInDB = user.getPassword();
            String salt = user.getSalt();
            // 认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
            // 盐也放进去
            // 这样通过applicationContext-shiro.xml里配置的 HashedCredentialsMatcher 进行自动校验
            SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(user, passwordInDB, ByteSource.Util.bytes(salt),getName());
            return a;
        }
	}

}