package org.example.springbootpractice.service.implement;


import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ResponseMessage;
import org.example.springbootpractice.dto.response.*;
import org.example.springbootpractice.repository.OrderDetailRepository;
import org.example.springbootpractice.service.OrderService;
import org.example.springbootpractice.entity.Order;
import org.example.springbootpractice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public ResponseDto<List<OrderResponseDto>> getAllOrders(String orderState) {
        List<OrderResponseDto> data = null;

        try {
            List<Order> orderList = orderRepository.findByOrderState(orderState);

            data = orderList.stream()
                    .map(OrderResponseDto::new)
                    .collect(Collectors.toList());

        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // api/v1/orders/{orderId}
    @Override
    public ResponseDto<OrderResponseDto> getOrder(Long orderId) {
        OrderResponseDto data = null;

        try {
            Optional<Order> optionalOrder = orderRepository.findById(orderId);

            if(optionalOrder.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_ORDER);
            }

            Order order = optionalOrder.get();
            data = new OrderResponseDto(order);

        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<OrderResponseDto> updateOrderState(UpdateOrderStateDto dto) {
        Long orderId = dto.getOrderId();
        String orderState = dto.getOrderState();
        OrderResponseDto data = null;

        try {
            Optional<Order> optionalOrder = orderRepository.findById(orderId);

            if (optionalOrder.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_ORDER);
            }

            Order order = optionalOrder.get();
            order.setOrderState(orderState);

            orderRepository.save(order);
            data = new OrderResponseDto(order);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

//    @Override
//    public ResponseDto<Object> get() {
//        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, orderRepository.findOrderDetailsWithTotalPriceAndOrderMenuName(1l));
//    }
}

