package org.example.springbootpractice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {
    @NotBlank
    private String userId;
    @NotBlank
    private String userPw;
}
