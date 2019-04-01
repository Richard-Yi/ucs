package com.ric.studio.ucs.api;

import java.io.Serializable;

/**
 * 解析流水号请求 实体
 * @author Richard_yyf
 * @version 1.0 2019/2/28
 */
public class UniqueControlParseReq implements Serializable {

    /**
     * 银行代码
     */
    private String bankCode;

    /**
     * 流水号
     */
    private String billNo;

    public UniqueControlParseReq(String bankCode, String billNo) {
        this.bankCode = bankCode;
        this.billNo = billNo;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }
}
