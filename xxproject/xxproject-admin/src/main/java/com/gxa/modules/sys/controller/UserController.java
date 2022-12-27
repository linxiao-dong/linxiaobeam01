package com.gxa.modules.sys.controller;

import com.gxa.common.utils.AssertUtils;
import com.gxa.common.utils.ErrorCode;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.common.validator.ValidatorUtils;
import com.gxa.common.validator.group.AddGroup;
import com.gxa.modules.sys.entity.User;
import com.gxa.modules.sys.form.UserForm;
import com.gxa.modules.sys.service.UserService;
import com.gxa.modules.sys.service.UserTokenService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户接口")
@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenService userTokenService;


    @PostMapping("/sys/login")
    public Result login(@RequestBody UserForm userFrom){

        log.info("user------->{}",userFrom);
        //1、拿着用户名去 查询用户信息
        User user = this.userService.queryByUsername(userFrom.getUsername());
        if(user == null){
            return new Result().error(ErrorCode.ACCOUNT_PASSWORD_ERROR,"用户名或密码不正确");
        }

        //2、通过明文加密  对比  密文 是否一致
        String pwd = new SimpleHash("MD5", userFrom.getPassword(), user.getSalt(), 1024).toString();
        //3、不一致      返回Result.error()
        if(!pwd.equals(user.getPwd())){
            return new Result().error(ErrorCode.ACCOUNT_PASSWORD_ERROR,"用户名或密码不正确");
        }

        //4、一致     生成token 保存redis中 返回Result.ok()
        Result result = this.userTokenService.createToken(user);
        Map map = new HashMap();
        map.put("token",result.getData());
        return new Result().ok(map);
    }

    @RequiresPermissions("sys:user:list")
    @ApiOperation(value="用户分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "username",value ="查询条件",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "order",value ="升序asc，降序填desc",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "sidx",value ="排序字段",dataType ="String"),

    }
    )
    @GetMapping("/user/list01")
    public Result<PageUtils> list01(@RequestParam @ApiIgnore Map<String,Object> params){
        log.info("----params-----{}----",params);
        PageUtils pageUtils = this.userService.queryByPage01(params);
        return new Result<PageUtils>().ok(pageUtils);
    }

    @GetMapping("/user/list02")
    public Result<PageUtils> list02(@RequestParam Map<String,Object> params){
        log.info("----params-----{}----",params);
        PageUtils pageUtils = this.userService.queryByPage02(params);
        return new Result<PageUtils>().ok(pageUtils);
    }
    @GetMapping("/user/list03")
    public Result add(@RequestParam User user){

        ValidatorUtils.validateEntity(user, AddGroup.class);

        //添加操作
        return new Result<>().ok();
    }
    @GetMapping("/user/list04")
    public Result query(@RequestParam String str){
        AssertUtils.isBlank(str, "字符串不为能空");
        //添加操作
        return new Result<>().ok();
    }

}
