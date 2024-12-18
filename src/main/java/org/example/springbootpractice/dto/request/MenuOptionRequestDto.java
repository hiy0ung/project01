package org.example.springbootpractice.dto.request;

import lombok.Data;

@Data
public class MenuOptionRequestDto {
    private Long menuId;
    private String optionName;
}
