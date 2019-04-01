package com.ric.studio.ucs.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.ric.studio.ucs.api.UniqueControlApi;
import com.ric.studio.ucs.api.UniqueControlParseReq;
import com.ric.studio.ucs.api.UniqueControlReq;
import com.ric.studio.ucs.api.UniqueControlResult;
import com.ric.studio.ucs.billno.BillNoDealer;
import com.ric.studio.ucs.billno.BillNoDealerFactory;
import com.ric.studio.ucs.exception.InvalidBankCodeException;
import com.ric.studio.ucs.manager.BillNoManager;
import com.ric.studio.ucs.model.pojo.BillNoParseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Richard_yyf
 * @version 1.0 2019/2/25
 */
@Slf4j
@Service(timeout = 500)
public class UniqueControlService implements UniqueControlApi {

    @Autowired
    private BillNoManager billNoManager;

    @Autowired
    private BillNoDealerFactory billNoDealerFactory;

    @Override
    public UniqueControlResult getUniqueBillNo(UniqueControlReq req) {
        log.info("收到唯一流水号请求 {}" , req);
        try {
            BillNoDealer billNoDealer = billNoDealerFactory.makeBillNoDealer(req.getBankCode());
            return billNoDealer.getUniqueBillNo(req);
        } catch (InvalidBankCodeException ex) {
            return UniqueControlResult.error(ex.getMessage());
        } catch (Exception e) {
            log.error("未知错误", e);
            return UniqueControlResult.error("未知错误");
        }
    }

    @Override
    public UniqueControlResult getEnterpriseNum(UniqueControlParseReq req) {
        try {
            BillNoDealer billNoDealer = billNoDealerFactory.makeBillNoDealer(req.getBankCode());
            return billNoDealer.parseEntNumFromBillNo(req);
        } catch (InvalidBankCodeException ex) {
            return UniqueControlResult.error(ex.getMessage());
        } catch (Exception e) {
            log.error("未知错误", e);
            return UniqueControlResult.error("未知错误");
        }
    }

    @Override
    public UniqueControlResult parseBillNo(UniqueControlParseReq req) {
        try {
            BillNoDealer billNoDealer = billNoDealerFactory.makeBillNoDealer(req.getBankCode());
            BillNoParseVO billNoParseVO = billNoDealer.parseBillNo(req);
            return UniqueControlResult.success(JSON.toJSONString(billNoParseVO));
        }catch (InvalidBankCodeException ex) {
            return UniqueControlResult.error(ex.getMessage());
        } catch (Exception e) {
            log.error("未知错误", e);
            return UniqueControlResult.error("未知错误");
        }
    }

    @Override
    public UniqueControlResult getPosFlowNo(UniqueControlReq req) {
        try {
            return billNoManager.getPosFlowNo(req);
        } catch (Exception e) {
            log.error("未知错误", e);
            return UniqueControlResult.error("未知错误");
        }
    }
}
