package com.zhcs.utils.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

//*****************************************************************************
/**
* <p>Title:MyAuthenticator</p>
* <p>Description:自定义验证器类</p>
* <p>Copyright: Copyright (c) 2017</p>
* <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
* @author 刘晓东 - Alter
* @version v1.0 2017年2月23日
*/
//*****************************************************************************
public class MyAuthenticator extends Authenticator {   
	
    /**用户名*/
    String userName = null;   
    /**密码*/
    String password = null;   
        
    public MyAuthenticator() {   
    }  
    
    public MyAuthenticator(String username, String password) {    
        this.userName = username;    
        this.password = password;    
    }
    
    //*************************************************************************
    /** 
    * 【取得】密码验证器
    * @return  	PasswordAuthentication	密码验证器
    */
    //*************************************************************************
    protected PasswordAuthentication getPasswordAuthentication(){   
        return new PasswordAuthentication(userName, password);   
    }   
}   