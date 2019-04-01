package com.ric.studio.ucs.worker;

import com.alibaba.fastjson.JSON;
import com.ric.studio.ucs.api.UniqueControlReq;
import com.ric.studio.ucs.api.UniqueControlResult;
import com.ric.studio.ucs.billno.BillNoDealer;
import com.ric.studio.ucs.billno.BillNoDealerFactory;
import com.ric.studio.ucs.api.BankCodeEnum;
import com.ric.studio.ucs.exception.InvalidBankCodeException;
import com.ric.studio.ucs.manager.BillNoManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Richard_yyf
 * @version 1.0 2019/2/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SnowflakeTest {

    @Autowired
    private BillNoManager billNoManager;

    @Autowired
    private BillNoDealerFactory billNoDealerFactory;

    @Test
    public void snowflakeTest() {
        UniqueControlReq req = new UniqueControlReq("QT330001", BankCodeEnum.CHANNEL_ONE.getValue(), "0", "0");
        try {
            BillNoDealer billNoDealer = billNoDealerFactory.makeBillNoDealer(req.getBankCode());
            UniqueControlResult result = billNoDealer.getUniqueBillNo(req);
            System.out.println("请求结果：" + JSON.toJSONString(result));
        } catch (InvalidBankCodeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反解析测试
     */
    @Test
    public void parseTest() {
    }


}
