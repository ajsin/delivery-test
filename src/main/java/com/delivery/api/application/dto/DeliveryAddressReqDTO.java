package com.delivery.api.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Schema(description = "주소 수정 request 객체")
public class DeliveryAddressReqDTO {

    @Schema(description = "수정할 주소")
    @NotBlank(message = "수정할 주소를 입력해주세요.")
    private String address;
}
