package com.ric.studio.ucs.billno.dealer;

import com.ric.studio.ucs.api.UniqueControlParseReq;
import com.ric.studio.ucs.api.UniqueControlReq;
import com.ric.studio.ucs.api.UniqueControlResult;
import com.ric.studio.ucs.api.BankCodeEnum;
import com.ric.studio.ucs.billno.BillNoDealer;
import com.ric.studio.ucs.mapper.BillNoRecordMapper;
import com.ric.studio.ucs.model.domain.BillNoRecord;
import com.ric.studio.ucs.model.pojo.BillNoParseDTO;
import com.ric.studio.ucs.model.pojo.BillNoParseVO;
import com.ric.studio.ucs.snowflake.UniqueIdWorkerComponent;
import com.ric.studio.ucs.util.BillNoRuleUtil;
import com.ric.studio.ucs.util.ConcurrentDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 渠道一 流水号 处理器
 * @author Richard_yyf
 * @version 1.0 2019/3/1
 */

@Component("billNoDealerForChannelOne")
public class BillNoDealerForChannelOne implements BillNoDealer {

    @Autowired
    private UniqueIdWorkerComponent uniqueIdWorkerComponent;

    @SuppressWarnings("all")
    @Autowired
    private BillNoRecordMapper billNoRecordMapper;

    @Override
    public BillNoParseVO parseBillNo(UniqueControlParseReq req) {
        String billNo = req.getBillNo();
        // 产品位
        String productId = billNo.substring(0, 1);
        String productNum = BillNoRuleUtil.convertCodeStr2NumStr(productId);
        // 节点位
        String nodeId = billNo.substring(1, 2);
        String nodeNum = BillNoRuleUtil.convertCodeStr2NumStr(nodeId);
        // 16位
        String uniqueId = billNo.substring(2);
        BillNoParseDTO parseDTO = uniqueIdWorkerComponent.getInstance().parseId(Long.valueOf(uniqueId));
        // 转化日期格式
        String date = ConcurrentDateUtil.format(new Date(parseDTO.getTimeStamp()));
        return BillNoParseVO.builder()
                .success(true).date(date)
                .sequence(Long.toString(parseDTO.getSequence()))
                .clusterId(Long.toString(parseDTO.getClusterId()))
                .workerId(Long.toString(parseDTO.getWorkerId()))
                .nodeId(nodeNum).productId(productNum).build();
    }

    @Override
    public UniqueControlResult getUniqueBillNo(UniqueControlReq req) {
        // 16位
        String uniqueNo = Long.toString(uniqueIdWorkerComponent.getInstance().nextId());
        // 1+1+16
        String billNo = req.getProductId() + req.getNodeId() + uniqueNo;
        // 入库
        BillNoRecord billNoRecord = new BillNoRecord(billNo, req.getEnterpriseNum(),
                BankCodeEnum.CHANNEL_ONE.getValue(), req.getProductId(), req.getNodeId());
        billNoRecordMapper.insert(billNoRecord);
        return UniqueControlResult.success(billNo);
    }

    @Override
    public UniqueControlResult parseEntNumFromBillNo(UniqueControlParseReq req) {
        String entNum = billNoRecordMapper.getEntNumByBillNo(req.getBillNo());
        return UniqueControlResult.success(entNum);
    }
}
