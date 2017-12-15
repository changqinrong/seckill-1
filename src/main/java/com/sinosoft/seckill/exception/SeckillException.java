package com.sinosoft.seckill.exception;

import com.sinosoft.seckill.emun.SeckillEnum;

/**
 * 秒杀异常类
 */
public class SeckillException extends RuntimeException {
    private int code;//错误码

    public SeckillException(SeckillEnum seckillEnum) {
        super(seckillEnum.getMsg());
        this.code = seckillEnum.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
