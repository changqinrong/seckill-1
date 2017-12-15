package com.sinosoft.seckill.dao;

import com.sinosoft.seckill.bean.Seckill;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
public interface SeckillDao {
    /**
     * 减库存
     * @param seckillId
     * @return
     */
    int reduceNumber(@Param("seckillId") Long seckillId, @Param("date") Date date);

    /**
     * 根据商品id查询商品库存信息
     * @param seckillId
     * @return
     */
    Seckill getSckillById(Long seckillId);

    /**
     * 查询所有的库存信息
     * @return
     */
    List<Seckill> getAllSckill(@Param("offset") int offset, @Param("limit") int limit);
}
