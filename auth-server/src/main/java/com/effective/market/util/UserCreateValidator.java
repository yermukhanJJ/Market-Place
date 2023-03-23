package com.effective.market.util;

import com.effective.market.dao.ProfileRepository;
import com.effective.market.dto.UserCreateDto;
import com.effective.market.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * Компонент для валидаций входящих параметров регистраций
 * @author YermukhanJJ
 */
@Component
@RequiredArgsConstructor
public class UserCreateValidator implements Validator {

    private final ProfileRepository profileRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserCreateDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserCreateDto userCreateDto = (UserCreateDto) target;

        String regexEmail = "^[\\w]+(?:\\.[\\w]+)*@(?:[a-z0-9-]+\\.)+[\\\\a-zA-Z]{2,6}";
        Pattern patternEmail = Pattern.compile(regexEmail);

        if (!patternEmail.matcher(userCreateDto.getEmail()).matches()) {
            errors.rejectValue("email", "", "Email address is invalid! Format:example@example.com");
            throw new BadRequestException("Email is invalid! Format: example@example.com");
        }
        if (profileRepository.existsByEmail(userCreateDto.getEmail())) {
            errors.rejectValue("email", "", "Email is already taken!");
            throw new BadRequestException("Email is already taken");
        }
        if (profileRepository.existsByUsername(userCreateDto.getUsername())) {
            errors.rejectValue("username", "", "Username is already taken!");
            throw new BadRequestException("Username is already taken");
        }
    }
}
