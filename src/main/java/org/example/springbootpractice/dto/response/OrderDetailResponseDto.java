package org.example.springbootpractice.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.springbootpractice.entity.OrderDetail;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderDetailResponseDto {
    private Long orderId;
    private String orderMenuName;
    private int menuTotalPrice;
    private int quantity;
    private List<MenuOptionResponseDto> menuOption;

//    public  OrderDetailResponseDto(OrderDetail orderDetail) {
//        this.orderId = orderDetail.getOrder().getId();
//        this.menuOption = orderDetail.getMenuOption().stream()
//                .map(MenuOptionResponseDto::new)
//                .collect(Collectors.toList());
//    }

//    public OrderDetailResponseDto(Object[] result) {
//        this.orderMenuName = (String) result[1];
//        this.menuTotalPrice = ((Number) result[2]).intValue(); // BigDecimal 호환성 처리
//        this.quantity = ((Number) result[3]).intValue();
//    }
    public OrderDetailResponseDto(Long orderId, String orderMenuName, int menuTotalPrice, int quantity, List<MenuOptionResponseDto> menuOption) {
        this.orderId = orderId;
        this.orderMenuName = orderMenuName;
        this.menuTotalPrice = menuTotalPrice;
        this.quantity = quantity;
        this.menuOption = menuOption;
    }
}
