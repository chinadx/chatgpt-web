package asia.chatme.chatgpt.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
public class Chat2Controller {
    private Logger logger = LoggerFactory.getLogger(ChatController.class);
    @Resource
    private ChatService chatService;

    @Resource
    private SessionService sessionService;

    @GetMapping("/chat2")
    public String chatView(Model model) {
        model.addAttribute("dialog", new DialogDTO());
        return "chat2";
    }

    @PostMapping("/chat2")
    public RedirectView chat(@RequestHeader Map<String, String> headers,
                             @ModelAttribute("dialog") DialogDTO dialog,
                             RedirectAttributes redirectAttributes,
                             HttpServletRequest request) {
        final RedirectView redirectView = new RedirectView("/chat2", true);
        logger.info("headers={}", JSON.toJSONString(headers));
        logger.info("ip={}, hostname={}", HttpReqRespUtils.getIPFromRequest(request), HttpReqRespUtils.getHostName(request));
        //get session
        UserSession userSession = new UserSession();
        userSession.setIp(HttpReqRespUtils.getIPFromRequest(request));
        userSession.setUserAgent(HttpReqRespUtils.getHostName(request));
        userSession.setCreateTime(new Date());
        String sessionId = sessionService.getSessionId(userSession);
        dialog.setSessionId(sessionId);
        DialogDTO dialogResult = chatService.chat(dialog);
//        logger.info("dialog=" + JSON.toJSONString(dialogResult));
        redirectAttributes.addFlashAttribute("result", dialogResult);
        return redirectView;
    }
}
