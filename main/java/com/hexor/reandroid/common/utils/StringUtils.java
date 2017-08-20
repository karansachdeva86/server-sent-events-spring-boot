package com.hexor.reandroid.common.utils;

import com.hexor.reandroid.common.utils.exception.UtilsException;

/**
 * Created by karan on 12/3/15.
 */
public class StringUtils {
    public static void isBlank(String key,String value) throws UtilsException {
        if (org.apache.commons.lang.StringUtils.isBlank(value))
            throw new UtilsException(key + " is null or empty in the request");
    }

    public static boolean isBlank(String value){
        return org.apache.commons.lang.StringUtils.isBlank(value);
    }
}
