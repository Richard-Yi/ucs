package com.ric.studio.ucs.worker;

import org.junit.Test;

/**
 * @author Richard_yyf
 * @version 1.0 2019/2/26
 */
public class NormalTest {

    /**
     * 1天后  14位
     * 下个月 15位
     * 一年后 16位
     * 5年后  17位
     * 10年   17位
     * 12年后 18位
     * 50年后 18位
     *
     */
    @Test
    public void snowflakeTest() {
        long sequenceBits = 12L;
        long timestamp = 3129177238000L;
        long twepoch = 1551167638000L;
        long workerIdBits = 3L;
        long datacenterIdBits = 3L;
        /** 时间截向左移 22 位(5+5+12) */
        long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
        /** 工作机器 ID(0~31) */
        long workerId = 0;
        /** 数据中心 ID(0~31) */
        long datacenterId = 0;
        /** 毫秒内序列(0~4095) */
        long sequence = 0L;
        /** 上次生成 ID 的时间截 */
        long lastTimestamp = -1L;
        long datacenterIdShift = sequenceBits + workerIdBits;
        long workerIdShift = sequenceBits;
        long id =  ((timestamp - twepoch) << timestampLeftShift) //
                | (datacenterId << datacenterIdShift) //
                | (workerId << workerIdShift) //
                | sequence;
        System.out.println("idWorker0：" + id);
        System.out.println("idWorker0：" + Long.toBinaryString(id));
    }


}
