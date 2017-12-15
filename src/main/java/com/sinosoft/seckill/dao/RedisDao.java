package com.sinosoft.seckill.dao;

import com.sinosoft.seckill.bean.Seckill;
import com.sinosoft.seckill.util.ProtoBufUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisDao {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private static Logger logger= LoggerFactory.getLogger(RedisDao.class);

    public Seckill getSeckill(Long seckillId) throws InstantiationException, IllegalAccessException {
        ValueOperations<String,String> valueOperations=stringRedisTemplate.opsForValue();
        if (valueOperations.get("seckillId:"+seckillId)==null){
            return null;
        }else {
            byte[] bytes=valueOperations.get("seckillId:"+seckillId).getBytes();
            logger.info("反序列化前字节数组：{}",bytes);
            return ProtoBufUtil.deserialize(bytes,Seckill.class);
        }

    }

    public  void  putSeckill(Seckill seckill){
        ValueOperations<String,String> valueOperations=stringRedisTemplate.opsForValue();
        byte[] bytes= ProtoBufUtil.serialize(seckill);
        logger.info("序列化后字节数组：{}",bytes);
        valueOperations.set("seckillId:"+seckill.getSeckillId(),bytes.toString(),3600);
    }


}
