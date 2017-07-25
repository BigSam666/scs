package com.zhcs.service;

import com.zhcs.entity.SysUserWechatEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:SysUserWechatService</p>
 * <p>Description: 用户与微信对应关系(只与管理员有关)</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface SysUserWechatService {
	
	SysUserWechatEntity queryObject(Long id);
	
	List<SysUserWechatEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysUserWechatEntity sysUserWechat);
	
	void update(SysUserWechatEntity sysUserWechat);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
