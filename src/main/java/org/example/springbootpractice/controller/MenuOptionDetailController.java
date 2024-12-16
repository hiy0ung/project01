package org.example.springbootpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ApiMappingPattern;
import org.example.springbootpractice.dto.request.MenuOptionDetailRequestDto;
import org.example.springbootpractice.dto.response.MenuOptionDetailResponseDto;
import org.example.springbootpractice.dto.response.ResponseDto;
import org.example.springbootpractice.service.MenuOptionDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.MENU_OPTION_DETAILS)
@RequiredArgsConstructor
public class MenuOptionDetailController {

    private final MenuOptionDetailService menuOptionDetailService;

    public static final String OPTION_DETAIL_POST_ADD = "/add";
    public static final String OPTION_DETAIL_GET_LIST = "/";
    public static final String OPTION_DETAIL_GET_ID = "/{id}";
    public static final String OPTION_DETAIL_PUT_ID = "/{id}";
    public static final String OPTION_DETAIL_DELETE_ID = "/{id}";

    @PostMapping(OPTION_DETAIL_POST_ADD)
    public ResponseEntity<ResponseDto<MenuOptionDetailResponseDto>> addOptionDetail(@RequestBody MenuOptionDetailRequestDto dto) {
        ResponseDto<MenuOptionDetailResponseDto> response = menuOptionDetailService.addOptionDetail(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(OPTION_DETAIL_GET_LIST)
    public ResponseEntity<ResponseDto<List<MenuOptionDetailResponseDto>>> getAllOptionDetails() {
        ResponseDto<List<MenuOptionDetailResponseDto>> response = menuOptionDetailService.getAllOptionDetails();
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(OPTION_DETAIL_GET_ID)
    public ResponseEntity<ResponseDto<MenuOptionDetailResponseDto>> getOptionDetailById(@PathVariable Long id) {
        ResponseDto<MenuOptionDetailResponseDto> response = menuOptionDetailService.getOptionDetailById(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(OPTION_DETAIL_PUT_ID)
    public ResponseEntity<ResponseDto<MenuOptionDetailResponseDto>> updateOptionDetail(@RequestBody MenuOptionDetailRequestDto dto, @PathVariable Long id) {
        ResponseDto<MenuOptionDetailResponseDto> response = menuOptionDetailService.updateOptionDetail(dto, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(OPTION_DETAIL_DELETE_ID)
    public ResponseEntity<ResponseDto<Void>> deleteOptionDetail(@PathVariable Long id) {
        ResponseDto<Void> response = menuOptionDetailService.deleteOptionDetail(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
