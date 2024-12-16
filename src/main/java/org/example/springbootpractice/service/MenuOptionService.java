package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.request.MenuOptionRequestDto;
import org.example.springbootpractice.dto.response.MenuOptionResponseDto;
import org.example.springbootpractice.dto.response.ResponseDto;

import java.util.List;

public interface MenuOptionService {
    ResponseDto<MenuOptionResponseDto> addMenuOption(MenuOptionRequestDto dto);

    ResponseDto<List<MenuOptionResponseDto>> getAllMenuOptions();

    ResponseDto<MenuOptionResponseDto> getMenuOptionById(Long id);

    ResponseDto<MenuOptionResponseDto> updateMenuOption(MenuOptionRequestDto dto, Long id);

    ResponseDto<Void> deleteMenuOption(Long id);
}
