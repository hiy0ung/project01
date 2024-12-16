package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.response.OrderResponseDto;
import org.example.springbootpractice.dto.response.ResponseDto;
import org.example.springbootpractice.dto.response.UpdateOrderStateDto;

import java.util.List;

public interface OrderService {
    ResponseDto<List<OrderResponseDto>> getAllOrders(String orderState);

    ResponseDto<OrderResponseDto> getOrder(Long orderId);

    ResponseDto<OrderResponseDto> updateOrderState(UpdateOrderStateDto updateOrderState);

//    ResponseDto<Object> get();
}