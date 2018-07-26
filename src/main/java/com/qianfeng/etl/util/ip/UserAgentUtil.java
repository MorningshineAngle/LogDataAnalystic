package com.qianfeng.etl.util.ip;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * 解析浏览器代理对象
 * Created by lyd on 2018/3/28.
 */
public class UserAgentUtil {
    private static final Logger logger = Logger.getLogger(UserAgentUtil.class);
    private static UASparser uas = null;

    //初始化uas
    static {
       try {
           uas = new UASparser(OnlineUpdater.getVendoredInputStream());
       } catch (Exception e) {
           logger.warn("初始化uasparser失败",e);
       }
    }

    /**
     * 解析
     * @param userAgent ："Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36"
     * @return
     */
    public static UserAgentInfo parserUserAgent(String userAgent){
        UserAgentInfo us = new UserAgentInfo();

        if(StringUtils.isEmpty(userAgent)){
            return null;
        }

        //使用uasparser工具来解析useragent
        try {
            cz.mallat.uasparser.UserAgentInfo info = uas.parse(userAgent);
            if(info!=null){
                //从解析后的info获取对应的属性的值,并封装到对象中返回
                us.setBrowserName(info.getUaFamily());
                us.setBrowserVersion(info.getBrowserVersionInfo());
                us.setOsName(info.getOsFamily());
                us.setOsVersion(info.getOsName());
            }

        } catch (IOException e) {
            logger.error("usa解析useragent异常.",e);
        }
        return us;
    }


    /**
     * 封装浏览器信息的对象
     */
    public static class UserAgentInfo{
        private String browserName;
        private String browserVersion;
        private String osName;
        private String osVersion;

        public UserAgentInfo(){
        }

        public UserAgentInfo(String browserName, String browserVersion, String osName, String osVersion) {
            this.browserName = browserName;
            this.browserVersion = browserVersion;
            this.osName = osName;
            this.osVersion = osVersion;
        }
        public String getBrowserName() {
            return browserName;
        }

        public void setBrowserName(String browserName) {
            this.browserName = browserName;
        }

        public String getBrowserVersion() {
            return browserVersion;
        }

        public void setBrowserVersion(String browserVersion) {
            this.browserVersion = browserVersion;
        }

        public String getOsName() {
            return osName;
        }

        public void setOsName(String osName) {
            this.osName = osName;
        }

        public String getOsVersion() {
            return osVersion;
        }

        public void setOsVersion(String osVersion) {
            this.osVersion = osVersion;
        }
        @Override
        public String toString() {
            return "UserAgentInfo{" +
                    "browserName='" + browserName + '\'' +
                    ", browserVersion='" + browserVersion + '\'' +
                    ", osName='" + osName + '\'' +
                    ", osVersion='" + osVersion + '\'' +
                    '}';
        }
    }
}