package com.zhcs.validator.group;

import javax.validation.GroupSequence;

//*****************************************************************************
/**
 * <p>Title:Group</p>
 * <p>Description:定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年5月26日
 */
//*****************************************************************************
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
