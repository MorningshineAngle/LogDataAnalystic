package com.qianfeng.etl.util.ip;

import java.util.List;

public class IpTest {
    public static void main(String[] agrs){
        System.out.println(IPSeeker.getInstance().getCountry("90.26.184.114"));
        System.out.println(IPSeeker.getInstance().getCountry("192.168.216.111"));

       System.out.println(new IpParserUtil().parserIp("123.150.187.130"));

        List<String> ips =  IPSeeker.getInstance().getAllIp();
        for (String ip:ips){
            System.out.println("ip="+ip+"   "+new IpParserUtil().parserIp(ip));

        }

//        List<String> ips =  IPSeeker.getInstance().getAllIp();
//        for (String ip:ips){
//            try {
//                System.out.println("ip="+ip+"   "+new IpParserUtil().parserIp1("http://ip.taobao.com/service/getIpInfo.php?ip="+ip,"UTF-8"));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
    }

}
