package asia.chatme.chatgpt.service;

import asia.chatme.chatgpt.dto.DialogDTO;

import java.util.List;

public interface ChatService {

    DialogDTO chat(DialogDTO input);

    List<DialogDTO> listDialog(String sessionId);
}
