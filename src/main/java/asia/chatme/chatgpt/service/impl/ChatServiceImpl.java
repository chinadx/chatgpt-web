package asia.chatme.chatgpt.service.impl;

import asia.chatme.chatgpt.conf.ChatGptConf;
import asia.chatme.chatgpt.dto.Dialog;
import asia.chatme.chatgpt.dto.GptModel;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import asia.chatme.chatgpt.service.ChatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {
    @Resource
    private ChatGptConf chatGptConf;

    @Override
    public Dialog chat(Dialog input) {
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("Content-Type","application/json;charset=UTF-8");

        JSONObject json = new JSONObject();
        //选择模型
        json.put("model",chatGptConf.getModel());
        //添加我们需要输入的内容
        json.put("prompt",input.getAsk());
        json.put("temperature",chatGptConf.getTemperature());
        json.put("max_tokens",chatGptConf.getMax_tokens());
        json.put("top_p",chatGptConf.getTop_p());
        json.put("frequency_penalty",chatGptConf.getFrequency_penalty());
        json.put("presence_penalty",chatGptConf.getPresence_penalty());

        HttpResponse response = HttpRequest.post("https://api.openai.com/v1/completions")
                .headerMap(headers, false)
                .bearerAuth(chatGptConf.getApiKey())
                .body(String.valueOf(json))
                .timeout(5 * 60 * 1000)
                .execute();

        System.out.println(response.body());

        String body = response.body();
        GptModel model = JSONObject.parseObject(body, GptModel.class);
        System.out.println(JSON.toJSONString(model));
        String text = model.getChoices().get(0).getText().replace("\n","<br/>");
        return Dialog.of(input.getAsk(), text, model.getUsage().getTotalTokens());
    }
}
