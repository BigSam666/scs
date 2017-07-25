package com.zhcs.dao;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.SysUserDeptEntity;

//*****************************************************************************
/**
 * <p>Title:SysUserDeptDao</p>
 * <p>Description: 用户与部门对应关系</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface SysUserDeptDao extends BaseDao<SysUserDeptEntity> {
	
	/**
	 * 根据用户ID，获取部门ID列表
	 */
	List<Long> queryDeptIdList(Long userId);

	String queryDeptText(Map<String, Object> map);
	
}
