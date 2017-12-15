package com.sinosoft.seckill.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillSuccessDaoTest {
    @Autowired
    private SeckillSuccessDao seckillSuccessDao;
    private static final Logger logger= LoggerFactory.getLogger(SeckillSuccessDaoTest.class);
    @Test
    public void addSeckillSuccess() throws Exception {
        Long seckillId=1000L;
        String userPhone="18210208855";
        Assert.assertEquals(1,seckillSuccessDao.addSeckillSuccess(seckillId,userPhone));
    }


    @Test
    public void getSeckillSuccessWithSeckill() throws Exception {
        Long seckillId=1000L;
        String userPhone="18210208855";
        logger.info("购买成功信息{}",seckillSuccessDao.getSeckillSuccessWithSeckill(seckillId,userPhone));
    }

}