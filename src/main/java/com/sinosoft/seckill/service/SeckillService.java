package com.sinosoft.seckill.service;

import com.sinosoft.seckill.bean.Seckill;
import com.sinosoft.seckill.dto.ExeSeckillResult;
import com.sinosoft.seckill.dto.ExportSeckill;

import java.util.List;

public interface SeckillService {

    //查询所有秒杀产品信息
    List<Seckill> getAllSeckill();

    //查询给定id的秒杀产品信息
    Seckill getSeckillById(Long seckillId);

    /**
     *
     * 如果秒杀开启，暴露秒杀地址
     * 如果秒杀未开启，返回seckillid，当前时间，开始时间和结束时间
     */
    ExportSeckill exprotSeckillUrl(Long seckillId) throws Exception;

    /**
     * 执行秒杀
     * @param seckillId
     * @param md5
     */
    ExeSeckillResult executeSeckill(Long seckillId, String userPhone, String md5)
            throws Exception;


}
