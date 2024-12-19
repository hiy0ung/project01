package org.example.springbootpractice.service;

import jakarta.validation.Valid;
import org.example.springbootpractice.dto.request.MenuRequestDto;
import org.example.springbootpractice.dto.response.MenuGetResponseDto;
import org.example.springbootpractice.dto.response.MenuResponseDto;
import org.example.springbootpractice.dto.response.ResponseDto;
import java.util.List;


public interface MenuService {
    ResponseDto<MenuResponseDto> addMenu(@Valid MenuRequestDto dto);
    ResponseDto<List<MenuGetResponseDto>> getAllMenus();
    ResponseDto<MenuGetResponseDto> getMenusById(Long id);
    ResponseDto<MenuResponseDto> updateMenu(@Valid Long id, MenuRequestDto dto);
    ResponseDto<Void> deleteMenu(Long id);
}
