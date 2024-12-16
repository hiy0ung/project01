package org.example.springbootpractice.service.implement;

import org.example.springbootpractice.common.constant.ResponseMessage;
import org.example.springbootpractice.dto.response.MenuOptionResponseDto;
import org.example.springbootpractice.dto.response.OrderDetailResponseDto;
import org.example.springbootpractice.dto.response.ResponseDto;
import org.example.springbootpractice.entity.MenuOption;
import org.example.springbootpractice.entity.MenuOptionDetail;
import org.example.springbootpractice.entity.OrderDetail;
import org.example.springbootpractice.repository.MenuOptionRepository;
import org.example.springbootpractice.repository.OrderDetailRepository;
import org.example.springbootpractice.service.OrderDetailService;
import org.example.springbootpractice.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final MenuOptionRepository menuOptionRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, MenuOptionRepository menuOptionRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.menuOptionRepository = menuOptionRepository;
    }

    @Override
    public ResponseDto<List<OrderDetailResponseDto>> getOrderDetail(Long orderId) {
        List<OrderDetailResponseDto> data = null;

        List<Object[]> orderDetailDatas = orderDetailRepository.findOrderDetailsWithOptions(orderId);

        data = orderDetailDatas.stream()
                .map(dto -> {
                    Long orderIdFromDto = (Long) dto[0];
                    String orderMenuName = (String) dto[1];
                    int menuTotalPrice = ((BigDecimal) dto[2]).intValue();
                    int quantity = (int) dto[3];


//                    List<MenuOption> menuOptionList = menuOptionRepository.findByOrderDetailId(orderIdFromDto);

//                    int additionalFeeTotal = menuOptionList.stream()
//                            .flatMap(menuOption -> menuOption.getMenuOptionDetails().stream())
//                            .mapToInt(MenuOptionDetail::getAdditionalFee)
//                            .sum();

                    // 메뉴 총 가격에 추가 금액 반영
//                    menuTotalPrice += additionalFeeTotal;


//                    // MenuOptionResponseDto 리스트 생성
//                    List<MenuOptionResponseDto> menuOptions = menuOptionList.stream()
//                            .map(MenuOptionResponseDto::new)  // MenuOption을 MenuOptionResponseDto로 변환
//                            .collect(Collectors.toList());

                    // OrderDetailResponseDto 생성
//                    return new OrderDetailResponseDto(orderIdFromDto, orderMenuName, menuTotalPrice, quantity, menuOptions);
                    return new OrderDetailResponseDto(orderIdFromDto, orderMenuName, menuTotalPrice, quantity, null);
                }).collect(Collectors.toList());
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

//    List<Object[]> orderDetailsData = orderDetailRepository.findOrderDetailsWithOptions(orderId);
//    List<OrderDetail> menuOptions = orderDetailRepository.findMenuOptionByOrderId(orderId);
//    List<OrderDetailResponseDto> orderDetailResponseDtoList = new ArrayList<>();
////            orderDetailResponseDtoList.addAll(orderDetailsData.stream()
////                    .map(OrderDetailResponseDto::new)
////                    .collect(Collectors.toList()));
////            orderDetailResponseDtoList.addAll(menuOptions.stream()
////                    .map(OrderDetailResponseDto::new)
////                    .collect(Collectors.toList()));
//
//    Map<String, OrderDetailResponseDto> orderDetailMap = orderDetailsData.stream()
//            .map(OrderDetailResponseDto::new)
//            .collect(Collectors.toMap(
//                    OrderDetailResponseDto::getOrderMenuName,
//                    dto -> dto,
//                    (existing, replacement) -> existing
//            ));
//            menuOptions.forEach(orderDetail -> {
//        String orderMenuName = orderDetail.getOrderMenuName();
//        OrderDetailResponseDto dto = orderDetailMap.get(orderMenuName);
//
//        if (dto != null) {
//            List<MenuOptionResponseDto> menuOptionsList = orderDetail.getMenuOption().stream()
//                    .map(MenuOptionResponseDto::new)
//                    .collect(Collectors.toList());
//            dto.setMenuOption(menuOptionsList);
//        }
//    });
//
//            orderDetailResponseDtoList.addAll(orderDetailMap.values());

}
