package org.example.springbootpractice.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ResponseMessage;
import org.example.springbootpractice.dto.request.MenuOptionDetailRequestDto;
import org.example.springbootpractice.dto.response.MenuOptionDetailResponseDto;
import org.example.springbootpractice.dto.response.ResponseDto;
import org.example.springbootpractice.entity.MenuOption;
import org.example.springbootpractice.entity.MenuOptionDetail;
import org.example.springbootpractice.repository.MenuOptionDetailRepository;
import org.example.springbootpractice.repository.MenuOptionRepository;
import org.example.springbootpractice.service.MenuOptionDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuOptionDetailServiceImpl implements MenuOptionDetailService {

    private final MenuOptionDetailRepository menuOptionDetailRepository;

    private final MenuOptionRepository menuOptionRepository;

    @Override
    public ResponseDto<MenuOptionDetailResponseDto> addOptionDetail(MenuOptionDetailRequestDto dto) {
        MenuOptionDetailResponseDto data = null;

        try {
            MenuOption optionId = menuOptionRepository.findById(dto.getMenuOptionId())
                    .orElseThrow(() -> new Error(ResponseMessage.NOT_EXIST_DATA));

            MenuOptionDetail menuOptionDetail = MenuOptionDetail.builder()
                    .menuOption(optionId)
                    .optionDetailName(dto.getOptionDetailName())
                    .additionalFee(dto.getAdditionalFee())
                    .build();

            MenuOptionDetail saveOptionDetail = menuOptionDetailRepository.save(menuOptionDetail);
//            data = new MenuOptionDetailResponseDto(saveOptionDetail);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
//        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

    @Override
    public ResponseDto<List<MenuOptionDetailResponseDto>> getAllOptionDetails() {
        List<MenuOptionDetailResponseDto> data = null;

        try {
            List<MenuOptionDetail> optionDetails = menuOptionDetailRepository.findAll();

//            data = optionDetails.stream()
//                    .map(MenuOptionDetailResponseDto::new)
//                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
//        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

    @Override
    public ResponseDto<MenuOptionDetailResponseDto> getOptionDetailById(Long id) {
        MenuOptionDetailResponseDto data = null;
        Long optionDetailId = id;

        try {
            Optional<MenuOptionDetail> menuOptionDetailOptional = menuOptionDetailRepository.findById(optionDetailId);

            if (menuOptionDetailOptional.isPresent()) {
//                data = new MenuOptionDetailResponseDto(menuOptionDetailOptional.get());
            } else {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
//        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

    @Override
    public ResponseDto<MenuOptionDetailResponseDto> updateOptionDetail(MenuOptionDetailRequestDto dto, Long id) {
        MenuOptionDetailResponseDto data = null;
        Long optionDetailId = id;

        try {
            Optional<MenuOptionDetail> menuOptionDetailOptional = menuOptionDetailRepository.findById(optionDetailId);

            if (menuOptionDetailOptional.isPresent()) {
                MenuOptionDetail menuOptionDetail = menuOptionDetailOptional.get().toBuilder()
                        .optionDetailName(dto.getOptionDetailName())
                        .additionalFee(dto.getAdditionalFee())
                        .build();

                MenuOptionDetail updateOptionDetail = menuOptionDetailRepository.save(menuOptionDetail);
//                data = new MenuOptionDetailResponseDto(updateOptionDetail);
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
    public ResponseDto<Void> deleteOptionDetail(Long id) {
        Long optionDetailId = id;

        try {
            Optional<MenuOptionDetail> menuOptionDetailOptional = menuOptionDetailRepository.findById(optionDetailId);

            if (menuOptionDetailOptional.isPresent()) {
                MenuOptionDetail menuOptionDetail = menuOptionDetailOptional.get();
                menuOptionDetailRepository.delete(menuOptionDetail);
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
