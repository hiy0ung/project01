package org.example.springbootpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ApiMappingPattern;
import org.example.springbootpractice.dto.response.OrderResponseDto;
import org.example.springbootpractice.dto.response.ResponseDto;
import org.example.springbootpractice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.ORDER)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private static final String GET_ORDER_LIST = "/";
    private static final String UPDATE_ORDER_STATE = "/update/state/{id}";

    @GetMapping(GET_ORDER_LIST)
    public ResponseEntity<ResponseDto<List<OrderResponseDto>>> getAllOrders() {
        ResponseDto<List<OrderResponseDto>> response = orderService.getAllOrders();
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(UPDATE_ORDER_STATE)
    public ResponseEntity<ResponseDto<OrderResponseDto>> updateOrderState(
            @PathVariable Long id,
            @RequestParam String updateOrderState) {
        ResponseDto<OrderResponseDto> response = orderService.updateOrderState(id, updateOrderState);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}

