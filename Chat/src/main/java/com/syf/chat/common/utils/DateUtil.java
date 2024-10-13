package com.syf.chat.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    public static final String FORMAT1 = "yyyyMMdd";
    public static final String FORMAT2 = "yyyy-MM-dd";
    public static final String FORMAT3 = "yyyy-MM-dd HH:mm:ss";

    public static Date getDate() {
        return new Date();
    }

    public static String getDate(String format) {
        // 定义格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        // 格式化当前日期
        return LocalDateTime.now().format(formatter);
    }

    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date stringToDate(String dateString, String format) {
        DateFormat fmt =new SimpleDateFormat(format);
        try {
            return fmt.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
