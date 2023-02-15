package asia.chatme.chatgpt.controller;

import asia.chatme.chatgpt.conf.ChatmeContants;
import asia.chatme.chatgpt.dto.DialogDTO;
import asia.chatme.chatgpt.model.UserSession;
import asia.chatme.chatgpt.service.ChatService;
import asia.chatme.chatgpt.service.SessionService;
import asia.chatme.chatgpt.utils.HttpReqRespUtils;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class ChatController {
    private Logger logger = LoggerFactory.getLogger(Chat2Controller.class);
    @Resource
    private ChatService chatService;

    @Resource
    private SessionService sessionService;

    /**
     * chat主入口，默认展示当前用户session下的问题，并支持提问
     */
    @GetMapping("/chat")
    public String chatView(Model model,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        String sessionId = packSessionId(request, response);

        logger.info("sessionId={}", sessionId);

        List<DialogDTO> dialogs = chatService.listDialog(sessionId);
        model.addAttribute("dialogs", dialogs);
        return "chat";
    }

    /**
     * 提交问题
     */
    @PostMapping("/saveData")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ask = request.getParameter("ask");
        // 在这里处理请求，将 data 存入后台数据列表中
        String sessionId = packSessionId(request, response);
        DialogDTO dialog = new DialogDTO();
        dialog.setAsk(ask);
        dialog.setSessionId(sessionId);
        DialogDTO dialogResult = chatService.chat(dialog);
    }

    /**
     * 查看所有问题
     * todo 分页处理
     */
    @GetMapping("/monitor")
    public String monitor(Model model,
                           HttpServletRequest request) {
        List<DialogDTO> dialogs = chatService.listDialog(ChatmeContants.ALL_SESSION_ID);
        model.addAttribute("dialogs", dialogs);
        return "monitor";
    }

    private String packSessionId(HttpServletRequest request, HttpServletResponse response) {
        String ip = request.getRemoteAddr();
        String ua = HttpReqRespUtils.getUserAgent(request);
        String sessionId = HttpReqRespUtils.extractJSESSIONID(request);
        //get session
        UserSession userSession = new UserSession();
        userSession.setIp(ip);
        userSession.setUserAgent(ua);
        userSession.setSessionId(sessionId);
        userSession.setCreateTime(new Date());
        logger.info("userSession={}, remoteAddr={}, remoteHost={}, userAgent={}",
                JSON.toJSONString(userSession), request.getRemoteAddr(), request.getRemoteHost(), request.getHeader("User-Agent"));
        sessionService.getSessionId(userSession);

        /** 手动设置cookie失效时间 */
        Cookie cookie = new Cookie("JSESSIONID", sessionId);
        cookie.setPath(request.getContextPath()+"/");
        cookie.setMaxAge(ChatmeContants.COOKIE_MAX_AGE);
        response.addCookie(cookie);
        return sessionId;
    }
}
