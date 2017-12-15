package com.sinosoft.seckill.handle;

import com.sinosoft.seckill.dto.SeckillResult;
import com.sinosoft.seckill.emun.SeckillEnum;
import com.sinosoft.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;;

@ControllerAdvice
public class SeckillExceptionHandle {
    private static final Logger logger= LoggerFactory.getLogger(SeckillExceptionHandle.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public SeckillResult<Object> handleException(Exception e){
        if (e instanceof SeckillException){
            SeckillException seckillException=(SeckillException)e;
            return new SeckillResult<Object>(seckillException.getCode(),seckillException.getMessage());
        }else{
            logger.error("系统错误:{}",e.getMessage());
            return new SeckillResult<Object>(SeckillEnum.SECKILL_INNER_ERROR.getCode(), SeckillEnum.SECKILL_INNER_ERROR.getMsg());
        }
    }
}
