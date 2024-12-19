package org.example.springbootpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ApiMappingPattern;
import org.example.springbootpractice.dto.response.OrderDetailResponseDto;
import org.example.springbootpractice.dto.response.ResponseDto;
import org.example.springbootpractice.service.OrderDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.ORDER)
@RequiredArgsConstructor
public class OrderDetailController {
    
    private final OrderDetailService orderDetailService;

    private static final String GET_ORDER_DETAIL_BY_ID = "/detail/{id}";

    @GetMapping(GET_ORDER_DETAIL_BY_ID)
    public ResponseEntity<ResponseDto<List<OrderDetailResponseDto>>> getOrderDetail(@PathVariable Long id) {
        ResponseDto<List<OrderDetailResponseDto>> response = orderDetailService.getOrderDetail(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
