package com.delivery.api.domain.delivery.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class OrderAddressParamVO {
    private String uid;
    private int orderId;
    private String address;
}
