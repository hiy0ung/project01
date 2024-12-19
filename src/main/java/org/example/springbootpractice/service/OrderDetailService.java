package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.response.OrderDetailResponseDto;
import org.example.springbootpractice.dto.response.ResponseDto;

import java.util.List;

public interface OrderDetailService {
    ResponseDto<List<OrderDetailResponseDto>> getOrderDetail(Long id);
}
