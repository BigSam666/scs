package com.zhcs.dao;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.MailtemplateEntity;

//*****************************************************************************
/**
 * <p>Title:MailtemplateDao</p>
 * <p>Description: 邮件模板</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface MailtemplateDao extends BaseDao<MailtemplateEntity> {

	List<Map<String, Object>> getSendTemplate();
}
