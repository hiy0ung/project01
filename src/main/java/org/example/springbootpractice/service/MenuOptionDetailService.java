package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.request.MenuOptionDetailRequestDto;
import org.example.springbootpractice.dto.response.MenuOptionDetailResponseDto;
import org.example.springbootpractice.dto.response.ResponseDto;

import java.util.List;

public interface MenuOptionDetailService {
    ResponseDto<MenuOptionDetailResponseDto> addOptionDetail(MenuOptionDetailRequestDto dto);

    ResponseDto<List<MenuOptionDetailResponseDto>> getAllOptionDetails();

    ResponseDto<MenuOptionDetailResponseDto> getOptionDetailById(Long id);

    ResponseDto<MenuOptionDetailResponseDto> updateOptionDetail(MenuOptionDetailRequestDto dto, Long id);

    ResponseDto<Void> deleteOptionDetail(Long id);
}
