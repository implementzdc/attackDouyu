package zdc.cc.web;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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

    private static Logger log = Logger.getLogger(UserController.class);

    @RequestMapping(value={"/","/index.action"},method = RequestMethod.GET)
    public User loginPage(){
        System.out.println("111");
        User bob = new User();
        bob.setName("Bob");
        bob.setSex("boy");

//        return new ModelAndView("login","user",bob);
        return bob;
    }

    @RequestMapping(value="/getName")
    public void getName(){
        String douyuUrl= "https://www.douyu.com/directory/all";
        try {
            Document document = Jsoup.connect(douyuUrl).timeout(30000).get();
            Elements elements = document.select("a");
            for (Element s : elements) {
                String href  = s.attr("href");
                log.info(href);
            }
            log.info(elements);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
