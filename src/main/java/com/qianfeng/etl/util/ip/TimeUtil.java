package com.qianfeng.etl.util.ip;

import com.qianfeng.etl.util.ip.etl.mr.tohbase.LogToHbaseRunner;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeUtil {
    private static final Logger logger = Logger.getLogger(LogToHbaseRunner.class);
    private static String DEFAULT_DATE_FORMAT="yyyy-MM-dd";

    public static boolean isValidateDate(String date){
        Matcher matcher=null;
        boolean res=false;
        String regexp="^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}";
        if(date!=null){
            Pattern pattern=Pattern.compile(regexp);
            matcher=pattern.matcher(date);
        }
        if(matcher!=null){
            res=matcher.matches();
        }
        return res;
    }
    public static String getYesterdayDate(){
        return getYesterdayDate(DEFAULT_DATE_FORMAT);
    }
    public static String getYesterdayDate(String dateformat){
        SimpleDateFormat sdf =new SimpleDateFormat(dateformat);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,-1);
        return sdf.format(calendar.getTime());
    }

    public static void main(String[] args){
        System.out.println(getYesterdayDate("yyyy/MM/dd"));
    }

    public static long parserString2Long(String date){
        return parserString2Long(date,DEFAULT_DATE_FORMAT);

    }

    private static long parserString2Long(String date, String pattern) {
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        Date dt=null;
        try {
            dt=sdf.parse(date);
        } catch (ParseException e) {
            logger.warn("解析字符串的date为时间戳异常");
        }
        return dt==null?0:dt.getTime();
    }

    public static String parserLong2String(long time){
        return parserLong2String(time,DEFAULT_DATE_FORMAT);

    }
    public static String parserLong2String(long time,String format){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return new SimpleDateFormat(format).format(calendar.getTime());
    }
}
