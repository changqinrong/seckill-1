package com.sinosoft.seckill.controler;

import com.sinosoft.seckill.dto.ExportSeckill;
import com.sinosoft.seckill.dto.SeckillResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillControlerTest {
    @Autowired
    private SeckillControler seckillControler;
    private  static  final Logger logger= LoggerFactory.getLogger(SeckillControlerTest.class);
    @Test
    public void list() throws Exception {
        //logger.info("秒杀产品信息：{}",seckillControler.list());
    }

    @Test
    public void detail() throws Exception {
        //logger.info("秒杀产品信息{}",seckillControler.detail(1001L));
    }

    @Test
    public void now() throws Exception {
        SeckillResult<Date> seckillResult=seckillControler.now();
        logger.info("当前系统时间：{}", seckillResult.getData());
    }

    @Test
    public void seckill() throws Exception {
        Long seckillId=1000L;
        SeckillResult<ExportSeckill> seckillSeckillResult=seckillControler.exposer(seckillId);

        if (seckillSeckillResult.getCode()==0){
            String md5=seckillSeckillResult.getData().getMd5();
            String userPhone="18210100089";
            logger.info("信息如下：{}",seckillControler.exeSeckill(seckillId,md5,userPhone));
            /**
             *  SeckillResult{code=0, msg='成功',
             *  data=ExeSeckillResult{seckillId=1000, state=0, state_info='成功',
             *  seckillSuccess=SeckillSuccess{seckillId=1000, userPhone=18210100089, state=-1, createTime=Thu Nov 23 17:19:01 CST 2017, seckill=Seckill{seckillId=1000, name='1000元秒杀iphone7', number=7, startTime=Thu Nov 23 17:19:01 CST 2017, endTime=Fri Nov 24 00:00:00 CST 2017, createTime=Tue Nov 21 15:51:27 CST 2017}}}}
             */


        }else{
            logger.info("信息如下：{}",seckillSeckillResult);
        }
    }

}