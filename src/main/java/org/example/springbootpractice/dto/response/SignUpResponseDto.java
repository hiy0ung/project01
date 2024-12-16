package org.example.springbootpractice.dto.response;

import lombok.Getter;
import org.example.springbootpractice.entity.User;

@Getter
public class SignUpResponseDto {
    private String userName;


    public SignUpResponseDto(User user) {
        this.userName = user.getUserName();
    }
}
