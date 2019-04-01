package com.ric.studio.ucs.billno.dealer;

import com.ric.studio.ucs.api.UniqueControlParseReq;
import com.ric.studio.ucs.api.UniqueControlReq;
import com.ric.studio.ucs.api.UniqueControlResult;
import com.ric.studio.ucs.billno.BillNoDealer;
import com.ric.studio.ucs.model.pojo.BillNoParseDTO;
import com.ric.studio.ucs.model.pojo.BillNoParseVO;
import com.ric.studio.ucs.snowflake.UniqueIdWorkerComponent;
import com.ric.studio.ucs.util.BillNoRuleUtil;
import com.ric.studio.ucs.util.ConcurrentDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Richard_yyf
 * @version 1.0 2019/3/1
 */

@Component("normalBillNoDealer")
public class NormalBillNoDealer implements BillNoDealer {

    @Autowired
    private UniqueIdWorkerComponent uniqueIdWorkerComponent;

    @Override
    public BillNoParseVO parseBillNo(UniqueControlParseReq req) {
        String billNo = req.getBillNo();
        // 产品位
        String productId = billNo.substring(0, 1);
        String productNum = BillNoRuleUtil.convertCodeStr2NumStr(productId);
        // 节点位
        String nodeId = billNo.substring(1, 2);
        String nodeNum = BillNoRuleUtil.convertCodeStr2NumStr(nodeId);

        String enterpriseNum = billNo.substring(2,10);
        String uniqueId = billNo.substring(10);
        BillNoParseDTO parseDTO = uniqueIdWorkerComponent.getInstance().parseId(Long.valueOf(uniqueId));
        // 转化日期格式
        String date = ConcurrentDateUtil.format(new Date(parseDTO.getTimeStamp()));
        return BillNoParseVO.builder()
                .success(true).date(date)
                .nodeId(nodeNum).productId(productNum)
                .sequence(Long.toString(parseDTO.getSequence()))
                .clusterId(Long.toString(parseDTO.getClusterId()))
                .workerId(Long.toString(parseDTO.getWorkerId()))
                .enterpriseNum(enterpriseNum)
                .build();
    }

    @Override
    public UniqueControlResult getUniqueBillNo(UniqueControlReq req) {
        String uniqueNo = Long.toString(uniqueIdWorkerComponent.getInstance().nextId());
        String billNo = req.getProductId() + req.getNodeId() + req.getEnterpriseNum() + uniqueNo;
        return UniqueControlResult.success(billNo);
    }

    @Override
    public UniqueControlResult parseEntNumFromBillNo(UniqueControlParseReq req) {
        return UniqueControlResult.success(req.getBillNo().substring(2,10));
    }
}
