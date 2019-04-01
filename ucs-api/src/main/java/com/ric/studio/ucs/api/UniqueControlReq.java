package com.ric.studio.ucs.api;

import java.io.Serializable;

/**
 * 微控制服务请求
 * @author Richard_yyf
 * @version 1.0 2019/2/25
 */
public class UniqueControlReq implements Serializable {

    /**
     * 企业号
     */
    private String enterpriseNum;
    /**
     * 银行代码
     */
    private String bankCode;
    /**
     * 产品id  varchar(1)
     */
    private String productId;
    /**
     * 节点id varchar(1)
     */
    private String nodeId;

    public UniqueControlReq(String enterpriseNum, String bankCode, String productId, String nodeId) {
        this.enterpriseNum = enterpriseNum;
        this.bankCode = bankCode;
        this.productId = productId;
        this.nodeId = nodeId;
    }

    public String getEnterpriseNum() {
        return enterpriseNum;
    }

    public void setEnterpriseNum(String enterpriseNum) {
        this.enterpriseNum = enterpriseNum;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public String toString() {
        return "UniqueControlReq{" +
                "enterpriseNum='" + enterpriseNum + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", productId='" + productId + '\'' +
                ", nodeId='" + nodeId + '\'' +
                '}';
    }
}
