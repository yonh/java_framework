package org.yonh.util;


import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类
 */

public final class StringUtil {
    public static boolean isEmpty(String s) {
        if (s != null) {
            s = s.trim();
        }

        return StringUtils.isEmpty(s);
    }

    public static  boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }
}
