package com.zhcs.shiro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhcs.dao.SysMenuDao;
import com.zhcs.dao.SysUserDao;
import com.zhcs.entity.SysDepartmentEntity;
import com.zhcs.entity.SysMenuEntity;
import com.zhcs.entity.SysUserEntity;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:UserRealm</p>
 * <p>Description:认证</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年5月26日
 */
//*****************************************************************************
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysMenuDao sysMenuDao;
    
    /**

     * 授权(验证权限时调用)

     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SysUserEntity user = (SysUserEntity)principals.getPrimaryPrincipal();
		Long userId = user.getId();
		
		List<String> permsList = null;
		
		//系统管理员，拥有最高权限

		if(userId == 1){
			List<SysMenuEntity> menuList = sysMenuDao.queryList(new HashMap<String, Object>());
			permsList = new ArrayList<>(menuList.size());
			for(SysMenuEntity menu : menuList){
				permsList.add(menu.getPerms());
			}
		}else{
			permsList = sysUserDao.queryAllPerms(userId);
		}

		//用户权限列表

		Set<String> permsSet = new HashSet<String>();
		for(String perms : permsList){
			if(StringUtils.isBlank(perms)){
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}

	/**

	 * 认证(登录时调用)

	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String mobile = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        
        //查询用户信息

        SysUserEntity user = sysUserDao.queryByMobile(mobile);
        
        List<SysDepartmentEntity> depts = sysUserDao.queryDepartmentByUserId(user.getId());
        List<Long> dept = new ArrayList<Long>();
        List<String> deptText = new ArrayList<String>();
        for (SysDepartmentEntity department : depts) {
			dept.add(department.getId());
			deptText.add(department.getName());
		}
        if(StringUtil.isValid(dept)){
        	user.setDeptList(dept);
        	if(StringUtil.isValid(deptText)){
        		user.setDeptTextList(StringUtil.listToString(deptText));
        	}
        }
        //账号不存在

        if(StringUtil.isBlank(user)) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        
        //密码错误

        if(!password.equals(user.getPwd())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        
        //账号锁定

        if(user.getStatus() == 0){
        	throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
	}

}