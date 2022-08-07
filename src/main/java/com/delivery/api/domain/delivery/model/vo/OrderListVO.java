package com.delivery.api.domain.delivery.model.vo;

import lombok.Getter;

import java.util.Date;

@Getter
public class OrderListVO {
    private int orderId;
    private String uid;
    private Integer shopId;
    private String address;
    private boolean isModifiable;
    private Date orderDate;
}
