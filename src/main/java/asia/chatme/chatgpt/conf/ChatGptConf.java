package asia.chatme.chatgpt.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ChatGptConf {
    @Value("${chatgpt.api.key}")
    private String apiKey;

    @Value("${chatgpt.api.model}")
    private String model;
    @Value("${chatgpt.api.temperature}")
    private Double temperature;
    @Value("${chatgpt.api.max_tokens}")
    private Integer max_tokens;
    @Value("${chatgpt.api.top_p}")
    private Integer top_p;
    @Value("${chatgpt.api.frequency_penalty}")
    private Double frequency_penalty;
    @Value("${chatgpt.api.presence_penalty}")
    private Double presence_penalty;

}
