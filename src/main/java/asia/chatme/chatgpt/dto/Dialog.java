package asia.chatme.chatgpt.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Dialog {
    private String ask;
    private String answer;
    private String user;
    private Date time;
    private Integer tokens;

    public static Dialog of(String ask, String answer) {
        Dialog dialog = new Dialog();
        dialog.setAsk(ask);
        dialog.setAnswer(answer);
        dialog.setTime(new Date());
        return dialog;
    }

    public static Dialog of(String ask, String answer, Integer tokens) {
        Dialog dialog = Dialog.of(ask, answer);
        dialog.setTokens(tokens);
        return dialog;
    }
}
