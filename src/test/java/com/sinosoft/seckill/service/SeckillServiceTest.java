package com.sinosoft.seckill.service;

import com.sinosoft.seckill.bean.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.channels.SelectionKey;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillServiceTest {
    @Autowired
    private SeckillService seckillService;
    private static final Logger logger= LoggerFactory.getLogger(SeckillServiceTest.class);
    @Test
    public void getAllSeckill() throws Exception {
        logger.info("秒杀产品信息如下：{}",seckillService.getAllSeckill());
    }

    @Test
    public void getSeckillById() throws Exception {
        logger.info("秒杀产品信息如下:{}",seckillService.getSeckillById(1001L));
    }

    @Test
    public void exprotSeckillUrl() throws Exception {
        logger.info("秒杀产品接口信息：{}",seckillService.exprotSeckillUrl(1001L));
        //秒杀产品接口信息：ExportSeckill{exposer=true, seckillId=1001, currTime=null, startTime=null, endTime=null, md5='b75774067e772dc46420b87191489e91'}
    }

    @Test
    public void executeSeckill() throws Exception {
        String md5="b75774067e772dc46420b87191489e91";
        logger.info("执行秒杀信息:{}",seckillService.executeSeckill(1001L,"18210108855",md5));
    }

}