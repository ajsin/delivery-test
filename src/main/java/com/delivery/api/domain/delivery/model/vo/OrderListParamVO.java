package com.delivery.api.domain.delivery.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class OrderListParamVO {
    private String startDT;
    private String endDT;
    private String uid;
}
