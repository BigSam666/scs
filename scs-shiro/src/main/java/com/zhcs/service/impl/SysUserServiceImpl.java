package com.zhcs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhcs.context.PlatformContext;
import com.zhcs.dao.SysDepartmentDao;
import com.zhcs.dao.SysUserDao;
import com.zhcs.entity.SysDepartmentEntity;
import com.zhcs.entity.SysRoleEntity;
import com.zhcs.entity.SysUserEntity;
import com.zhcs.service.SysUserDeptService;
import com.zhcs.service.SysUserRoleService;
import com.zhcs.service.SysUserService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.ShiroUtils;
import com.zhcs.utils.StringUtil;
import com.zhcs.utils.strRandom.RandomStr;

/**
 * 系统用户
 * 
 * @author Alter
 * @email Alter@Alter.com
 * @date 2016年9月18日 上午9:46:09
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysUserDeptService sysUserDeptServic;
	@Autowired
	private SysDepartmentDao sysDepartmentDao;

	@Override
	public List<String> queryAllPerms(Long id) {
		return sysUserDao.queryAllPerms(id);
	}

	@Override
	public List<Long> queryAllMenuId(Long id) {
		return sysUserDao.queryAllMenuId(id);
	}

	@Override
	public SysUserEntity queryByUserName(String username) {
		return sysUserDao.queryByUserName(username);
	}
	
	@Override
	public SysUserEntity queryByOpenId(String openId) {
		return sysUserDao.queryByOpenId(openId);
	}
	
	@Override
	public SysUserEntity queryByMobile(String mobile) {
		return sysUserDao.queryByMobile(mobile);
	}
	
	@Override
	@Cacheable(value="menuCache", key="'User'+#id")  
	public SysUserEntity queryObject(Long id) {
		return sysUserDao.queryObject(id);
	}

	@Override
	public List<SysUserEntity> queryList(Map<String, Object> map){
		return sysUserDao.queryList(map);
	}

	@Override
	public List<Map<String, Object>> getUserListByMsg(){
		return sysUserDao.getUserListByMsg();
	}
	
	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysUserDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(SysUserEntity user) {
		//sha256加密
		user.setPwd(new Sha256Hash(user.getPwd() + user.getSalt()).toHex());
		sysUserDao.save(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getId(), user.getRoleIdList());
		
		//保存用户与部门关系
		sysUserDeptServic.saveOrUpdate(user.getId(), user.getDeptList());
	}

	@Override
	@Transactional
	public void update(SysUserEntity user) {
		if(StringUtils.isBlank(user.getPwd())){
			user.setPwd(null);
		}else{
			String salt = RandomStr.randomStr(Integer.parseInt(PlatformContext.getGoalbalContext("saltLength", String.class)));
			user.setSalt(salt);
			user.setPwd(new Sha256Hash(user.getPwd() + salt).toHex());
		}
		sysUserDao.update(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getId(), user.getRoleIdList());
		
		//保存用户与部门关系
		sysUserDeptServic.saveOrUpdate(user.getId(), user.getDeptList());
		
	}
	
	@Override
	@Transactional
	public void updateUser(SysUserEntity user) {
		sysUserDao.update(user);
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] userIds) {
		sysUserDao.deleteBatch(userIds);
		//删除用户与角色关系
		sysUserRoleService.deleteBatch(userIds);
		//删除用户与部门关系
		sysUserDeptServic.deleteBatch(userIds);
	}

	@Override
	@Transactional
	public int updatePassword(Long id, String password, String newPassword, String salt) {
		Map<String, Object> map = new HashMap<String, Object>();
		BeanUtil.fillCCUUD(map, ShiroUtils.getUserId());
		map.put("userId", id);
		map.put("password", password);
		map.put("newPassword", newPassword);
		map.put("salt", salt);
		return sysUserDao.updatePassword(map);
	}

	@Override
	public List<Map<String, Object>> queryListTree() {
		return sysUserDao.queryListTree();
	}

	@Override
	public List<SysDepartmentEntity> queryDepartmentByUser(Long userId) {
		return sysUserDao.queryDepartmentByUserId(userId);
	}

	@Override
	public List<SysDepartmentEntity> getUserProject(Long userId) {
		String projectOtype = PlatformContext.getGoalbalContext("projectOtype", String.class);
		return getUserDept(userId, projectOtype);
	}

	@Override
	public List<SysDepartmentEntity> getUserInstitution(Long userId) {
		String projectOtype = PlatformContext.getGoalbalContext("institutionOtype", String.class);
		return getUserDept(userId, projectOtype);
	}
	
	/**
	 * 获取用户的指定层次的部门，比如获取用户所属项目部、所属事业部等等
	 * 
	 * @param targetOtype 目标组织类型
	 */
	private List<SysDepartmentEntity> getUserDept(Long userId, String targetOtype){
		List<SysDepartmentEntity> ps = sysUserDao.queryDepartmentByUserId(userId);
		List<SysDepartmentEntity> ret = new ArrayList<>();
		
		String companyOtype = PlatformContext.getGoalbalContext("companyOtype", String.class);
		for (SysDepartmentEntity project: ps) {
			while (!project.getOtype().equals(targetOtype.trim()) && !project.getOtype().equals(companyOtype.trim())) {
				project = sysDepartmentDao.queryObject(project.getPid());
			}
			int temp = 0;
			if (project.getOtype().equals(targetOtype.trim())) {
				for (SysDepartmentEntity sysDepartmentEntity : ret) {
					if(sysDepartmentEntity.getId().equals(project.getId())){
						temp ++;
					}
				}
				if(temp == 0){
					ret.add(project);
				}
			}
		}
		return ret;
	}

	@Override
	public List<SysUserEntity> queryUsersBydepts(List<Long> depts) {
		if(StringUtil.isBlank(depts)){
			return new ArrayList<SysUserEntity>();
		} else {
			return sysUserDao.queryUsersBydepts(depts);
		}
	}

	@Override
	public List<SysRoleEntity> queryRoleByUser(Long id) {
		return sysUserDao.queryRoleByUser(id);
	}

}
