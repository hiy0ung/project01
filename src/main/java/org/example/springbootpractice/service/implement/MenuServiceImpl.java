package org.example.springbootpractice.service.implement;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ResponseMessage;
import org.example.springbootpractice.dto.request.MenuRequestDto;
import org.example.springbootpractice.dto.response.*;
import org.example.springbootpractice.entity.Menu;
import org.example.springbootpractice.entity.MenuCategory;
import org.example.springbootpractice.repository.MenuCategoryRepository;
import org.example.springbootpractice.repository.MenuRepository;
import org.example.springbootpractice.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private final MenuRepository menuRepository;
    @Autowired
    private final MenuCategoryRepository menuCategoryRepository;

    public ResponseDto<MenuResponseDto> addMenu(@Valid MenuRequestDto dto) {
        MenuResponseDto data = null;

        try {
            Optional<MenuCategory> OptionalCategory = menuCategoryRepository.findById(dto.getCategoryId());
            if (OptionalCategory.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

            MenuCategory category = OptionalCategory.get();
            Menu menu = Menu.builder()
                    .menuName(dto.getMenuName())
                    .imageUrl(dto.getImageUrl())
                    .menuDescription(dto.getMenuDescription())
                    .menuPrice(dto.getMenuPrice())
                    .isAvailable(dto.getIsAvailable())
                    .menuCategory(category)
                    .build();

            Menu savedMenu = menuRepository.save(menu);
            MenuResponseDto responseDto = new MenuResponseDto(savedMenu);
            data = responseDto;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<List<MenuAllResponseDto>> getAllMenus() {
        List<MenuAllResponseDto> resultList = null;

        try {
            resultList = menuRepository.findAllMenuWithCategoryAndOption().stream().collect(Collectors.groupingBy(
                    a -> (Long) a[0],
                Collectors.collectingAndThen(
                        Collectors.toList(),
                        b -> {
                            List<MenuOptionAllResponseDto> optionAllResponseDtos = b.stream().collect(Collectors.groupingBy(
                                    c -> (Long) c[7],
                                    Collectors.collectingAndThen(
                                            Collectors.toList(),
                                            d -> {
                                                List<MenuOptionDetailAllResponseDto> optionDetailAllResponseDtos = d.stream().collect(Collectors.groupingBy(
                                                        e -> (Long) e[9],
                                                        Collectors.collectingAndThen(
                                                                Collectors.toList(),
                                                                f -> new MenuOptionDetailAllResponseDto((Long)f.get(0)[9], (String)f.get(0)[10], (Integer)f.get(0)[11])
                                                        )
                                                )).values().stream().collect(Collectors.toList());
                                                return new MenuOptionAllResponseDto((Long)d.get(0)[7], (String)d.get(0)[8], optionDetailAllResponseDtos);
                                            }
                                    )
                            )).values().stream().collect(Collectors.toList());
                            return new MenuAllResponseDto(
                                    (Long)b.get(0)[0],
                                    (String)b.get(0)[1],
                                    (Integer) b.get(0)[2],
                                    (String) b.get(0)[3],
                                    (String) b.get(0)[4],
                                    (Boolean) b.get(0)[5],
                                    (String) b.get(0)[6],
                                    optionAllResponseDtos);
                        }
                )
            )).values().stream().collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);

        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, resultList);
    }

    public ResponseDto<MenuResponseDto> updateMenu(@Valid Long id, MenuRequestDto dto) {
        MenuResponseDto data = null;

        try {
            Optional<MenuCategory> OptionalCategory = menuCategoryRepository.findById(dto.getCategoryId());
            if (OptionalCategory.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
            MenuCategory category = OptionalCategory.get();

            Optional<Menu> OptionalMenu = menuRepository.findById(id);
            if (OptionalMenu.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
            Menu menu = OptionalMenu.get();
            menu.setImageUrl(dto.getImageUrl());
            menu.setMenuName(dto.getMenuName());
            menu.setMenuDescription(dto.getMenuDescription());
            menu.setMenuPrice(dto.getMenuPrice());
            menu.setIsAvailable(dto.getIsAvailable());
            menu.setMenuCategory(category);
            menuRepository.save(menu);
            data = new MenuResponseDto(menu);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<Void> deleteMenu(Long id) {
        try {
            Optional<Menu> optionalMenu = menuRepository.findById(id);
            if (optionalMenu.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
            menuRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

    @Override
    public ResponseDto<MenuResponseDto> getMenusById(Long menuId) {
        MenuResponseDto data = null;

        try {
            Optional<Menu> menuOptional = menuRepository.findById(menuId);

            if (menuOptional.isPresent()) {

                data = new MenuResponseDto(menuOptional.get());
            } else {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
        } catch (Exception e) {
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}

