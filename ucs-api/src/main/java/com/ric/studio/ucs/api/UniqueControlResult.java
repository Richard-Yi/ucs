package com.ric.studio.ucs.api;

import java.io.Serializable;

/**
 * 唯一控制服务 返回实体
 * @author Richard_yyf
 * @version 1.0 2019/2/25
 */

public class UniqueControlResult implements Serializable {

    /** 1-成功 0-失败 */
    private String code = "";

    /** 成功时返流水号 */
    private String message = "";

    public UniqueControlResult() {
    }

    public UniqueControlResult(String code) {
        this.code = code;
    }

    public UniqueControlResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static UniqueControlResult error(String msg) {
        return new UniqueControlResult("0",msg);
    }
    public static UniqueControlResult success(String msg) {
        return new UniqueControlResult("1",msg);
    }
}
