package org.yonh.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 数据转换工具类
 */

public final class CastUtil {

    /**
     * 转换字符串
     *
     * @param obj
     * @return
     */
    public static String castString(Object obj) {
        return CastUtil.castString(obj, "");
    }

    /**
     * 转换字符串
     *
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String castString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    public static double castDouble(Object obj) {
        return CastUtil.castDouble(obj, 0);
    }

    /**
     * 转换为double类型
     *
     * @param obj
     * @param defaultValue
     * @return
     */
    public static double castDouble(Object obj, double defaultValue) {
        double value = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    value = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }

        return value;
    }

    public static long castLong(Object obj) {
        return CastUtil.castLong(obj, 0L);
    }

    public static long castLong(Object obj, long defaultValue) {
        long value = defaultValue;

        if (null == obj) return value;

        String strValue = castString(obj);

        if (StringUtil.isEmpty(strValue)) return value;

        try {
            value = Long.parseLong(strValue);
        } catch (NumberFormatException e) {
            value = defaultValue;
        }

        return value;
    }

    public static int castInt(Object obj) {

        return CastUtil.castInt(obj, 0);
    }

    public static int castInt(Object obj, int defaultValue) {
        int value = defaultValue;

        if (null == obj) return value;

        String strValue = castString(obj);

        if (StringUtil.isEmpty(strValue)) return value;

        try {
            value = Integer.parseInt(strValue);
        } catch (NumberFormatException e) {
            value = defaultValue;
        }

        return value;
    }

    public static boolean castBoolean(Object obj) {
        return CastUtil.castBoolean(obj, false);
    }

    public static boolean castBoolean(Object obj, boolean defaultValue) {
        if (obj==null) return defaultValue;

        return Boolean.parseBoolean(castString(obj));
    }
}
