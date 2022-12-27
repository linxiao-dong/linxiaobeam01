package com.gxa.modules.sys.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gxa.common.validator.group.AddGroup;
import com.gxa.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@TableName("t_user")
public class User implements Serializable {

    private Integer id;

    @TableField("user_name")
    @NotBlank(message="用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;

    @NotBlank(message="密码不能为空", groups = AddGroup.class)
    private String pwd;
    private String salt;


}
