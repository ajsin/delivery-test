package com.delivery.api.application.dto;

import com.delivery.api.domain.delivery.model.vo.OrderListVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Schema(description = "배달 조회 response 객체")
public class DeliveryOrderResDTO {

    @Schema(description = "배달 리스트")
    private List<OrderListVO> orderList;
}
