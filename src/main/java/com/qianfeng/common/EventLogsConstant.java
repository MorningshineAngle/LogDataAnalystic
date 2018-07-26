package com.qianfeng.common;

/**
 * @Auther: lyd
 * @Date: 2018/7/26 09:25
 * @Description:定义采集的数据中的相对应的key
 */
public class EventLogsConstant {

    /**
     * 定义事件枚举
     */
    public static enum EventEnum{
        LAUNCH(1,"lanuch event","e_l"),
        PAGEVIEW(2,"page view event","e_pv"),
        CHARGEREQUEST(3,"charge request event","e_crt"),
        CHARGESUCCESS(4,"charge success","e_cs"),
        CHARGEREFUND(5,"charge refund","e_cr"),
        EVENT(6,"event","e_e")
        ;

        public  final int id;
        public final  String name;
        public final String alias; //别名

        EventEnum(int id, String name, String alias) {
            this.id = id;
            this.name = name;
            this.alias = alias;
        }

        /**
         * 根据别名获取事件枚举
         * @param alias
         * @return
         */
        public static EventEnum valueOfAlias(String alias){
            //for循环
            for (EventEnum event:values()){
                if(alias.equals(event.alias)){
                    return event;
                }
            }
            return null;
        }
    }


    /**
     * 和日志相关
     */
    public static final String HBASE_TABLE_NAME = "logs";

    public static final String HBASE_COLUMN_FAMILY = "info";

    public static final String EVENT_COLUMN_NAME_VERSION = "ver";

    public static final String EVENT_COLUMN_NAME_SERVER_TIME = "s_time";

    public static final String EVENT_COLUMN_NAME_EVENT_NAME = "en";

    public static final String EVENT_COLUMN_NAME_UUID = "u_ud";

    public static final String EVENT_COLUMN_NAME_MEMBER_ID = "u_mid";

    public static final String EVENT_COLUMN_NAME_SESSION_ID = "u_sd";

    public static final String EVENT_COLUMN_NAME_CLIENT_TIME = "c_time";

    public static final String EVENT_COLUMN_NAME_LANGUAGE = "l";

    public static final String EVENT_COLUMN_NAME_USERAGENT = "b_iev";

    public static final String EVENT_COLUMN_NAME_RESOLUTION = "b_rst";

    public static final String EVENT_COLUMN_NAME_CURRENT_URL = "p_url";

    public static final String EVENT_COLUMN_NAME_PREFFER_URL = "p_ref";

    public static final String EVENT_COLUMN_NAME_TITLE = "tt";

    public static final String EVENT_COLUMN_NAME_PLATFORM = "pl";

    public static final String EVENT_COLUMN_NAME_IP = "ip";

    public static final String COLUMN_SEPARTOR = "\\^A";


    /**
     * 和订单相关
     */
    public static final String EVENT_COLUMN_NAME_ORDER_ID = "oid";

    public static final String EVENT_COLUMN_NAME_ORDER_NAME = "on";

    public static final String EVENT_COLUMN_NAME_CURRENCY_AMOUTN = "cua";

    public static final String EVENT_COLUMN_NAME_CURRENCY_TYPE = "cut";

    public static final String EVENT_COLUMN_NAME_PAYMENT_TYPE = "pt";


    /**
     * 事件相关
     */
    public static final String EVENT_COLUMN_NAME_EVENT_CATEGORY = "ca";

    public static final String EVENT_COLUMN_NAME_EVENT_ACTION = "ac";

    public static final String EVENT_COLUMN_NAME_EVENT_KV = "kv_";

    public static final String EVENT_COLUMN_NAME_EVENT_DURATION = "du";

    /**
     * 浏览器相关
     */

    public static final String EVENT_COLUMN_NAME_BROWSER_NAME = "browserName";

    public static final String EVENT_COLUMN_NAME_BROWSER_VERSION = "browserVersion";

    public static final String EVENT_COLUMN_NAME_OS_NAME = "osName";

    public static final String EVENT_COLUMN_NAME_OS_VERSION = "osVersion";

    /**
     * 地域相关
     */

    public static final String EVENT_COLUMN_NAME_COUNTRY = "country";

    public static final String EVENT_COLUMN_NAME_PROVINCE = "province";

    public static final String EVENT_COLUMN_NAME_CITY = "city";

}
