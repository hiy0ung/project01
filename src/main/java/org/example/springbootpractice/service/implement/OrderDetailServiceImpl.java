package org.example.springbootpractice.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ResponseMessage;
import org.example.springbootpractice.dto.response.OrderDetailResponseDto;
import org.example.springbootpractice.dto.response.ResponseDto;
import org.example.springbootpractice.repository.OrderDetailRepository;
import org.example.springbootpractice.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    @Override
    public ResponseDto<List<OrderDetailResponseDto>> getOrderDetail(Long id) {
        List<OrderDetailResponseDto> data = null;
        Long orderId = id;

        try {
            data = orderDetailRepository.findOrderDetailsWithOptions(orderId).stream()
                    .map(dto -> new OrderDetailResponseDto(
                            (Long) dto[0],
                            (Long) dto[1],
                            (String) dto[2],
                            ((Timestamp) dto[3]).toLocalDateTime(),
                            (String) dto[4],
                            ((Number) dto[5]).intValue(),
                            ((Number) dto[6]).intValue(),
                            (String) dto[7],
                            (String) dto[8],
                            ((Number) dto[9]).intValue()
                    )).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
