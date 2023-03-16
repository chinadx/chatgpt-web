package asia.chatme.chatgpt.service;

import asia.chatme.chatgpt.conf.ServiceResult;
import asia.chatme.chatgpt.dto.DialogDTO;

import java.util.List;

public interface Chat2Service {

    ServiceResult<DialogDTO> chat(DialogDTO input);

    ServiceResult<List<DialogDTO>> listDialog(String sessionId);

    ServiceResult<Boolean> deleteDialog(String sessionId, Integer id);
}
