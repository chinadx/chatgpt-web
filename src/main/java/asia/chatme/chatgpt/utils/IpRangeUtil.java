package asia.chatme.chatgpt.utils;

import asia.chatme.chatgpt.conf.ChatmeContants;
import cn.hutool.core.net.Ipv4Util;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IpRangeUtil {
    private static Logger logger = LoggerFactory.getLogger(IpRangeUtil.class);

    /**
     * 判断ip是否在ipMask掩码范围内
     * @param ip
     * @return
     */
    public static boolean isInRange(String ip) {
        for (String ipMask : ChatmeContants.OFFICE_IPS) {
            String[] maskArr = ipMask.split("/");
            String maskIp = maskArr[0];
            int maskLen = Integer.parseInt(maskArr[1]);

            long inIp = Ipv4Util.ipv4ToLong(ip);
            long beginIp = Ipv4Util.getBeginIpLong(maskIp, maskLen);
            String endIpStr = Ipv4Util.getEndIpStr(maskIp, maskLen);
            long endIp = Ipv4Util.ipv4ToLong(endIpStr);

            if ((inIp >= beginIp) && (inIp <= endIp)) {
                logger.info("-YES-client ip {} in range {}", ip, ipMask);
                return true;
            };
        }
        logger.info("-NO-client ip {} not in range {}", ip, JSON.toJSONString(ChatmeContants.OFFICE_IPS));
        return false;
    }

    public static void main(String[] args) {
        String ip1 = "124.65.127.185";
        String ip2 = "124.65.127.186";
        String ip3 = "124.65.127.187";
        String ip4 = "124.65.127.188";
        System.out.println(isInRange(ip1)); // true
        System.out.println(isInRange(ip2)); // true
        System.out.println(isInRange(ip3)); // true
        System.out.println(isInRange(ip4)); // false
    }
}
