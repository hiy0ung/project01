package org.example.springbootpractice.service.implement;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.example.springbootpractice.common.constant.ResponseMessage;
import org.example.springbootpractice.dto.request.LoginRequestDto;
import org.example.springbootpractice.dto.request.SignUpRequestDto;
import org.example.springbootpractice.dto.response.LoginResponseDto;
import org.example.springbootpractice.dto.response.ResponseDto;
import org.example.springbootpractice.dto.response.SignUpResponseDto;
import org.example.springbootpractice.entity.User;
import org.example.springbootpractice.provider.JwtProvider;
import org.example.springbootpractice.repository.UserRepository;
import org.example.springbootpractice.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptpasswordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseDto<SignUpResponseDto> signUp(@Valid SignUpRequestDto dto){
        String userId = dto.getUserId();
        String userPw = dto.getUserPw();
        String checkPw = dto.getCheckPw();
        String userName = dto.getUserName();
        String userEmail = dto.getUserEmail();
        String userPhone = dto.getUserPhone();
        String userBusinessNumber = dto.getUserBusinessNumber();
        boolean privacyPolicyAgreed = dto.isPrivacyPolicyAgreed();
        boolean marketingAgreed = dto.isMarketingAgreed();

        SignUpResponseDto data = null;

        if (userId == null || userId.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_ID);
        }

        if (userPw == null || userPw.isEmpty() || checkPw == null || checkPw.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PASSWORD);
        }

        if (!userPw.equals(checkPw)) {
            return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
        }

        if (userPw.length() < 8 || !userPw.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*])[a-zA-Z\\d!@#$%^&*]{10,}$")) {
            return ResponseDto.setFailed(ResponseMessage.WEAK_PASSWORD);
        }

        if (userName == null || userName.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_NAME);
        }

        if (userEmail == null || userEmail.isEmpty() || !EmailValidator.getInstance().isValid(userEmail)) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_EMAIL);
        }

        if (userPhone == null || userPhone.isEmpty() || !userPhone.matches("^\\d{10,11}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PHONE);
        }

        if (userBusinessNumber == null || userBusinessNumber.isEmpty() || !userBusinessNumber.matches("^\\d{10}$")) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PHONE);
        }

        if (userRepository.existsByUserId(userId)) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
        }

        if (userRepository.existsByUserBusinessNumber(userBusinessNumber)) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
        }

        try {
            String encodePassword = bCryptpasswordEncoder.encode(userPw);
            User user = User.builder()
                    .userId(userId)
                    .userPw(encodePassword)
                    .userName(userName)
                    .userEmail(userEmail)
                    .userPhone(userPhone)
                    .userBusinessNumber(userBusinessNumber)
                    .privacyPolicyAgreed(privacyPolicyAgreed)
                    .marketingAgreed(marketingAgreed)
                    .build();

            userRepository.save(user);

            data = new SignUpResponseDto(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<LoginResponseDto> login(@Valid LoginRequestDto dto) {
        String userId = dto.getUserId();
        String userPw = dto.getUserPw();

        LoginResponseDto data = null;

        if(userId == null || userId.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_USER_ID);
        }

        if(userPw == null || userPw.isEmpty()) {
            return ResponseDto.setFailed(ResponseMessage.INVALID_PASSWORD);
        }

        try {
            User user = userRepository.findByUserId(userId)
                    .orElse(null);

            if (user == null) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            }

            if (!bCryptpasswordEncoder.matches(userPw, user.getUserPw())) {
                return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
            }

            String token = jwtProvider.generateJwtToken(userId);
            int exprTime = jwtProvider.getExpiration();
            data = new LoginResponseDto(token, exprTime);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

}
