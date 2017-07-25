package com.zhcs.validator;

import com.zhcs.utils.StringUtil;
import com.zhcs.utils.ZHCSException;

//*****************************************************************************
/**
 * <p>Title:Assert</p>
 * <p>Description:数据校验</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年5月26日
 */
//*****************************************************************************
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtil.isBlank(str)) {
            throw new ZHCSException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new ZHCSException(message);
        }
    }
}
