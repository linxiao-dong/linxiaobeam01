package com.gxa.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.User;

import java.util.Map;


public interface UserService extends IService<User> {

    User queryByUsername(String username);
    User queryByUsername01(String username);

    /**
     * 只是单独的分页，不带查询条件
     * @param params
     * @return
     */
    PageUtils queryByPage01(Map<String,Object> params);

    /**
     * 带条件查询的分页
     *  查询的条件和 分页的相关信息都在 params中
     * @param params
     * @return
     */
    PageUtils queryByPage02(Map<String,Object> params);



}
