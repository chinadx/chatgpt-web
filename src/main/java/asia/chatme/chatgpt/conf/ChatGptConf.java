package asia.chatme.chatgpt.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="chatgpt.api")
public class ChatGptConf {
    private String key;
    private String model;
    private Double temperature;
    private Integer maxTokens;
    private Integer topP;
    private Double frequencyPenalty;
    private Double presencePenalty;

}
