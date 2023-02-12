package asia.chatme.chatgpt.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author l
 */
@Data
public class Dialog implements Serializable {
    private static final long serialVersionUID = 3690282725011976351L;

    private Integer id;
    private String sessionId;
    private String ask;
    private String answer;
    private Integer tokens;
    private Date createTime;

}
