package com.ric.studio.ucs.billno;

import com.ric.studio.ucs.api.UniqueControlParseReq;
import com.ric.studio.ucs.api.UniqueControlReq;
import com.ric.studio.ucs.api.UniqueControlResult;
import com.ric.studio.ucs.model.pojo.BillNoParseVO;

/**
 *
 * 渠道一流水号：
 *      最高19位 产品ID + 节点ID + UUID = 1+1+16= 18
 *      渠道一流水号需要入库
 * 渠道二流水号：
 *      位数：16 UUID
 * 渠道三流水号：
 *      位数：18
 *      规则：产品ID + 节点ID + UUID
 * 其他流水号：
 *      位数：26位 产品ID + 节点ID + 企业编号 + UUID = 1+1+8+16 = 26
 *
 * 渠道二 最高16位 2+14 -企业号映射-uuid
 *
 * 流水号请求 处理者 抽象类
 * @author Richard_yyf
 * @version 1.0 2019/3/1
 */
public interface BillNoDealer {

    /**
     * 反解析流水号
     * @param req 请求实体
     * @return BillNoParseVO
     * @author Richard_yyf 2019/3/1
     */
    BillNoParseVO parseBillNo(UniqueControlParseReq req);

    /**
     * 获取唯一流水号
     * @param req 请求实体
     * @return UniqueControlResult
     * @author Richard_yyf 2019/3/1
     */
    UniqueControlResult getUniqueBillNo(UniqueControlReq req);

    /**
     * 获取唯一流水号
     * @param req 请求实体
     * @return UniqueControlResult
     * @author Richard_yyf 2019/3/1
     */
    UniqueControlResult parseEntNumFromBillNo(UniqueControlParseReq req);
}
