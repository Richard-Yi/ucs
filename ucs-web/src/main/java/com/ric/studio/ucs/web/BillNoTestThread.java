package com.ric.studio.ucs.web;

import com.ric.studio.ucs.api.UniqueControlReq;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.*;

/**
 * @author Richard_yyf
 * @version 1.0 2019/3/19
 */
public class BillNoTestThread implements Callable<String> {

    private UniqueControlReq req;

    private UcsConsumer ucsConsumer ;

    public BillNoTestThread(UniqueControlReq req, UcsConsumer ucsConsumer) {
        this.req = req;
        this.ucsConsumer = ucsConsumer;
    }

    @Override
    public String call() {
        return ucsConsumer.test(req).getMessage();
    }
}
