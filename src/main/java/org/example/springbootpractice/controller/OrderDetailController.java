package org.example.springbootpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.dto.response.OrderDetailResponseDto;
import org.example.springbootpractice.dto.response.ResponseDto;
import org.example.springbootpractice.service.OrderDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/test")
@RequiredArgsConstructor
public class OrderDetailController {
    
    private final OrderDetailService orderDetailService;

    @GetMapping("/")
    public ResponseEntity<ResponseDto<List<OrderDetailResponseDto>>> getOrderDetail(@RequestParam Long orderId) {
        ResponseDto<List<OrderDetailResponseDto>> response = orderDetailService.getOrderDetail(orderId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
