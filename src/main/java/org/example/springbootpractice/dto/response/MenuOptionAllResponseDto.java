package org.example.springbootpractice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuOptionAllResponseDto {
    private Long menuOptionId;
    private String optionName;

    private List<MenuOptionDetailAllResponseDto> optionDetails;
}
