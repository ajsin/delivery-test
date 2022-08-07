package com.delivery.api.application.service;

import com.delivery.api.application.dto.DeliveryAddressReqDTO;
import com.delivery.api.application.dto.DeliveryOrderResDTO;
import org.springframework.stereotype.Service;

@Service
public interface DeliveryService {

    public DeliveryOrderResDTO getOrderList(String startDT, String endDT, String uid) throws Exception;
    public void modifyOrderAddress(DeliveryAddressReqDTO deliveryAddressReqDTO, int orderId, String uid) throws Exception;
}
