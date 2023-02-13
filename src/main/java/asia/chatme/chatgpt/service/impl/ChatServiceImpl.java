package asia.chatme.chatgpt.service.impl;

import asia.chatme.chatgpt.conf.ChatGptConf;
import asia.chatme.chatgpt.conf.ChatmeContants;
import asia.chatme.chatgpt.dto.DialogDTO;
import asia.chatme.chatgpt.dto.GptModel;
import asia.chatme.chatgpt.mapper.DialogMapper;
import asia.chatme.chatgpt.model.Dialog;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import asia.chatme.chatgpt.service.ChatService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {
    private Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

    @Resource
    private ChatGptConf chatGptConf;

    @Resource
    private DialogMapper dialogMapper;

    @Override
    public DialogDTO chat(DialogDTO input) {
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("Content-Type","application/json;charset=UTF-8");
        logger.info("ask = {}", input.getAsk());

        JSONObject json = new JSONObject();
        //选择模型
        json.put("model",chatGptConf.getModel());
        //添加我们需要输入的内容
        json.put("prompt",input.getAsk());
        json.put("temperature",chatGptConf.getTemperature());
        json.put("max_tokens",chatGptConf.getMaxTokens());
        json.put("top_p",chatGptConf.getTopP());
        json.put("frequency_penalty",chatGptConf.getFrequencyPenalty());
        json.put("presence_penalty",chatGptConf.getPresencePenalty());

        HttpResponse response = HttpRequest.post("https://api.openai.com/v1/completions")
                .headerMap(headers, false)
                .bearerAuth(chatGptConf.getKey())
                .body(String.valueOf(json))
                .timeout(5 * 60 * 1000)
                .execute();

        logger.info(response.body());
        String body = response.body();

//        String body = "{\n" +
//                "\t\"id\": \"cmpl-6iaDKHVTkL17zFIDClVWcoNhGZgCm\",\n" +
//                "\t\"object\": \"text_completion\",\n" +
//                "\t\"created\": 1676083658,\n" +
//                "\t\"model\": \"text-davinci-003\",\n" +
//                "\t\"choices\": [\n" +
//                "\t\t{\n" +
//                "\t\t\t\"text\": \"\\n\\nHi there!\",\n" +
//                "\t\t\t\"index\": 0,\n" +
//                "\t\t\t\"logprobs\": null,\n" +
//                "\t\t\t\"finish_reason\": \"stop\"\n" +
//                "\t\t}\n" +
//                "\t],\n" +
//                "\t\"usage\": {\n" +
//                "\t\t\"prompt_tokens\": 2,\n" +
//                "\t\t\"completion_tokens\": 5,\n" +
//                "\t\t\"total_tokens\": 7\n" +
//                "\t}\n" +
//                "}\n";
        GptModel model = JSONObject.parseObject(body, GptModel.class);
        logger.info(JSON.toJSONString(model));
        String text = model.getChoices().get(0).getText().replace("\n","<br/>");
        DialogDTO dialogDTO = DialogDTO.of(input.getAsk(), text, model.getUsage().getTotalTokens());

        Dialog dialog = new Dialog();
        BeanUtils.copyProperties(dialogDTO, dialog);
        dialog.setCreateTime(new Date());
        dialog.setSessionId(input.getSessionId());
        Integer ins = dialogMapper.insert(dialog);
        logger.info("----ins={}", dialog.getId());
        return dialogDTO;
    }

    @Override
    public List<DialogDTO> listDialog(String sessionId) {
        List<Dialog> dialogs = null;
        if (ChatmeContants.ALL_SESSION_ID.equals(sessionId)) {
            dialogs = dialogMapper.selectAll();
        } else {
            dialogs = dialogMapper.selectBySessionId(sessionId);
        }
        logger.info("dialogs={}", JSON.toJSONString(dialogs));
        List<DialogDTO> dialogList = new LinkedList<>();
        for (Dialog dialog : dialogs) {
            DialogDTO dto = new DialogDTO();
            BeanUtils.copyProperties(dialog, dto);
            dialogList.add(dto);
        }
        logger.info("dialogs={}", JSON.toJSONString(dialogList));
        return dialogList;
    }
}
