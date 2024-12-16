package org.example.springbootpractice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuOptionDetailAllResponseDto {
    private Long detailId;
    private String optionDetailName;
    private Integer addtionalFee;
}
