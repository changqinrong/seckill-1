package com.sinosoft.seckill.util;


import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

public class ProtoBufUtil {

    public static byte[] serialize(Object o){
        RuntimeSchema schema = RuntimeSchema.createFrom(o.getClass());
        return ProtostuffIOUtil.toByteArray(o, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
    }

    public static <T> T deserialize(byte[] bytes,Class<T> tClass) throws IllegalAccessException, InstantiationException {
        T obj=tClass.newInstance();
        RuntimeSchema schema = RuntimeSchema.createFrom(obj.getClass());
        ProtostuffIOUtil.mergeFrom(bytes, obj, schema);
        return obj;
    }
}
