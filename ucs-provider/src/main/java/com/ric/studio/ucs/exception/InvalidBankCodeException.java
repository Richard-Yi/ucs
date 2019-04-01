package com.ric.studio.ucs.exception;

/**
 * 不支持的银行代码
 * @author Richard_yyf
 * @version 1.0 2019/3/1
 */

public class InvalidBankCodeException extends Exception {

    public InvalidBankCodeException(String str) {
        super(str);
    }

    public InvalidBankCodeException(Throwable ex) {
        super(ex);
    }

    public InvalidBankCodeException(String str, Throwable ex) {
        super(str, ex);
    }
}
