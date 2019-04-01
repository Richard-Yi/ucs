package com.ric.studio.ucs.billno;

import com.ric.studio.ucs.api.BankCodeEnum;
import com.ric.studio.ucs.exception.InvalidBankCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Richard_yyf
 * @version 1.0 2019/3/1
 */
@Component
public class BillNoDealerFactory {

    @Autowired
    private Map<String, BillNoDealer> billNoDealerMap;

    public BillNoDealer makeBillNoDealer(String bankCode) throws InvalidBankCodeException {
        BankCodeEnum bankCodeEnum = BankCodeEnum.convertByValue(bankCode);
        if (bankCodeEnum == null) {
           throw new InvalidBankCodeException(String.format("Invalid BankCode : %s", bankCode));
        }
        switch (bankCodeEnum) {
            case CHANNEL_ONE:
                // 渠道一
                return billNoDealerMap.get("bocBillNoDealer");
            case CHANNEL_TWO:
                // 渠道二
                return billNoDealerMap.get("cmbBillNoDealer");
            default:
                // 其他
                return billNoDealerMap.get("normalBillNoDealer");
        }
    }
}
