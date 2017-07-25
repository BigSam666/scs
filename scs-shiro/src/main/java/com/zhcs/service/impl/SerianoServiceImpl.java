package com.zhcs.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhcs.context.PlatformContext;
import com.zhcs.dao.SysSerianoDao;
import com.zhcs.entity.SysSerianoEntity;
import com.zhcs.service.SerianoService;
import com.zhcs.utils.StringUtil;


@Service("serianoService")
public class SerianoServiceImpl implements SerianoService {
	@Autowired
	private SysSerianoDao sysSerianoDao;
	
	@Override
	public SysSerianoEntity queryObject(String type){
		return sysSerianoDao.queryObject(type);
	}
	
	@Override
	public List<SysSerianoEntity> queryList(Map<String, Object> map){
		return sysSerianoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysSerianoDao.queryTotal(map);
	}
	
	@Override
	public void save(SysSerianoEntity seriano){
		sysSerianoDao.save(seriano);
	}
	
	@Override
	public void update(SysSerianoEntity seriano){
		sysSerianoDao.update(seriano);
	}
	
	@Override
	public void delete(String type){
		sysSerianoDao.delete(type);
	}
	
	@Override
	public void deleteBatch(String[] types){
		sysSerianoDao.deleteBatch(types);
	}
	
	@Override
	public SysSerianoEntity getno(SysSerianoEntity seriano) {
		return sysSerianoDao.getno(seriano);
	}

	@Override
	public synchronized String getId(String type){
		return this.getId(type, null);
	}
	
	@Override
	public synchronized String getId(String type, String year){
		StringBuffer id = new StringBuffer();
		String cases[] = PlatformContext.getGoalbalContext(type, String.class).split("#_#");
		String key = null;
		for (String name : cases) {
			String iLen = name.split("_")[1];
			if (name.startsWith("KEY_")) {
				id.append(iLen);
				key = iLen;
			} else if (name.startsWith("YEAR_")) {
				id.append(StringUtil.fillZero(year, Integer.parseInt(iLen), 0));
			} else if (name.startsWith("NO_")) {
				// 得到流水号
				SysSerianoEntity serano = new SysSerianoEntity();
				serano.setType(type);
				serano.setYear(year);
				serano.setKeyone(StringUtil.isValid(key) ? key : "not_need");
				serano = this.getno(serano);
				BigDecimal no = new BigDecimal("0");
				if(StringUtil.isBlank(serano)) {
					// 表示没有查询到数据，进行插入操作
					serano = new SysSerianoEntity();
					no = no.add(new BigDecimal("1"));
					serano.setType(type);
					serano.setYear(StringUtil.isValid(year) ? year : "not_need");
					serano.setKeyone(StringUtil.isValid(key) ? key : "not_need");
					serano.setNo(no.add(new BigDecimal("1")));
					this.save(serano);
				} else {
					no = serano.getNo();
					serano.setType(type);
					serano.setYear(year);
					serano.setKeyone(StringUtil.isValid(key) ? key : "not_need");
					serano.setNo(no.add(new BigDecimal("1")));
					this.update(serano);
				}
				id.append(StringUtil.fillZero(StringUtil.valueOf(no), Integer.parseInt(iLen), 0));
			}
		}
		return StringUtil.valueOf(id);
	}
	
}
