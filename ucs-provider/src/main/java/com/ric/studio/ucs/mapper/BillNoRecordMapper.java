package com.ric.studio.ucs.mapper;

import com.ric.studio.ucs.model.domain.BillNoRecord;
import org.apache.ibatis.annotations.*;

/**
 * @author Richard_yyf
 * @version 1.0 2019/2/28
 */

public interface BillNoRecordMapper {

    @Select("SELECT * FROM BILLNORECORD WHERE BILLNO = #{billNo}")
    @Results({
            @Result(property = "billNo", column = "BILLNO"),
            @Result(property = "enterpriseNum", column = "ENTERPRISENUM"),
            @Result(property = "bankCode", column = "BANKCODE"),
            @Result(property = "productId", column = "PRODUCTID"),
            @Result(property = "nodeId", column = "NODEID")
    })
    BillNoRecord getByBillNo(String billNo);

    @Insert("INSERT INTO BILLNORECORD(BILLNO, ENTERPRISENUM, BANKCODE, PRODUCTID, NODEID) " +
            "VALUES (#{billNo}, #{enterpriseNum}, #{bankCode}, #{productId}, #{nodeId})")
    void insert(BillNoRecord billNoRecord);

    @Select("SELECT ENTERPRISENUM FROM BILLNORECORD WHERE BILLNO = #{billNo}")
    String getEntNumByBillNo(String billNo);
}
