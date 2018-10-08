/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: RedisTest
 * Author:   zt
 * Date:     2018/9/24 19:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package zdc.cc.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zdc.cc.Application;
import zdc.cc.domain.User;
import zdc.cc.util.RedisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author
 * @create 2018/9/24
 * @since 1.0.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest {


    private static final Logger log = LoggerFactory.getLogger(RedisTest.class);
    @Autowired
    RedisUtil redisUtil;

    @Test
    public void testRedis(){
        User user= new User();
        user.setSex("1");
        redisUtil.set("123", user);

        List<User> list = new ArrayList<>();
        list.add(user);
        redisUtil.set("test",list);
        User user1 =(User) redisUtil.get("123");
        List<User> list1 =( List<User>) redisUtil.get("test");
        log.info(user1.toString());
    }

    public RedisUtil getRedisUtil() {
        return redisUtil;
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
}
