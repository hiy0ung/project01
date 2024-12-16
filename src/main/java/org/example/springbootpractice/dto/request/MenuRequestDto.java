package org.example.springbootpractice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuRequestDto {
//    private Long storeId;
    private Long categoryId;
    private String menuName;
    private String imageUrl;
    private String menuDescription;
    private int menuPrice;
    private Boolean isAvailable;
}