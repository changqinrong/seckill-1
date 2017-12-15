package com.sinosoft.seckill.dto;

import com.sinosoft.seckill.bean.SeckillSuccess;
import com.sinosoft.seckill.emun.SeckillEnum;

/**
 * 秒杀结果类
 */
public class ExeSeckillResult {
    private Long seckillId;//秒杀商品Id
    private int state;//秒杀状态
    private String state_info;//秒杀状态描述
    private SeckillSuccess seckillSuccess;//秒杀成功详细信息

    public ExeSeckillResult(Long seckillId, SeckillEnum seckillEnum, SeckillSuccess seckillSuccess) {
        this.seckillId = seckillId;
        this.state = seckillEnum.getCode();
        this.state_info = seckillEnum.getMsg();
        this.seckillSuccess = seckillSuccess;
    }

    public ExeSeckillResult(Long seckillId, SeckillEnum seckillEnum) {
        this.seckillId = seckillId;
        this.state = seckillEnum.getCode();
        this.state_info = seckillEnum.getMsg();
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getState_info() {
        return state_info;
    }

    public void setState_info(String state_info) {
        this.state_info = state_info;
    }

    public SeckillSuccess getSeckillSuccess() {
        return seckillSuccess;
    }

    public void setSeckillSuccess(SeckillSuccess seckillSuccess) {
        this.seckillSuccess = seckillSuccess;
    }

    @Override
    public String toString() {
        return "ExeSeckillResult{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", state_info='" + state_info + '\'' +
                ", seckillSuccess=" + seckillSuccess +
                '}';
    }
}
