package codesquad.todo.web.works.dto;

import lombok.Getter;

@Getter
public class BaseResponse {
    private Boolean valid;
    private String message;

    public BaseResponse(Boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    public static BaseResponse ok() {
        return new BaseResponse(true, "OK");
    }

    public static BaseResponse fail(String errorMessage) {
        return new BaseResponse(false, errorMessage);
    }
}
