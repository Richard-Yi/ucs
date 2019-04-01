package com.ric.studio.ucs.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * @author Richard_yyf
 * @version 1.0 2019/2/25
 */

@AllArgsConstructor
@Setter
@Getter
public class BillNoRecord {

    /**
     * 流水号 同时也是主键
     */
    private String billNo;

    /**
     * 企业号
     */
    private String enterpriseNum;
    /**
     * 银行代码
     */
    private String bankCode;
    /**
     * 数据中心id - 产品
     */
    private String productId;
    /**
     * 机器id - 节点（机器）
     */
    private String nodeId;


}
