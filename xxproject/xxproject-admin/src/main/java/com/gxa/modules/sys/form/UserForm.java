package com.gxa.modules.sys.form;

import lombok.Data;

@Data
public class UserForm {
    private String username;
    private String password;
    private String captch;//验证码
    private String uuid;//用于找验证码的

}
