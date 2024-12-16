package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.request.LoginRequestDto;
import org.example.springbootpractice.dto.request.SignUpRequestDto;
import org.example.springbootpractice.dto.response.LoginResponseDto;
import org.example.springbootpractice.dto.response.ResponseDto;
import org.example.springbootpractice.dto.response.SignUpResponseDto;

public interface AuthService {
    ResponseDto<SignUpResponseDto> signUp(SignUpRequestDto dto);

    ResponseDto<LoginResponseDto> login(LoginRequestDto dto);
}
