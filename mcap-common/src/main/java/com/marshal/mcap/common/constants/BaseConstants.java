package com.marshal.mcap.common.constants;

/**
 * Created with IntelliJ IDEA.
 * author: Marshal
 * Date: 2018/11/2
 * Time: 23:17
 * Description:系统常量
 */
public interface BaseConstants {
    /**
     * 基本常量 - 是、否 标记.
     */
    String YES = "Y";

    String NO = "N";


    /**
     * 时间格式
     */
    String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 正则表达式-phone.
     */
    String PATTERN_PHONE_REGEX = "^1[3|4|5|8][0-9]\\d{4,8}";

    /**
     * 格式错误信息
     */
    String PHONE_NOT_VALID = "手机号码格式不正确!";

}
