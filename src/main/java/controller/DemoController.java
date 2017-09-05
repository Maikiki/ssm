package controller;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

import java.util.List;

/**
 * Created by Froid on 2017/5/12.
 */
@Controller
@RequestMapping("/user")
public class DemoController {


    @Autowired
    private UserService userService;


//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    @RequestMapping("/get/{var1}")
    @ResponseBody
    public String test(@PathVariable String var1) {
        return var1;
    }

    @RequestMapping("/get")
    @ResponseBody
    public List<User> test() {
        return userService.getUsers(1, 10);
    }

    @RequestMapping(value = "testParams", params = {"param1=v1", "param2", "!param3"})
    @ResponseBody
    public String testParams() {
        return "testParams";
    }
}


