package com.delivery.api.application.service.impl;

import com.delivery.api.application.dto.DeliveryAddressReqDTO;
import com.delivery.api.application.dto.DeliveryOrderResDTO;
import com.delivery.api.application.service.DeliveryService;
import com.delivery.api.common.exception.DeliveryException;
import com.delivery.api.common.exception.UserException;
import com.delivery.api.domain.delivery.model.entity.Order;
import com.delivery.api.domain.delivery.model.vo.OrderAddressParamVO;
import com.delivery.api.domain.delivery.model.vo.OrderListParamVO;
import com.delivery.api.domain.delivery.model.vo.OrderListVO;
import com.delivery.api.domain.delivery.repository.dao.DeliveryDao;
import com.delivery.api.domain.delivery.repository.orm.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    DeliveryDao deliveryDao;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public DeliveryOrderResDTO getOrderList(String startDT, String endDT, String uid) throws Exception {

        OrderListParamVO orderListParamVO = new OrderListParamVO(startDT, endDT, uid);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        long startDate = 0;
        long endDate = 0;

        try {
            startDate = formatter.parse(startDT).getTime();
            endDate = formatter.parse(endDT).getTime();
        } catch (ParseException e) {
            throw new DeliveryException.WrongPeriodException("기간 형식이 올바르지 않습니다.");
        }

        if(startDate > endDate) {
            throw new DeliveryException.WrongPeriodException("기간 설정이 올바르지 않습니다. (시작 기간은 종료 기간보다 이전이어야 합니다.)");
        }

        long diff = Math.abs(endDate - startDate);
        long maxInterval = 1000*60*60*24*3;
        if(diff > maxInterval) {
            throw new DeliveryException.WrongPeriodException("기간 설정이 올바르지 않습니다. (최대 3일 이내)");
        }

        List<OrderListVO> orderLists = deliveryDao.getOrderList(orderListParamVO);

        return new DeliveryOrderResDTO(orderLists);

    }

    public void modifyOrderAddress(DeliveryAddressReqDTO deliveryAddressReqDTO, int orderId, String uid) throws Exception {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new UserException.LoginFailException("존재하지 않는 주문입니다."));
        if(!order.isModifiable()) {
            throw new DeliveryException.NotModifiableException("변경 불가능한 배달입니다.");
        }

        OrderAddressParamVO orderAddressParamVO = new OrderAddressParamVO(uid, orderId, deliveryAddressReqDTO.getAddress());

        deliveryDao.updateOrderAddress(orderAddressParamVO);

    }


}
