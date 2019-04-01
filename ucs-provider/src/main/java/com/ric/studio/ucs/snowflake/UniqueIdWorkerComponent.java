package com.ric.studio.ucs.snowflake;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Richard_yyf
 * @version 1.0 2019/2/26
 */
@Component
@Slf4j
public class UniqueIdWorkerComponent {

    //    @Value("${server.clusterId}")
    @NacosValue(value = "${server.clusterId}")
    private long clusterId;

    //    @Value("${server.workerId}")
    @NacosValue("${server.workerId}")
    private long workId;

    private static volatile UniqueIdWorker instance;

    public UniqueIdWorker getInstance() {
        if (instance == null) {
            synchronized (UniqueIdWorker.class) {
                if (instance == null) {
                    log.info("init UniqueIdWorker instance, workId = {}, clusterId = {}", workId, clusterId);
                    instance = new UniqueIdWorker(workId, clusterId);
                }
            }
        }
        return instance;
    }
}
