package asia.chatme.chatgpt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GptMessage implements Serializable {
    private static final long serialVersionUID = -7272160911364834338L;

    private String role;
    private String content;

    public static GptMessage of(String role, String content) {
        return new GptMessage(role, content);
    }
}
