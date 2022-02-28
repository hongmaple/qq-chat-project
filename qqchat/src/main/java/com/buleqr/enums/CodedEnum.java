package com.buleqr.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author buleqr
 */
public interface CodedEnum {
    /**
     * 获取枚举编码
     * @return 枚举编码
     */
    Integer getCode();

    /**
     * 获取详细的描述信息
     * @return 描述信息
     */
    String getTag();

    /**
     * 根据code获取枚举值
     * @param enumClass 枚举class
     * @param code 枚举编码
     * @param <E> 枚举类
     * @return 枚举值
     */
    static <E extends Enum<?> & CodedEnum> Optional<E> codeOf(Class<E> enumClass, Integer code) {
        return Arrays.stream(enumClass.getEnumConstants()).filter(e -> e.getCode().equals(code)).findFirst();
    }

    /**
     * 根据enumName获取枚举值
     * @param enumClass 枚举class
     * @param enumName 枚举名
     * @param <E> 枚举类
     * @return 枚举值
     */
    static <E extends Enum<?> & CodedEnum> Optional<E> codeOf(Class<E> enumClass, String enumName) {
        return Arrays.stream(enumClass.getEnumConstants()).filter(e -> e.toString().equals(enumName)).findFirst();
    }
}