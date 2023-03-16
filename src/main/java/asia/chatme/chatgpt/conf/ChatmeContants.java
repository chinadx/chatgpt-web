package asia.chatme.chatgpt.conf;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 一些常量定义
 */
public class ChatmeContants {
    public static final String ALL_SESSION_ID = "-1";
    public static final String ALL_SESSION_ID_CAN_SEE = "0";

    /** cookie失效时间 */
    public static final Integer COOKIE_MAX_AGE = 3600 * 24 * 365 * 10;

    public static final String JSESSIONID = "JSESSIONID";

    /** cookie定义,用来标识会话id */
    public static final String CHAT_SESSION_ID = "CHAT_SESSION_ID";

    /** 办公网ip段 */
    public static final Set<String> OFFICE_IPS = Sets.newHashSet();
    static {
        OFFICE_IPS.add("124.65.127.186/30");
        OFFICE_IPS.add("106.38.62.10/30");
    }
}
