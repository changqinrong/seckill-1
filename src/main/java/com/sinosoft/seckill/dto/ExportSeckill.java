package com.sinosoft.seckill.dto;

/**
 * 暴露秒杀地址
 */
public class ExportSeckill {
    private  boolean exposer;//秒杀开启标识
    private Long seckillId;//秒杀产品id
    private Long currTime;//当前系统时间
    private Long startTime;//秒杀开始时间
    private Long endTime;//秒杀结束时间
    private String md5;//加密串


    public ExportSeckill(boolean exposer, Long seckillId, String md5) {
        this.exposer = exposer;
        this.seckillId = seckillId;
        this.md5 = md5;
    }

    public ExportSeckill(boolean exposer, Long seckillId) {
        this.exposer = exposer;
        this.seckillId = seckillId;
    }

    public ExportSeckill(boolean exposer, Long seckillId, Long currTime, Long startTime, Long endTime) {
        this.exposer = exposer;
        this.seckillId = seckillId;
        this.currTime = currTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isExposer() {
        return exposer;
    }

    public void setExposer(boolean exposer) {
        this.exposer = exposer;
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Long getCurrTime() {
        return currTime;
    }

    public void setCurrTime(Long currTime) {
        this.currTime = currTime;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Override
    public String toString() {
        return "ExportSeckill{" +
                "exposer=" + exposer +
                ", seckillId=" + seckillId +
                ", currTime=" + currTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", md5='" + md5 + '\'' +
                '}';
    }
}
