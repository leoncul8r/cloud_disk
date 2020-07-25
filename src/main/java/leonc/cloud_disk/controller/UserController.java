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
        Result res = new Result ();


            Integer userId;
            try
            {
                userId = userService.create (userName, password).getId ();

                res.setData (userId);
                res.setMessageAndCode ("注册成功", 1);
                log.info ("注册成功");
            }catch (NullPointerException e)
            {
                res.setMessageAndCode ("注册失败，可能是使用了重复的用户名", 0);
                log.info ("注册失败，可能是使用了重复的用户名");
            }

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
            log.info ("登录失败");
        }
        else
        {
            res.setData (user.getId ());
            res.setMessageAndCode ("登录成功，返回用户id", 1);
            log.info ("登录成功");
        }

        return res;
    }

}
