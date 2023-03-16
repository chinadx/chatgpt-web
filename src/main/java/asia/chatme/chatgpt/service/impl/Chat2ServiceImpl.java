package asia.chatme.chatgpt.service.impl;

import asia.chatme.chatgpt.conf.ServiceResult;
import asia.chatme.chatgpt.dto.DialogDTO;
import asia.chatme.chatgpt.service.Chat2Service;
import asia.chatme.chatgpt.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Chat2ServiceImpl implements Chat2Service {
    private Logger logger = LoggerFactory.getLogger(Chat2ServiceImpl.class);

    @Resource
    private ChatService chatService;

    @Override
    public ServiceResult<DialogDTO> chat(DialogDTO input) {
        return ServiceResult.success(chatService.chat(input));
    }

    @Override
    public ServiceResult<List<DialogDTO>> listDialog(String sessionId) {

        return ServiceResult.success(chatService.listDialog(sessionId));
    }

    @Override
    public ServiceResult<Boolean> deleteDialog(String sessionId, Integer id) {

        return ServiceResult.success(chatService.deleteDialog(sessionId, id));
    }
}
