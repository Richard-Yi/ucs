package com.ric.studio.ucs.manager;

import com.ric.studio.ucs.api.UniqueControlReq;
import com.ric.studio.ucs.api.UniqueControlResult;
import com.ric.studio.ucs.snowflake.UniqueIdWorkerComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 *
 * @author Richard_yyf
 * @version 1.0 2019/2/26
 */
@Component
public class BillNoManager {

    @Autowired
    private UniqueIdWorkerComponent uniqueIdWorkerComponent;

    /**
     * 获取渠道三流水号 18位
     * @param req
     * @return
     */
    public UniqueControlResult getPosFlowNo(UniqueControlReq req) {
        String productCode = req.getProductId();
        String nodeCode = req.getNodeId();
        String uniqueNo = Long.toString(uniqueIdWorkerComponent.getInstance().nextId());
        return UniqueControlResult.success(productCode + nodeCode + uniqueNo);
    }
}
