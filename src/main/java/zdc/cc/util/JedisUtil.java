/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: JedisUtil
 * Author:   zt
 * Date:     2018/9/27 23:09
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package zdc.cc.util;

import org.apache.log4j.Logger;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author
 * @create 2018/9/27
 * @since 1.0.0
 */
public class JedisUtil {
    private static final Logger log = Logger.getLogger(JedisUtil.class);

    private JedisUtil() {

    }

    private static class RedisUtilHolder{
        private static final JedisUtil instance = new JedisUtil();
    }

    public static JedisUtil getInstance() {
        return RedisUtilHolder.instance;
    }

    private static Map<String, JedisPool> map = new HashMap<String, JedisPool>();


    private static JedisPool getPool(String ip, int port) {
        String key = ip + ":"+ port;
        JedisPool jedisPool = null;
        return jedisPool;
    }
}
