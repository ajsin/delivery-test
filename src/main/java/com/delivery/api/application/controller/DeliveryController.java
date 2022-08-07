package com.delivery.api.application.controller;

import com.delivery.api.application.dto.DeliveryAddressReqDTO;
import com.delivery.api.application.dto.DeliveryOrderResDTO;
import com.delivery.api.application.service.DeliveryService;
import com.delivery.api.common.model.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/delivery")
@Tag(name = "2. 배달 관련 API")
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @GetMapping("/orders")
    @Operation(summary = "배달 리스트 조회 API", description = "기간 내에 사용자가 주문한 배달 리스트 조회")
    public CommonResponse<DeliveryOrderResDTO> orders(
            @Parameter(name = "startDT", description = "기간 시작날짜", in = ParameterIn.QUERY) String startDT,
            @Parameter(name = "endDT", description = "기간 종료날짜", in = ParameterIn.QUERY) String endDT,
            @RequestAttribute(name = "uid") String uid
    ) throws Exception {

        DeliveryOrderResDTO deliveryOrderResDTO = deliveryService.getOrderList(startDT, endDT, uid);

        return CommonResponse.<DeliveryOrderResDTO>builder()
                .data(deliveryOrderResDTO)
                .build();
    }


    @PostMapping("/address/{orderId}")
    @Operation(summary = "배달 주문 수정 API", description = "도착지 주소 변경")
    public CommonResponse modifyOrderAddress(
            @Parameter(name = "orderId", description = "order ID", in = ParameterIn.PATH) @PathVariable("orderId") Integer orderId,
            @RequestBody @Valid DeliveryAddressReqDTO deliveryAddressReqDTO,
            @RequestAttribute(name = "uid") String uid
    ) throws Exception {

        deliveryService.modifyOrderAddress(deliveryAddressReqDTO, orderId, uid);

        return CommonResponse.builder()
               .build();
    }

}
