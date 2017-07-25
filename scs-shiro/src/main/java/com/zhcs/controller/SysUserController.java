package com.zhcs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.zhcs.context.PlatformContext;
import com.zhcs.entity.SysDepartmentEntity;
import com.zhcs.entity.SysUserEntity;
import com.zhcs.service.SysUserDeptService;
import com.zhcs.service.SysUserRoleService;
import com.zhcs.service.SysUserService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.LogUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.ShiroUtils;
import com.zhcs.utils.StringUtil;
import com.zhcs.utils.strRandom.RandomStr;

/**
 *                    _ooOoo_
 *                   o8888888o
 *                   88" . "88
 *                   (| -_- |)
 *                    O\ = /O
 *                ____/`---'\____
 *              .   ' \\| |// `.
 *               / \\||| : |||// \
 *             / _||||| -:- |||||- \
 *               | | \\\ - /// | |
 *             | \_| ''\---/'' | |
 *              \ .-\__ `-` ___/-. /
 *           ___`. .' /--.--\ `. . __
 *        ."" '< `.___\_<|>_/___.' >'"".
 *       | | : `- \`.;`\ _ /`;.`/ - ` : | |
 *         \ \ `-. \_ __\ /__ _/ .-` / /
 * ======`-.____`-.___\_____/___.-`____.-'======
 *                    `=---='
 *
 * .............................................
 *          佛祖保佑             永无BUG
 */

//*****************************************************************************
/**
 * <p>Title:SysUserController</p>
 * <p>Description:系统用户</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysUserDeptService sysUserDeptService;
	
	/**
	 * 所有用户列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:user:list")
	public R list(String name, String sidx, String order, Integer page, Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		// 查询列表数据
		List<SysUserEntity> userList = sysUserService.queryList(map);
		int total = sysUserService.queryTotal(map);

		PageUtils pageUtil = new PageUtils(userList, total, limit, page);

		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 用于获取接收人列表
	 */
	@ResponseBody
	@RequestMapping("/getUserListByMsg")
	@RequiresPermissions("sys:user:getUserListByMsg")
	public R getUserListByMsg(){
		List<Map<String, Object>> userList = sysUserService.getUserListByMsg();
		
		return R.ok().putData(userList);
	}
	

	/**
	 * 获取登录的用户信息
	 */
	@RequestMapping("/info")
	public R info() {
		return R.ok().put("user", getUser());
	}

	/**
	 * 修改登录用户密码
	 */
	@RequestMapping("/password")
	public R password(String password, String newPassword) {
		if (StringUtils.isBlank(newPassword)) {
			return R.error("新密码不为能空");
		}
		Long userId = getUserId();
		SysUserEntity user = sysUserService.queryObject(userId);
		
		String salt = RandomStr.randomStr(Integer.parseInt(PlatformContext.getGoalbalContext("saltLength", String.class)));

		// sha256加密
		password = new Sha256Hash(password + user.getSalt()).toHex();
		// sha256加密
		newPassword = new Sha256Hash(newPassword + salt).toHex();

		// 更新密码
		int count = sysUserService.updatePassword(userId, password, newPassword, salt);
		if (count == 0) {
			return R.error("原密码不正确");
		}

		// 退出
		ShiroUtils.logout();

		return R.ok();
	}

	/**
	 * 用户信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:user:info")
	public R info(@PathVariable("id") Long id) {
		SysUserEntity user = sysUserService.queryObject(id);

		// 获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(id);
		user.setRoleIdList(roleIdList);
		if(StringUtil.isValid(roleIdList)){
			// 将角色id改为中文名称
			String roleTextList = sysUserRoleService.queryRoleText(roleIdList);
			user.setRoleTextList(roleTextList);
		}
		
		// 获取用户所属部门列表
		List<Long> deptList = sysUserDeptService.queryDeptIdList(id);
		user.setDeptList(deptList);
		if(StringUtil.isValid(deptList)){
			// 将部门id改为中文名称
			String deptTextList = sysUserDeptService.queryDeptText(deptList);
			user.setDeptTextList(deptTextList);
		}
		
		user.setPwd(null);
		user.setSalt(null);
		return R.ok().put("user", user);
	}

	/**
	 * 保存用户
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:user:save")
	public R save(@RequestBody SysUserEntity user) {
		if (StringUtils.isBlank(user.getName())) {
			return R.error("用户名不能为空");
		}
		if (StringUtils.isBlank(user.getPwd())) {
			return R.error("密码不能为空");
		}
		
		user.setSalt(RandomStr.randomStr(Integer.parseInt(PlatformContext.getGoalbalContext("saltLength", String.class))));
		BeanUtil.fillCCUUD(user, getUserId(), getUserId());
		sysUserService.save(user);

		return R.ok();
	}

	/**
	 * 修改用户
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUserEntity user) {
		if (PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(user.getId()))) {
			return R.error("超级管理员不能自行修改");
		}
		if (StringUtils.isBlank(user.getName())) {
			return R.error("用户名不能为空");
		}
		BeanUtil.fillCCUUD(user, getUserId());
		sysUserService.update(user);

		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("sys:user:delete")
	public R delete(@PathVariable("id") Long id){
		if (PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(id))) {
			return R.error("超级管理员不能删除");
		}

		if (getUserId().equals(id)) {
			return R.error("当前用户不能删除");
		}
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			Long ids[] = new Long[1];
			ids[0]=id;
			sysUserService.deleteBatch(ids);
		} else {
			SysUserEntity user = new SysUserEntity();
			user.setId(id);
			user.setStatus(0);
			BeanUtil.fillCCUUD(user, getUserId());
			sysUserService.update(user);
		}
		return R.ok();
	}
	
	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@ResponseBody
	@RequestMapping("/selectForZtree")
	//@RequiresPermissions("department:selectForCmn")
	public R selectForZtree(){
		//查询列表数据
		List<Map<String, Object>> userList = sysUserService.queryListTree();
		
		//添加顶级菜单
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("ztid", 0L);
		root.put("name", "company");
		root.put("ztpid", -1L);
		root.put("open", true);
		userList.add(root);
		
		return R.ok().put("userList", userList);
	}
	
	/**
	 * 测试查询员工所属的项目部
	 */
	@ResponseBody
	@RequestMapping("/aaaa/{id}")
	public R aaaa(@PathVariable Long id){
		List<SysDepartmentEntity> project = sysUserService.getUserProject(id);
		
		return R.ok().putData(project);
	}
	
	
}
