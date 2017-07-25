package com.zhcs.dao;

import com.zhcs.entity.BditemEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************

/**
 * <p>Title:BditemDao</p>
 * <p>Description: 预算明细有哪些项</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface BditemDao extends BaseDao<BditemEntity> {

	/**
	 * 查询父元素的代码是指定代码  的  所有元素
	 */
	List<BditemEntity> queryListByParent(String parentCode);

	/**
	 * 根据code查询元素
	 */
	BditemEntity queryObjectByCode(String targetCode);
	
	/**
	 * 获取所有的预算明细项（以ztree需要的格式）
	 */
	List<Map<String,Object>> selectForZtree();
	
}
