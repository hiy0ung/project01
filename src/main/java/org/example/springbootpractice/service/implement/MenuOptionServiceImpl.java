package org.example.springbootpractice.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ResponseMessage;
import org.example.springbootpractice.dto.request.MenuOptionRequestDto;
import org.example.springbootpractice.dto.response.MenuOptionResponseDto;
import org.example.springbootpractice.dto.response.ResponseDto;

import org.example.springbootpractice.entity.Menu;
import org.example.springbootpractice.entity.MenuOption;
import org.example.springbootpractice.repository.MenuOptionRepository;
import org.example.springbootpractice.repository.MenuRepository;
import org.example.springbootpractice.service.MenuOptionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuOptionServiceImpl implements MenuOptionService {

    private final MenuOptionRepository menuOptionRepository;

    private final MenuRepository menuRepository;

    @Override
    public ResponseDto<MenuOptionResponseDto> addMenuOption(MenuOptionRequestDto dto) {
        MenuOptionResponseDto data = null;

        try {
            Menu menuId = menuRepository.findById(dto.getMenuId())
                    .orElseThrow(() -> new Error(ResponseMessage.NOT_EXIST_DATA));

            MenuOption menuOption = MenuOption.builder()
//                    .menu(menuId)
                    .optionName(dto.getOptionName())
                    .build();

            MenuOption saveOption = menuOptionRepository.save(menuOption);
//            data = new MenuOptionResponseDto(saveOption);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<MenuOptionResponseDto>> getAllMenuOptions() {
        List<MenuOptionResponseDto> data = null;

        try {
            List<MenuOption> options = menuOptionRepository.findAll();

//            data = options.stream()
//                    .map(MenuOptionResponseDto::new)
//                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<MenuOptionResponseDto> getMenuOptionById(Long id) {
        MenuOptionResponseDto data = null;
        Long menuOptionId = id;

        try {
            Optional<MenuOption> menuOptionOptional = menuOptionRepository.findById(menuOptionId);

            if (menuOptionOptional.isPresent()) {
//                data = new MenuOptionResponseDto(menuOptionOptional.get());
            } else {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<MenuOptionResponseDto> updateMenuOption(MenuOptionRequestDto dto, Long id) {
        MenuOptionResponseDto data = null;
        Long menuOptionId = id;

        try {
            Optional<MenuOption> menuOptionOptional = menuOptionRepository.findById(menuOptionId);

            if (menuOptionOptional.isPresent()) {
                MenuOption menuOption = menuOptionOptional.get().toBuilder()
                     .optionName(dto.getOptionName())
                     .build();

                MenuOption updateOption = menuOptionRepository.save(menuOption);
//                data = new MenuOptionResponseDto(updateOption);
            } else {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
        } catch (Exception e) {
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deleteMenuOption(Long id) {
        Long menuOptionId = id;
        try {
            Optional<MenuOption> menuOptionOptional = menuOptionRepository.findById(menuOptionId);

            if (menuOptionOptional.isPresent()) {
                MenuOption menuOption = menuOptionOptional.get();
                menuOptionRepository.delete(menuOption);
            } else {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}
