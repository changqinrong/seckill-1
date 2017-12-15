package com.sinosoft.seckill.config;

import com.sinosoft.seckill.bean.Seckill;
import com.sinosoft.seckill.util.ProtoBufSerialinze;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public RedisTemplate<String, Seckill> redisTemplate(){

        RedisTemplate template=new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setKeySerializer(new ProtoBufSerialinze<Seckill>(Seckill.class));
        return template;
    }
}
