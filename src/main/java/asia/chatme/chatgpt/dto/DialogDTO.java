package asia.chatme.chatgpt.dto;

import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.util.Date;

@Data
public class DialogDTO {
    private Integer id;
    private String sessionId;
    private String ask;
    private String answer;
    private String user;
    private Date createTime;
    private String createTimeStr;
    private Integer tokens;

    public String getCreateTimeStr() {
        return createTime == null ? null
                : DateUtil.formatDateTime(createTime);
    }

    public static DialogDTO of(String ask, String answer) {
        DialogDTO dialog = new DialogDTO();
        dialog.setAsk(ask);
        dialog.setAnswer(answer);
        dialog.setCreateTime(new Date());
        return dialog;
    }

    public static DialogDTO of(String ask, String answer, Integer tokens) {
        DialogDTO dialog = DialogDTO.of(ask, answer);
        dialog.setTokens(tokens);
        return dialog;
    }
}
