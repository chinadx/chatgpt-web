package asia.chatme.chatgpt.conf;

import asia.chatme.chatgpt.exception.ChatMeErrorEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 服务返回信息定义
 * @author dx
 */
@Data
public class ServiceResult<T> implements Serializable {
    private static final long serialVersionUID = 4176748894003508527L;

    private boolean success = false;
    private String errorCode;
    private String errorMsg;
    private String prompt;

    private T data;

    /**
     * 成功定义
     * @param data
     * @return
     * @param <T>
     */
    public static <T> ServiceResult<T> success(T data) {
        ServiceResult<T> serviceResult = new ServiceResult<>();
        serviceResult.setSuccess(Boolean.TRUE);
        serviceResult.setErrorCode(ChatMeErrorEnum.SUCCESS.getErrorCode());
        serviceResult.setErrorMsg(ChatMeErrorEnum.SUCCESS.getErrorMsg());
        serviceResult.setPrompt(ChatMeErrorEnum.SUCCESS.getPrompt());
        serviceResult.setData(data);
        return serviceResult;
    }

    /**
     * 失败定义
     * @param errorEnum
     * @return
     * @param <T>
     */
    public static <T> ServiceResult<T> fail(ChatMeErrorEnum errorEnum) {
        ServiceResult<T> serviceResult = new ServiceResult<>();
        serviceResult.setSuccess(Boolean.FALSE);
        serviceResult.setErrorCode(errorEnum.getErrorCode());
        serviceResult.setErrorMsg(errorEnum.getErrorMsg());
        serviceResult.setPrompt(errorEnum.getPrompt());
        serviceResult.setData(null);
        return serviceResult;
    }

}
