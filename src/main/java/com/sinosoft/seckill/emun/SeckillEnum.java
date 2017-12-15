package com.sinosoft.seckill.emun;

/**
 * 执行秒杀结果状态枚举
 */
public enum SeckillEnum {
    SUCCESS(0,"成功"),
    SECKILL_CLOSE(-1,"秒杀关闭"),
    SECKILL_REPEAT(-2,"重复秒杀"),
    SECKILL_MODIFY(-3,"信息被串改"),
    SECKILL_INNER_ERROR(-4,"系统错误")
    ;
    private int code;
    private String msg;


    SeckillEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
