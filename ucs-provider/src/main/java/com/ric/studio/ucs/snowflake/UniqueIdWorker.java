package com.ric.studio.ucs.snowflake;


import com.ric.studio.ucs.model.pojo.BillNoParseDTO;

/**
 * SnowFlake 的结构如下(每部分用-分开):<br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
 * 参考Twitter Snowflake算法，按实际需求，做了部分修改，结构如下(每部分用-分开):
 * 0000000000 - 00000000000000000000000000000000000000000- 00 - 000 - 00000000 <br
 * 10位不使用，因为目的是为了最终生成16位整数，所以只使用后面的54bit
 * 41位时间截(毫秒级)，存储时间截的差值（当前时间截 - 开始时间截)，41位的时间截，可以使用69年，且考虑到差值较小时，会生成不足16位的数字，因些需要选择一个合适的值
 * 2位的集群ID，可以部署在4个集群
 * 3位的节点ID，每个集群可以有8个节点
 * 8位序列，毫秒内的计数，支持每个节点每毫秒产生256个ID序号
 * 加起来刚好64位，为一个Long型
 *
 * @author Richard_yyf
 * @version 1.0 2019/2/26
 */


public class UniqueIdWorker {
    /**
     * 起始时间，用于调整位数
     * 这里取值 2012-12-22 00:00:00
     * 以41位表示毫秒，此方案可以使用到 2082-08-28 15:47:35，订单编号从15开头，
     */
    private final long baseTimestamp = 604553702000L;

    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 3L;

    /**
     * 集群id所占的位数
     */
    private final long clusterIdBits = 2L;

    /**
     * 支持的最大机器id
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 支持的最大集群id
     */
    private final long maxClusterId = -1L ^ (-1L << clusterIdBits);

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 8L;

    /**
     * 机器ID向左移位数
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 集群id向左移位数
     */
    private final long clusterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间截向左移位数
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + clusterIdBits;

    /**
     * 生成序列的掩码
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 工作机器ID
     */
    private long workerId;

    /**
     * 集群ID
     */
    private long clusterId;

    /**
     * 毫秒内序列
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    /**
     * 构造函数
     *
     * @param workerId
     * @param clusterId
     */
    public UniqueIdWorker(long workerId, long clusterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (clusterId > maxClusterId || clusterId < 0) {
            throw new IllegalArgumentException(String.format("clusterId Id can't be greater than %d or less than 0", maxClusterId));
        }
        this.workerId = workerId;
        this.clusterId = clusterId;
    }

    /**
     * 获得下一个ID
     * * @return
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        //系统时钟回退，抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards. Failed to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //同一毫秒内顺序递增
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变重置为0
        else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - baseTimestamp) << timestampLeftShift)
                | (clusterId << clusterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒的时间戳并返回
     *
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回当前毫秒时间戳
     *
     * @return
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 根据订单ID反向解析内容
     *
     * @param id
     * @return
     */
    public BillNoParseDTO parseId(Long id) {
        if (null == id) {
            return null;
        }
        return new BillNoParseDTO(((id) & ~(-1L << sequenceBits))
                , ((id >> (workerIdShift)) & ~(-1L << (workerIdBits)))
                , ((id >> clusterIdShift) & ~(-1L << clusterIdBits))
                , ((id >> timestampLeftShift) + baseTimestamp));
    }

}
