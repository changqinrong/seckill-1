package com.sinosoft.seckill.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillDaoTest {
    @Autowired
    private  SeckillDao seckillDao;
    private static final Logger logger= LoggerFactory.getLogger(SeckillDaoTest.class);
    @Test
    public void reduceNumber() throws Exception {
        Long seckillId=1000L;
        Date date=new Date();
        Assert.assertEquals(new Integer(1),new Integer(seckillDao.reduceNumber(seckillId,date)));
    }

    @Test
    public void getSckillById() throws Exception {
        Long seckill=1001L;
        logger.info("秒杀产品信息：{}",seckillDao.getSckillById(seckill));
    }

    @Test
    public void getAllSckill() throws Exception {
        int offset=1;
        int limit=1;
        logger.info("所有秒杀产品信息{}",seckillDao.getAllSckill(offset,limit));
    }

}