package com.gxa.common.utils;

/**
 * @author shelei
 * @since 1.0.0
 */
public class RedisKeys {
    /**
     * 系统参数Key
     */
    public static String getSysParamsKey(){
        return "sys:params";
    }

    /**
     * 登录验证码Key
     */
    public static String getLoginCaptchaKey(String uuid){
        return "sys:captcha:" + uuid;
    }

    /**
     * 系统日志Key
     */
    public static String getSysLogKey(){
        return "sys:log";
    }


    /**
     * 用户权限标识Key
     */
    public static String getUserPermissionsKey(Long userId){

        return "sys:user:permissions:" + userId;
    }

    /**
     * 用户登录的key
     * @return
     */
    public static String getSysUserTokenKey(String username,String token){
        return "sys:user:"+username + ":"+token;
    }
    public static String getSysUserTokenKey(String token){
        return "sys:user:"+token;
    }

}
 
 
