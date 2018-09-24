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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author
 * @create 2018/9/24
 * @since 1.0.0
 */
public class RedisTest {

    @Autowired
    RedisConnectionFactory factory;

    @Test
    public void testRedis(){
        //得到一个连接
        RedisConnection conn = factory.getConnection();
        conn.set("hello".getBytes(), "world".getBytes());
        System.out.println(new String(conn.get("hello".getBytes())));
    }
}
