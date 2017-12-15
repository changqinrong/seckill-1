package com.sinosoft.seckill.service.impl;

import com.sinosoft.seckill.bean.Seckill;
import com.sinosoft.seckill.bean.SeckillSuccess;
import com.sinosoft.seckill.dao.SeckillDao;
import com.sinosoft.seckill.dao.SeckillSuccessDao;
import com.sinosoft.seckill.dto.ExeSeckillResult;
import com.sinosoft.seckill.dto.ExportSeckill;
import com.sinosoft.seckill.emun.SeckillEnum;
import com.sinosoft.seckill.exception.SeckillException;
import com.sinosoft.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SeckillSuccessDao seckillSuccessDao;

    private final String slat = "dfadsfsdfask230ewopoeqiweqeopqw";//混淆md5

    private static final Logger logger= LoggerFactory.getLogger(SeckillServiceImpl.class);

    @Override
    public List<Seckill> getAllSeckill() {
        return seckillDao.getAllSckill(0, 100);
    }

    @Override
    public Seckill getSeckillById(Long seckillId) {
        //先从缓存中读取数据，如果缓存中没有，再从数据库中读取数据，并将数据放入缓存中

        return seckillDao.getSckillById(seckillId);
    }

    @Override
    public ExportSeckill exprotSeckillUrl(Long seckillId) throws Exception{
        Seckill seckill = this.getSeckillById(seckillId);
        if (seckill == null) {
            throw  new SeckillException(SeckillEnum.SECKILL_INNER_ERROR);
        }
        Date date = new Date();
        if (date.getTime() < seckill.getStartTime().getTime() || date.getTime() > seckill.getEndTime().getTime()) {
            return new ExportSeckill(false, seckillId, date.getTime(), seckill.getStartTime().getTime(), seckill.getEndTime().getTime());
        }
        String md5 = getMD5(seckillId);
        return new ExportSeckill(true, seckillId, md5);
    }

    private String getMD5(Long seckillId) {
        String md5 = seckillId + "/" + slat;
        return DigestUtils.md5DigestAsHex(md5.getBytes());
    }

    @Override
    @Transactional
    public ExeSeckillResult executeSeckill(Long seckillId, String userPhone, String md5) throws Exception {
        //校验md5是否被串改
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException(SeckillEnum.SECKILL_CLOSE);
        }
        //减库存
        Date date = new Date();
        int updateCount = seckillDao.reduceNumber(seckillId, date);//减少库存
        if (updateCount <= 0) {
            throw new SeckillException(SeckillEnum.SECKILL_CLOSE);
        }
        //增加秒杀成功信息
        int insertCount = seckillSuccessDao.addSeckillSuccess(seckillId, userPhone);
        if (insertCount <= 0) {
            throw new SeckillException(SeckillEnum.SECKILL_REPEAT);
        }
        SeckillSuccess seckillSuccess = seckillSuccessDao.getSeckillSuccessWithSeckill(seckillId, userPhone);

        return new ExeSeckillResult(seckillId, SeckillEnum.SUCCESS, seckillSuccess);
    }
}
