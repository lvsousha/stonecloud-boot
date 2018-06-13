package com.example.config.security;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by zli on 2017/6/21.
 *
 * 日期工具
 */
public class Java8DateUtil {

  public static final String DATE_FORMAT = "yyyy-MM-dd";

  public static final String TIME_FORMAT = "HH:mm:ss";

  public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

  /**
   * 00：00：00
   */
  public static final String START_TIME = " 00:00:00";
  /**
   * 59:59:59
   */
  public static final String END_TIME = " 23:59:59";

  /**
   * 获取日期
   */
  public static Date getDate(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
    ZoneId zone = ZoneId.systemDefault();
    Instant instant = localDateTime.atZone(zone).toInstant();
    return Date.from(instant);
  }

  public static Date getDate(LocalDate localDate) {
    ZoneId zone = ZoneId.systemDefault();
    Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
    return Date.from(instant);
  }

  public static Date getDate(LocalDateTime localDateTime) {
    ZoneId zoneId = ZoneId.systemDefault();
    ZonedDateTime zdt = localDateTime.atZone(zoneId);
    return Date.from(zdt.toInstant());
  }

  public static LocalDateTime getLocalDateTime(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return LocalDateTime.parse(date, formatter);
  }

  public static LocalDateTime getLocalDateTime(Date date) {
    Instant instant = date.toInstant();
    ZoneId zone = ZoneId.systemDefault();
    return LocalDateTime.ofInstant(instant, zone);
  }

  public static LocalDate getLocalDate(Date date) {
    Instant instant = date.toInstant();
    ZoneId zoneId = ZoneId.systemDefault();
    // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
    return instant.atZone(zoneId).toLocalDate();
  }

  /**
   * @param dateStr yyyy-MM-dd 格式
   */
  public static LocalDate getLocalDate(String dateStr) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return LocalDate.parse(dateStr, formatter);
  }

  public static void main(String[] args) {
  }

  /***
   * 返回当前年，如：2015
   *
   * @return
   */
  public static String getCurrentYear() {
    return String.valueOf(LocalDate.now().getYear());
  }

  /***
   * 返回当前月,如：07
   *
   * @return
   */
  public static String getCurrentMonth() {
    String month = String.valueOf(LocalDate.now().getMonthValue());
    if (month.length() == 1) {
      return "0" + month;
    }
    return month;
  }

  /***
   * 返回当前日,如：26
   *
   * @return
   */
  public static String getCurrentDayOfMonth() {
    String day = String.valueOf(LocalDate.now().getDayOfMonth());
    if (day.length() == 1) {
      return "0" + day;
    }
    return day;
  }

  /**
   * 格式化
   *
   * @param date 日期
   * @param pattern 格式化格式
   * @return string型日期
   */
  public static String formatter(Date date, String pattern) {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
    LocalDateTime localDateTime = getLocalDateTime(date);
    return localDateTime.format(dateFormatter);
  }

}
