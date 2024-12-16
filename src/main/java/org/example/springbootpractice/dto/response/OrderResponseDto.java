package org.example.springbootpractice.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springbootpractice.entity.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderResponseDto {
    private Long id; // 주문 번호로 사용
    private String deliveryAddress;
    private LocalDateTime orderDate;
    private int sumTotalPrice;
    private String orderState;
    private List<OrderDetailResponseDto> orderDetails;

    public OrderResponseDto(Order order) {
        this.id = order.getId();
        this.deliveryAddress = order.getDeliveryAddress();
        this.orderDate = order.getOrderDate();
        this.orderState = order.getOrderState();
//        this.orderDetails = order.getOrderDetail().stream()
//                .map(OrderDetailResponseDto::new)
//                .collect(Collectors.toList());
    }
}
