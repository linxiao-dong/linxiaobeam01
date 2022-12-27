package com.gxa.modules.sys.service.impl;

import com.gxa.common.utils.Base64Utils;
import com.gxa.common.utils.Result;
import com.gxa.common.utils.TokenGenerator;
import com.gxa.modules.sys.entity.User;
import com.gxa.modules.sys.redis.SysUserRedis;
import com.gxa.modules.sys.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokenServiceImpl  implements UserTokenService {

    @Autowired
    private SysUserRedis sysUserRedis;

    @Override
    public Result createToken(User user) {
        //生成token
        String token = TokenGenerator.generateValue();

        sysUserRedis.addToken(token,user);
        String encodeToken = Base64Utils.encode(user.getUsername()+":"+token);
        return new Result().ok(encodeToken);
    }

    @Override
    public User validateToken(String token) {
        String decodeToken = Base64Utils.decode(token);
        User user = sysUserRedis.getUserByToken(decodeToken);
        return user;
    }
}
