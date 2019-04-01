package com.ric.studio.ucs;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Richard_yyf
 * @version 1.0 2019/4/1
 */

@Controller
@RequestMapping("/config")
public class NacosConfigTestController {

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private String useLocalCache;

    @GetMapping("/get")
    @ResponseBody
    public String get() {
        return useLocalCache;
    }

}
