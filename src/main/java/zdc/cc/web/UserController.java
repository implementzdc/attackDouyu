package zdc.cc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import zdc.cc.domain.User;
import zdc.cc.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value={"/","/index.action"},method = RequestMethod.GET)
    public User loginPage(){
        System.out.println("111");
        User bob = new User();
        bob.setName("Bob");
        bob.setSex("boy");

//        return new ModelAndView("login","user",bob);
        return bob;
    }
}
