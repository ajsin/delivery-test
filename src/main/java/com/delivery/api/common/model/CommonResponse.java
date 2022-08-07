package com.delivery.api.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@Schema(description = "공통 reponse 객체")
public class CommonResponse<T>{

    @Schema(description = "return 코드, 성공일때 0, 실패일때 다른 코드로 내려감", defaultValue = "0")
    private int code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "클라이언트에서 필요한 응답 데이터")
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "클라이언트에 보낼 메세지")
    private String msg;

}
