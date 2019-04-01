package com.ric.studio.ucs.api;

/**
 * 唯一控制服务 api
 * @author Richard_yyf
 * @version 1.0 2019/2/25
 */
public interface UniqueControlApi {

    /**
     * 获取 唯一流水号
     * @param req 请求实体
     * @return 返回实体
     * @author Richard_yyf 2019/2/25 
     */
    UniqueControlResult getUniqueBillNo(UniqueControlReq req);

    /**
     * 解析流水号中的企业代码
     * @param req 请求实体
     * @return 返回实体
     * @author Richard_yyf 2019/2/28
     */
    UniqueControlResult getEnterpriseNum(UniqueControlParseReq req);

    /**
     * 解析流水号
     * @param req 请求实体
     * @return 返回实体 解析内容以json字符串形式体现
     * @author Richard_yyf 2019/2/28
     */
    UniqueControlResult parseBillNo(UniqueControlParseReq req);

    /**
     * 获取渠道三流水号 (18位)
     * @author Richard_yyf 2019/2/28
     */
    UniqueControlResult getPosFlowNo(UniqueControlReq req);
}
