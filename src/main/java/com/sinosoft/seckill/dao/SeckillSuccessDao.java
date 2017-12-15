package com.sinosoft.seckill.dao;

import com.sinosoft.seckill.bean.SeckillSuccess;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
public interface SeckillSuccessDao {
    /**
     * 添加购买成功信息
     * @param seckillId
     * @param userPhone
     * @return
     */
    int addSeckillSuccess(@Param("seckillId") Long seckillId,@Param("userPhone" +
            "") String userPhone);

    /**
     * 查询购买成功信息
     * @param offset
     * @param limit
     * @return
     */
    SeckillSuccess getSeckillSuccessWithSeckill(@Param("seckillId") Long seckillId,@Param("userPhone") String userPhone);
}
