package org.example.springbootpractice.dto.request;

import lombok.Data;

@Data
public class MenuOptionDetailRequestDto {
    private Long menuOptionId;
    private String optionDetailName;
    private int additionalFee;
}
