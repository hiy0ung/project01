package org.example.springbootpractice.service.implement;


import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ResponseMessage;
import org.example.springbootpractice.dto.response.*;
import org.example.springbootpractice.entity.Order;
import org.example.springbootpractice.service.OrderService;
import org.example.springbootpractice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    // 전체 주문 조회 리스트
    @Override
    public ResponseDto<List<OrderResponseDto>> getAllOrders() {
        List<OrderResponseDto> data = null;

        try {
            data = orderRepository.findAllOrderWithTotalPrice().stream()
                    .map(dto -> new OrderResponseDto(
                            (Long) dto[0],
                            (Long) dto[1],
                            (String) dto[2],
                            ((Timestamp) dto[3]).toLocalDateTime(),
                            (String) dto[4],
                            (String) dto[5],
                            ((BigDecimal) dto[6]).intValue()
                    )).collect(Collectors.toList());
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 주문 상태 수정
    @Override
    public ResponseDto<OrderResponseDto> updateOrderState(Long id, String updateOrderState) {
        OrderResponseDto data = null;
        Long orderId = id;

        try {
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXIST_ORDER));

            order.setOrderState(updateOrderState);
            orderRepository.save(order);

            List<Object[]> orderList = orderRepository.findOrderWithTotalPriceById(orderId);

            if (orderList.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

            Object[] dto = orderList.get(0);
            data = new OrderResponseDto(
                    (Long) dto[0],
                    (Long) dto[1],
                    (String) dto[2],
                    ((Timestamp) dto[3]).toLocalDateTime(),
                    (String) dto[4],
                    (String) dto[5],
                    ((BigDecimal) dto[6]).intValue()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}

