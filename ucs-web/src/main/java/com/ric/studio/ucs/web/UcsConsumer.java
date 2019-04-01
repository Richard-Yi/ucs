package com.ric.studio.ucs.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ric.studio.ucs.api.*;
import org.springframework.stereotype.Component;

/**
 * @author Richard_yyf
 * @version 1.0 2019/2/28
 */
@Component("ucsConsumer")
public class UcsConsumer {

    @Reference
    private UniqueControlApi uniqueControlApi;

    public UniqueControlResult test(UniqueControlReq req) {
        return uniqueControlApi.getUniqueBillNo(req);
    }

    public UniqueControlResult testParseEnt(UniqueControlParseReq req) {
        return uniqueControlApi.getEnterpriseNum(req);
    }

    public UniqueControlResult testParse(UniqueControlParseReq req) {
        return uniqueControlApi.parseBillNo(req);
    }

    public void testCapacity() {
        UniqueControlReq req = new UniqueControlReq("QT330001", "Z310", UcsNumConstants.NUM_0, UcsNumConstants.NUM_0);
        long start = System.currentTimeMillis();
        for (int i=0; i<100000; i++) {
            System.out.println(uniqueControlApi.getUniqueBillNo(req).getMessage());
        }
        System.out.println(String.format("==========100000条流水 耗时 : %s ms", System.currentTimeMillis() - start));
    }
}
