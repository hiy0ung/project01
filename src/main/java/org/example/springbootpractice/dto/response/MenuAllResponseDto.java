package org.example.springbootpractice.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuAllResponseDto {
    private Long menuId;
    private String menuName;
    private Integer menuPrice;
    private String imageUrl;
    private String menuDescription;
    private Boolean isAvailable;
    private String menuCategory;
    private List<MenuOptionAllResponseDto> menuOptions;
}
