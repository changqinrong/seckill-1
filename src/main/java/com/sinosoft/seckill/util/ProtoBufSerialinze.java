package com.sinosoft.seckill.util;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class ProtoBufSerialinze<T> implements RedisSerializer<T> {
    private Class<T> type;

    public ProtoBufSerialinze(Class<T> type) {
        this.type = type;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        return  ProtoBufUtil.serialize(t);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        try {
            return  ProtoBufUtil.deserialize(bytes,type);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
