package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.response.OrderResponseDto;
import org.example.springbootpractice.dto.response.ResponseDto;

import java.util.List;

public interface OrderService {
    ResponseDto<List<OrderResponseDto>> getAllOrders();

    ResponseDto<OrderResponseDto> updateOrderState(Long id, String updateOrderState);
}