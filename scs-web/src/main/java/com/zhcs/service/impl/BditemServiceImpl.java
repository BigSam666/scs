package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhcs.dao.BditemDao;
import com.zhcs.entity.BditemEntity;
import com.zhcs.service.BditemService;


//*****************************************************************************
/**
 * <p>Title:BditemServiceImpl</p>
 * <p>Description: 预算明细有哪些项</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("bditemService")
public class BditemServiceImpl implements BditemService {
	@Autowired
	private BditemDao bditemDao;
	
	@Override
	public BditemEntity queryObject(Long id){
		return bditemDao.queryObject(id);
	}
	
	@Override
	public List<BditemEntity> queryList(Map<String, Object> map){
		return bditemDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bditemDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(BditemEntity bditem){
		bditemDao.save(bditem);
	}
	
	@Override
	@Transactional
	public void update(BditemEntity bditem){
		bditemDao.update(bditem);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		bditemDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		bditemDao.deleteBatch(ids);
	}

	@Override
	public List<BditemEntity> queryListByParent(BditemEntity bditemEntity) {
		String queryParam = null;
		int parentLevel = 0;
		if (bditemEntity == null) {
			queryParam = "-1";
		} else {
			queryParam = bditemEntity.getCode();
			parentLevel = bditemEntity.getLevel();
		}
		List<BditemEntity> list = bditemDao.queryListByParent(queryParam);
		for(BditemEntity tmp:list){
			tmp.setLevel(parentLevel+1);
		}
		return list;
	}

	/**
	 * 查找指定code下所有的项
	 */
	@Override
	public List<List<BditemEntity>> getTotalItemByCode(String targetCode) {
		List<List<BditemEntity>> god = new ArrayList<>();
		// 先处理第一层
		List<BditemEntity> firstLevel = null;
		if (targetCode == null || targetCode.trim().equals("-1")) {
			targetCode = "-1";
			firstLevel = bditemDao.queryListByParent(targetCode);
		} else {
			firstLevel = new ArrayList<>();
			firstLevel.add(bditemDao.queryObjectByCode(targetCode));
		}
		for (BditemEntity tmp : firstLevel) {
			tmp.setLevel(1);
		}
		god.add(firstLevel);
		// 取出最后一层的所有元素，然后找出他们的子元素，并将这些子元素放入新的层
		// 也就是说每遍历一次，都会把新的一层的元素全部取出
		// 也就是说如果某次循环后god.size()没有发生变化，那么我们已经取出了所有的元素
		int preLevelCount = 0;
		while(god.size() > preLevelCount) {
			preLevelCount = god.size();
			List<BditemEntity> currentLastLevel = god.get(preLevelCount-1); // 当前最后一层
			List<BditemEntity> nextLevel = new ArrayList<>();               // 下一层
			for (BditemEntity bditemEntity : currentLastLevel) { 
				List<BditemEntity> _tmp = queryListByParent(bditemEntity);
				if (_tmp != null && _tmp.size() > 0) {
					nextLevel.addAll(_tmp);
					bditemEntity.setHasChild(true);
				} else {
					// 当前项没有子项，则跳过
					bditemEntity.setHasChild(false);
					continue;
				}
			}
			// 将下一级的Map放入顶级容器中
			if (nextLevel.size() > 0) {
				god.add(nextLevel);
			}
		}
		
		return god;
	}

	@Override
	public List<Map<String,Object>> selectForZtree() {
		return bditemDao.selectForZtree();
	}

	@Override
	public BditemEntity queryObjectByCode(String dtype) {
		return bditemDao.queryObjectByCode(dtype);
	}
	
}
