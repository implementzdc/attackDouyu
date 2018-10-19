/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: RedisConfig
 * Author:   zt
 * Date:     2018/9/24 18:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package zdc.cc.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;
import zdc.cc.util.RedisUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author
 * @create 2018/9/24
 * @since 1.0.0
 */
@Configuration
@EnableCaching
@PropertySource("classpath:redis.properties")
public class RedisConfig extends CachingConfigurerSupport {
    @Value("${redis.maxIdle}")
    private Integer maxIdle;

    @Value("${redis.maxTotal}")
    private Integer maxTotal;

    @Value("${redis.maxWaitMillis}")
    private Integer maxWaitMillis;

    @Value("${redis.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;

    @Value("${redis.numTestsPerEvictionRun}")
    private Integer numTestsPerEvictionRun;

    @Value("${redis.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${redis.testWhileIdle}")
    private boolean testWhileIdle;


    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;

    @Value("${spring.redis.cluster.max-redirects}")
    private Integer mmaxRedirectsac;

    @Value("${redis.hostName}")
    private String hostName;

    @Value("${redis.port}")
    private Integer port;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.timeout}")
    private Integer timeout;

    @Value("${redis.dbIndex}")
    private Integer dbIndex;

    /**
     * JedisPoolConfig 连接池
     *
     * @return
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲数
        jedisPoolConfig.setMaxIdle(maxIdle);
        // 连接池的最大数据库连接数
        jedisPoolConfig.setMaxTotal(maxTotal);
        // 最大建立连接等待时间
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        // 在空闲时检查有效性, 默认false
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        return jedisPoolConfig;
    }

    /**
     * Redis集群的配置
     *
     * @return RedisClusterConfiguration
     * @throws
     * @autor lpl
     * @date 2017年12月22日
     */
    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        //Set<RedisNode> clusterNodes
        String[] serverArray = clusterNodes.split(",");

        Set<RedisNode> nodes = new HashSet<RedisNode>();

        for (String ipPort : serverArray) {
            String[] ipAndPort = ipPort.split(":");
            nodes.add(new RedisNode(ipAndPort[0].trim(), Integer.valueOf(ipAndPort[1])));
        }

        redisClusterConfiguration.setClusterNodes(nodes);
        redisClusterConfiguration.setMaxRedirects(mmaxRedirectsac);

        return redisClusterConfiguration;
    }

//    @Bean
//    public JedisCluster getJedisCluster(JedisPoolConfig jedisPoolConfig) {
//        String[] cNodes = clusterNodes.split(",");
//        Set<HostAndPort> nodes = new HashSet<>();
//        //分割出集群节点
//        for (String node : cNodes) {
//            String[] hp = node.split(":");
//            nodes.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));
//        }
//
//        //创建集群对象//
////        JedisCluster jedisCluster = new JedisCluster(nodes,timeout);
//        return new JedisCluster(nodes, maxWaitMillis, timeout, mmaxRedirectsac, password, jedisPoolConfig);
//    }


    /**
     * 配置工厂
     *
     * @param @param  jedisPoolConfig
     * @param @return
     * @return JedisConnectionFactory
     * @throws
     * @Title: JedisConnectionFactory
     * @autor lpl
     * @date 2017年12月22日
     */
    @Bean
    public JedisConnectionFactory JedisConnectionFactory(JedisPoolConfig jedisPoolConfig, RedisClusterConfiguration redisClusterConfiguration) {
        JedisConnectionFactory JedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration, jedisPoolConfig);
        JedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        //IP地址
//        JedisConnectionFactory.setHostName(hostName);
//        //端口号
//        JedisConnectionFactory.setPort(port);
        JedisConnectionFactory.setTimeout(timeout);
//
        JedisConnectionFactory.setDatabase(dbIndex);
        JedisConnectionFactory.setPassword(password);
        return JedisConnectionFactory;
    }


    /**
     * 单机版配置
     * @Title: JedisConnectionFactory
     * @param @param jedisPoolConfig
     * @param @return
     * @return JedisConnectionFactory
     * @autor lpl
     * @date 2018年2月24日
     * @throws
     */
//    @Bean
//    public JedisConnectionFactory JedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
//        JedisConnectionFactory JedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
//        //连接池
//        JedisConnectionFactory.setPoolConfig(jedisPoolConfig);
//        //IP地址
//        JedisConnectionFactory.setHostName(hostName);
//        //端口号
//        JedisConnectionFactory.setPort(port);
//        //如果Redis设置有密码
//        JedisConnectionFactory.setPassword(password);
//        //客户端超时时间单位是毫秒
//        JedisConnectionFactory.setTimeout(timeout);
//
//        JedisConnectionFactory.setDatabase(dbIndex);
//        return JedisConnectionFactory;
//    }

    /**
     * 实例化 RedisTemplate 对象
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * 设置数据存入 redis 的序列化方式,并开启事务
     *
     * @param redisTemplate
     * @param factory
     */
    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(factory);
    }

    /**
     * 注入封装RedisTemplate
     *
     * @return RedisUtil
     * @throws
     * @Title: redisUtil
     * @autor lpl
     * @date 2017年12月21日
     */
    @Bean(name = "redisUtil")
    public RedisUtil redisUtil(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }
//    @Value("${vehicle.redis.caches.expiration:-1}")
//    private String expiration;
//    @Value("${vehicle.redis.defaultExpiration}")
//    private long defaultExpiration;
//
//    @Value("${vehicle.redis.caches.name}")
//    private String cache_name;
//
//    @Bean
//    public CacheManager cacheManager(RedisTemplate redisTemplate) {
//
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//        List<String> cacheNames=new ArrayList<String>();
//        Map<String,Long> cacheExpirations=new HashMap<String,Long>(cacheNames.size(),1);
//        String[] exps=expiration.split(",");
//        Cache c=new Cache();
//        Optional.ofNullable(cache_name).ifPresent(cname -> {
//            c.index=0;
//            Arrays.asList(cname.split(",")).forEach(name -> {
//                if(name!=null && !name.equals("")){
//                    cacheNames.add(name);
//                    c.index=c.index++;
//                    if(exps[c.index]!=null &&  !exps[c.index].equals("")){
//                        cacheExpirations.put(name, Long.valueOf(exps[c.index]));
//                    }
//                }
//            });
//        });
//        cacheManager.setCacheNames(cacheNames);
//
//        cacheManager.setDefaultExpiration(defaultExpiration);
//        cacheManager.setExpires(cacheExpirations);
//        return cacheManager;
//    }
//    public class Cache{
//        public int index;
//        public String name;
//        public long expiration;
//    }

}
