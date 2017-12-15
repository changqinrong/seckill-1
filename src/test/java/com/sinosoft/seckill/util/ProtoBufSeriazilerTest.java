package com.sinosoft.seckill.util;

import com.sinosoft.seckill.bean.Seckill;
import com.sinosoft.seckill.dao.SeckillDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProtoBufSeriazilerTest {
    @Autowired
    private SeckillDao seckillDao;
    private static final Logger logger= LoggerFactory.getLogger(ProtoBufSeriazilerTest.class);

    @Test
    public void serialize() throws Exception {
        Long seckillId=1000L;
        Seckill seckill=seckillDao.getSckillById(seckillId);
        logger.info("序列化后信息：{}", ProtoBufUtil.serialize(seckill));
        // 序列化后信息：[8, -24, 7, 18, 20, 49, 48, 48, 48, -27, -123, -125, -25, -89, -110, -26, -99, -128, 105, 112, 104, 111, 110, 101, 55, 24, 7, 33, -120, -77, 82, -19, 95, 1, 0, 0, 41, 0, -48, -63, -18, 95, 1, 0, 0, 49, 24, 116, -113, -35, 95, 1, 0, 0]
        seckill= ProtoBufUtil.deserialize(ProtoBufUtil.serialize(seckill),Seckill.class);
        logger.info("返序列化后对象:{}",seckill);
    }
}