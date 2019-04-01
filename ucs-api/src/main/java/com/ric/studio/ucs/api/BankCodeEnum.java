package com.ric.studio.ucs.api;

/**
 * 渠道代码枚举类
 * @author Richard_yyf
 * @version 1.0 2019/2/26
 */
public enum BankCodeEnum {

    CHANNEL_ONE("1","渠道一"),
    CHANNEL_TWO("2","渠道二"),
    CHANNEL_OTHERS("0","渠道三");

    private String value;
    private String label;

    BankCodeEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }


    public String getLabel() {
        return label;
    }

    public static BankCodeEnum convertByValue(String value) {
        for (BankCodeEnum st : BankCodeEnum.values()) {
            if (st.getValue().equals(value)) {
                return st;
            }
        }
        return null;
    }

    public static BankCodeEnum convertByLabel(String label) {
        for (BankCodeEnum st : BankCodeEnum.values()) {
            if (st.getLabel().equals(label)) {
                return st;
            }
        }
        return null;
    }
}
