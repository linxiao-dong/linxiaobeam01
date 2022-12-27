package com.gxa.modules.sys.service;

import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.User;

public interface UserTokenService {
    Result createToken(User user);

    User validateToken(String token);
}
