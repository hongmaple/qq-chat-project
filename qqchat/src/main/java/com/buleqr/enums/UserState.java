package com.buleqr.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserState implements CodedEnum {
    /**
     *
     */
    UNKNOWN(0, "未知"),
    valid(1, "有效"),
    invalid(2, "无效")
    ;
    private final Integer code;

    private final String tag;
}