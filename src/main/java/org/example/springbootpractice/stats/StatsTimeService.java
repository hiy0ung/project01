package org.example.springbootpractice.stats;

import org.example.springbootpractice.dto.response.ResponseDto;

import java.util.List;

public interface StatsTimeService {
    ResponseDto<List<StatsTimeResponseDto>> getRevenueByOrderDate(String orderDate);
}