package com.zhcs.service;

import com.zhcs.entity.BditemEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************

/**
 * <p>Title:BditemService</p>
 * <p>Description: 预算明细有哪些项</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface BditemService {
	
	BditemEntity queryObject(Long id);
	
	List<BditemEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BditemEntity bditem);
	
	void update(BditemEntity bditem);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	/**
	 * 查询父元素的代码是指定代码  的  所有元素
	 */
	List<BditemEntity> queryListByParent(BditemEntity bditemEntity);
	/**
	 * 获取指定代码对应的项及其子项
	 *     ★                    第一个List
	 *   /  \  \
	 *  ★    ★    ★          第二个List
	 *  /   /   /\
	 * ★    ★    ★  ★      第三个List
	 * 注意如果指定的targetCode不是-1时，第一个List中的元素是code属性值为targetCode的元素
	 * 如果指定的targetCode值为-1或null时，第一个List中的元素的pcode属性值为-1的元素
	 */
	List<List<BditemEntity>> getTotalItemByCode(String targetCode);

	/**
	 * 获取所有的预算明细项（以ztree需要的格式）
	 */
	List<Map<String,Object >> selectForZtree();

	/**
	 * 根据代码获取实体
	 */
	BditemEntity queryObjectByCode(String dtype);
}
