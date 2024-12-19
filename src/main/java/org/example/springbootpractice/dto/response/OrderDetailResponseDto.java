package org.example.springbootpractice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponseDto {
    private Long orderId;
    private Long orderDetailId;
    private String deliveryAddress;
    private LocalDateTime orderDate;
    private String menuName;
    private Integer menuPrice;
    private Integer quantity;
    private String menuOptionName;
    private String menuOptionDetailName;
    private Integer additionalFee;
}

