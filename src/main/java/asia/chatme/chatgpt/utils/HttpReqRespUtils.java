package asia.chatme.chatgpt.utils;

import asia.chatme.chatgpt.conf.ChatmeContants;
import cn.hutool.core.lang.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class HttpReqRespUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpReqRespUtils.class);
    private static final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    public static String getIPFromRequest(HttpServletRequest request) {
        String ip = null;
        if (request == null) {
            if (RequestContextHolder.getRequestAttributes() == null) {
                return null;
            }
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }

        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(ip)) {
            return ip;
        }

        for (String header : IP_HEADER_CANDIDATES) {
            String ipList = request.getHeader(header);
            if (ipList != null && ipList.length() != 0 && !"unknown".equalsIgnoreCase(ipList)) {
                return ipList.split(",")[0];
            }
        }
        return request.getRemoteAddr();
    }

    public static String getHostName(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractCHATSESSIONID(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(ChatmeContants.CHAT_SESSION_ID)) {
                    logger.info("------CHAT_SESSION---{}", cookie.getValue());
                    return cookie.getValue();
                }
            }
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(ChatmeContants.JSESSIONID)) {
                    logger.info("------JSESSIONID---{}", cookie.getValue());
                    return cookie.getValue();
                }
            }
        }
        return Md5Util.md5(UUID.fastUUID().toString());
    }

    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

}