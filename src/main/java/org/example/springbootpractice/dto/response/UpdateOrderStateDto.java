package org.example.springbootpractice.dto.response;

import lombok.Getter;

@Getter
public class UpdateOrderStateDto {
    private Long orderId;
    private String orderState;
}
