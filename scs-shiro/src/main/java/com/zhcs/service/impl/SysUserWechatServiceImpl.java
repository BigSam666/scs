package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.SysUserWechatDao;
import com.zhcs.entity.SysUserWechatEntity;
import com.zhcs.service.SysUserWechatService;


//*****************************************************************************
/**
 * <p>Title:SysUserWechatServiceImpl</p>
 * <p>Description: 用户与微信对应关系(只与管理员有关)</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("sysUserWechatService")
public class SysUserWechatServiceImpl implements SysUserWechatService {
	@Autowired
	private SysUserWechatDao sysUserWechatDao;
	
	@Override
	public SysUserWechatEntity queryObject(Long id){
		return sysUserWechatDao.queryObject(id);
	}
	
	@Override
	public List<SysUserWechatEntity> queryList(Map<String, Object> map){
		return sysUserWechatDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysUserWechatDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(SysUserWechatEntity sysUserWechat){
		sysUserWechatDao.save(sysUserWechat);
	}
	
	@Override
	@Transactional
	public void update(SysUserWechatEntity sysUserWechat){
		sysUserWechatDao.update(sysUserWechat);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		sysUserWechatDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		sysUserWechatDao.deleteBatch(ids);
	}
	
}
