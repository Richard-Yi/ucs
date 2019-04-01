package com.ric.studio.ucs.web;

import com.alibaba.fastjson.JSON;
import com.ric.studio.ucs.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Richard_yyf
 * @version 1.0 2019/2/27
 */

@Controller
@RequestMapping("/ucstest")
public class TestController {

//    @Reference(version = "1.0")
//    private UniqueControlApi uniqueControlApi;

    @Autowired
    private UcsConsumer ucsConsumer;

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/concurrenttestindex")
    public String concurrenttestindex() {
        return "concurrenttestindex";
    }

    @PostMapping("/getBillNo")
    @ResponseBody
    public UniqueControlResult getBillNo(String enterpriseNum, String bankCode,
                                         String productId, String nodeId) {
        UniqueControlReq req = new UniqueControlReq(enterpriseNum, bankCode,
                productId, nodeId);
        return ucsConsumer.test(req);
    }

    @PostMapping("/concurrentTest")
    @ResponseBody
    public String concurrentTest(String enterpriseNum, String bankCode,
                                 String productId, String nodeId, String threadNum) {
        UniqueControlReq req = new UniqueControlReq(enterpriseNum, bankCode, productId, nodeId);
        int num = Integer.valueOf(threadNum);
        List<Callable<String>> list = new ArrayList<>(num);
        for (int i=0; i < num; i++) {
            list.add(new BillNoTestThread(req, ucsConsumer));
        }
        long start = System.currentTimeMillis();
        List<String> list1 = ThreadPool.addTask(list);
        System.out.println("耗时：" + Long.toString(System.currentTimeMillis() - start) + "ms");
        return JSON.toJSONString(list1);
    }

    @PostMapping("/parse")
    @ResponseBody
    public UniqueControlResult parseBillNo(String billNo, String bankCode) {
        UniqueControlParseReq req = new UniqueControlParseReq(bankCode, billNo);
        return ucsConsumer.testParse(req);
    }

    @PostMapping("/getEntNum")
    @ResponseBody
    public UniqueControlResult getEntNum(String billNo, String bankCode) {
        UniqueControlParseReq req = new UniqueControlParseReq(bankCode, billNo);
        return ucsConsumer.testParseEnt(req);
    }

    @GetMapping("/test")
    public void test(String billNo, String bankCode) {
       ucsConsumer.testCapacity();
    }
}
