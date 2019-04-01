package com.ric.studio.ucs.model.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 流水号反解析VO
 * @author Richard_yyf
 * @version 1.0 2019/2/27
 */
@Builder
@Setter
@Getter
@ToString
public class BillNoParseVO {

    private Boolean success;
    private String msg;

    /** 企业号 */
    private String enterpriseNum;
    /** 产品ID */
    private String productId;
    /** 产品的节点ID */
    private String nodeId;
    /** 时间 */
    private String date;
    /** 数据产生集群id */
    private String clusterId;
    /** 数据产生节点id */
    private String workerId;
    /** 毫秒内序列 */
    private String sequence;

}
