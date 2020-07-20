package leonc.cloud_disk.controller;

import io.swagger.annotations.ApiOperation;
import leonc.cloud_disk.entity.User;
import leonc.cloud_disk.service.UserService;
import leonc.cloud_disk.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (value="/user")
public class UserController
{
    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private UserService userService;

    @PostMapping ("/register")
    @ResponseBody
    @ApiOperation (notes = "返回值补充说明：Data内容为用户Id（userId）", value = "用户注册")
    public Result register (@RequestParam ("userName") String userName,
                            @RequestParam ("password") String password)
    {
        Integer userId;
        userId = userService.create (userName, password).getId ();

        Result res = new Result ();
        res.setData (userId);
        res.setMessageAndCode ("注册成功", 1);

        return res;
    }

    @PostMapping ("/login")
    @ResponseBody
    @ApiOperation (notes = "返回值补充说明：Data内容为用户Id（userId）", value = "用户登录")
    public Result login (@RequestParam ("userName") String userName,
                         @RequestParam ("password") String password)
    {
        User user;
        user = userService.getUserInfoByUserName (userName);
        Result res = new Result ();

        //验证密码
        if (! password.equals (user.getPassword ()))
        {
            res.setMessageAndCode ("用户名或密码错误，登录失败", 0);
        }
        else
        {
            res.setData (user.getId ());
            res.setMessageAndCode ("登录成功，返回用户id", 1);
        }

        return res;
    }

}
