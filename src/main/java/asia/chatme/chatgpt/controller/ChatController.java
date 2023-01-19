package asia.chatme.chatgpt.controller;

import com.alibaba.fastjson.JSON;
import asia.chatme.chatgpt.dto.Dialog;
import asia.chatme.chatgpt.service.ChatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

@Controller
public class ChatController {
    @Resource
    private ChatService chatService;

    @GetMapping("/chat")
    public String chatView(Model model) {
        model.addAttribute("dialog", new Dialog());
        return "chat";
    }

    @PostMapping("/chat")
    public RedirectView chat(@ModelAttribute("dialog") Dialog dialog, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/chat", true);
        Dialog dialogResult = chatService.chat(dialog);
        System.out.println("dialog=" + JSON.toJSONString(dialogResult));
        redirectAttributes.addFlashAttribute("result", dialogResult);
        return redirectView;
    }
}
