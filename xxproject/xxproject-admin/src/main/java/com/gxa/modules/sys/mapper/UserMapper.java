package com.gxa.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.sys.entity.User;


public interface UserMapper extends BaseMapper<User> {

    public User queryByUsername01(String username);
}
