package com.ruoyi.common.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author:CJQ
 * Date:2023/10/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResp<T> {
    /**
     * 业务成功或失败
     */
    private Boolean success = true;
    /**
     * 返回信息
     */
    private String message="message";
    /**
     * 返回泛型数据，自定义类型
     */
    private T data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResp{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
