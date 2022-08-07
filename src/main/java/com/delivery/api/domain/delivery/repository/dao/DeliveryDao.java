package com.delivery.api.domain.delivery.repository.dao;

import com.delivery.api.domain.delivery.model.vo.OrderAddressParamVO;
import com.delivery.api.domain.delivery.model.vo.OrderListParamVO;
import com.delivery.api.domain.delivery.model.vo.OrderListVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DeliveryDao {
    public List<OrderListVO> getOrderList(OrderListParamVO orderListParamVO);
    public int updateOrderAddress(OrderAddressParamVO orderAddressParamVO);
}
