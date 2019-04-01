package com.ric.studio.ucs.billno.dealer;

import com.ric.studio.ucs.api.UniqueControlParseReq;
import com.ric.studio.ucs.api.UniqueControlReq;
import com.ric.studio.ucs.api.UniqueControlResult;
import com.ric.studio.ucs.billno.BillNoDealer;
import com.ric.studio.ucs.model.pojo.BillNoParseDTO;
import com.ric.studio.ucs.model.pojo.BillNoParseVO;
import com.ric.studio.ucs.snowflake.UniqueIdWorkerComponent;
import com.ric.studio.ucs.util.ConcurrentDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Richard_yyf
 * @version 1.0 2019/3/1
 */

@Component("billNoDealerForChannelTwo")
public class BillNoDealerForChannelTwo implements BillNoDealer {

    @Autowired
    private UniqueIdWorkerComponent uniqueIdWorkerComponent;

    @Override
    public BillNoParseVO parseBillNo(UniqueControlParseReq req) {
        BillNoParseDTO parseDTO = uniqueIdWorkerComponent.getInstance().parseId(Long.valueOf(req.getBillNo()));
        // 转化日期格式
        String date = ConcurrentDateUtil.format(new Date(parseDTO.getTimeStamp()));
        return BillNoParseVO.builder()
                .success(true).date(date)
                .sequence(Long.toString(parseDTO.getSequence()))
                .clusterId(Long.toString(parseDTO.getClusterId()))
                .workerId(Long.toString(parseDTO.getWorkerId()))
                .build();
    }

    @Override
    public UniqueControlResult getUniqueBillNo(UniqueControlReq req) {
        return UniqueControlResult.success(Long.toString(uniqueIdWorkerComponent.getInstance().nextId()));
    }

    @Override
    public UniqueControlResult parseEntNumFromBillNo(UniqueControlParseReq req) {
        return UniqueControlResult.error("对应银行编号服务暂未支持");
    }
}
