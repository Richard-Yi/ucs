package com.ric.studio.ucs.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Richard_yyf
 * @version 1.0 2019/2/27
 */

@AllArgsConstructor
@Getter
@ToString
public class BillNoParseDTO {

    private Long sequence;
    private Long workerId;
    private Long clusterId;
    private Long timeStamp;

}
