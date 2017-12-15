package com.sinosoft.seckill.dao;

import com.sinosoft.seckill.bean.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDaoTest {
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private SeckillDao seckillDao;
    private static final Logger logger= LoggerFactory.getLogger(RedisDaoTest.class);
    @Test
    public void getSeckill() throws Exception {
        Long seckillId=1001l;
        Seckill seckill=redisDao.getSeckill(seckillId);
        logger.info("缓存信息如下11:{}",seckill);
        if (seckill==null){
            seckill=seckillDao.getSckillById(seckillId);
            redisDao.putSeckill(seckill);
            seckill=redisDao.getSeckill(seckillId);
            logger.info("缓存信息如下22:{}",seckill);
        }
    }
}