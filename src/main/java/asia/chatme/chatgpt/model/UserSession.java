package asia.chatme.chatgpt.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserSession implements Serializable {
    private static final long serialVersionUID = 5382821569960042042L;
    private Integer id;
    private String sessionId;
    private String userAgent;
    private String ip;
    private String summary;
    private Integer rounds;
    private Integer tokens;
    private Date createTime;

}
