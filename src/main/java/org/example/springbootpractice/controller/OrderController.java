package org.example.springbootpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ApiMappingPattern;
import org.example.springbootpractice.dto.response.OrderResponseDto;
import org.example.springbootpractice.dto.response.ResponseDto;
import org.example.springbootpractice.dto.response.UpdateOrderStateDto;
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

    private static final String GET_ORDER_LIST = "/list";
    private static final String UPDATE_ORDER_STATE = "/update/state";

    @GetMapping(GET_ORDER_LIST)
    public ResponseEntity<ResponseDto<List<OrderResponseDto>>> getOrderList(@RequestParam String orderState) {
        ResponseDto<List<OrderResponseDto>> response = orderService.getAllOrders(orderState);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseDto<OrderResponseDto>> getOrder(@PathVariable Long orderId) {
        ResponseDto<OrderResponseDto> response = orderService.getOrder(orderId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(UPDATE_ORDER_STATE)
    public ResponseEntity<ResponseDto<OrderResponseDto>> updateOrderState(@RequestBody UpdateOrderStateDto dto) {
        ResponseDto<OrderResponseDto> response = orderService.updateOrderState(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(null);
    }

//    @GetMapping("/get")
//    public ResponseEntity<ResponseDto<Object>> get() {
//        ResponseDto<Object> response = orderService.get();
//        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
//        return ResponseEntity.status(status).body(response);
//    }
}

