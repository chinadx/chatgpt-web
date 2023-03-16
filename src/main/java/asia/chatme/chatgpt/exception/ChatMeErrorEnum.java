package asia.chatme.chatgpt.exception;

/**
 * 错误信息枚举定义
 * @author dx
 */
public enum ChatMeErrorEnum {
    /** 错误码枚举 */
    SUCCESS("SUCCESS", "成功", ""),
    SYSTEM_ERROR("SYSTEM_ERROR", "系统错误", "请稍后重试"),
    SYSTEM_NETWORK_FAIL("SYSTEM_NETWORK_FAIL", "网络错误", "请稍后重试"),

    BIZ_ERROR_LIMIT_EXCEED("BIZ_ERROR_LIMIT_EXCEED", "使用次数受限", "外网使用受限，请1小时后重试，或使用办公网继续访问"),

    ;

    private String errorCode;
    private String errorMsg;

    private String prompt;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getPrompt() {
        return prompt;
    }

    ChatMeErrorEnum(String errorCode, String errorMsg, String prompt) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.prompt = prompt;
    }
}
