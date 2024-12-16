package org.example.springbootpractice.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class totalPriceResponseDto {
    private int totalPrice;
    private String orderMenuName;
    private Long menuId;

    public totalPriceResponseDto( int totalPrice, String orderMenuName, Long menuId) {
        this.orderMenuName = orderMenuName;
        this.totalPrice = totalPrice;
        this.menuId = menuId;
    }
}
