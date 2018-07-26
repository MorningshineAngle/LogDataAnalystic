package com.qianfeng.etl.util.ip;

public class UserAgentTest {
    public static void main(String[] agrs){
        //console-->window.navigator.userAgent
        System.out.println(new UserAgentUtil().parserUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36"));
    }
}
