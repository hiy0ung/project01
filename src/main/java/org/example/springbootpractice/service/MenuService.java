package org.example.springbootpractice.service;

import jakarta.validation.Valid;
import org.example.springbootpractice.dto.request.MenuRequestDto;
import org.example.springbootpractice.dto.response.MenuAllResponseDto;
import org.example.springbootpractice.dto.response.MenuResponseDto;
import org.example.springbootpractice.dto.response.ResponseDto;
import java.util.List;
import java.util.Map;


public interface MenuService {
    ResponseDto<MenuResponseDto> addMenu(@Valid MenuRequestDto dto);
    ResponseDto<List<MenuAllResponseDto>> getAllMenus();
    ResponseDto<MenuResponseDto> updateMenu(@Valid Long id, MenuRequestDto dto);
    ResponseDto<Void> deleteMenu(Long id);

    ResponseDto<MenuResponseDto> getMenusById(Long id);
}
