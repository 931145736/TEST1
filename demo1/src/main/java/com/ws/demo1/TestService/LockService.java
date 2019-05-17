package com.ws.demo1.TestService;

import com.ws.demo1.util.RedisLockUtil;
import com.ws.demo1.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class LockService {


    private static JedisPool pool = null;

    private RedisLockUtil lock = new RedisLockUtil(pool);
    Logger logger = LoggerFactory.getLogger(LockService.class);


    int n = 500;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(200);
        // 设置最大空闲数
        config.setMaxIdle(8);
        // 设置最大等待时间
        config.setMaxWaitMillis(1000 * 100);
        // 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
        config.setTestOnBorrow(true);
        pool = new JedisPool(config, "192.168.1.88", 6379, 3000);
    }

    public void seckill() {
        // 返回锁的value值，供释放锁时候进行判断 上锁
        Jedis jedis = pool.getResource();
        String requestId = RedisUtil.getRequestId();
      //  String identifier = lock.lockWithTimeout("resource", 5000, 1000);
        Boolean flag = RedisUtil.tryGetDistributedLock(jedis,"resource",requestId,1);
        if(flag){
            logger.info(Thread.currentThread().getName()+"获取到锁"+flag);
            System.out.println("n-1="+n--);
            RedisUtil.releaseDistributedLock(jedis,"resource",requestId);
        }else{
            logger.info(Thread.currentThread().getName()+"获取锁失败");
        }

        //放锁
       // lock.releaseLock("resource", identifier);
    }
}
