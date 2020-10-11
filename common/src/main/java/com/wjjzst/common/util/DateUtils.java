package com.wjjzst.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Wjj
 * @Date: 2020/10/12 1:28 上午
 * @desc:
 */
public class DateUtils {
    public static final String YEAR_MONTH_NUMBER = "yyyyMM";

    public static String date2Str(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
