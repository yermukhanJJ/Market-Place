package com.effective.market.controller;

import com.effective.market.dto.JwtResponseDto;
import com.effective.market.dto.UserCreateDto;
import com.effective.market.dto.UserLoginDto;
import com.effective.market.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * REST контроллер для операций входа и регистраций на систему
 * @author YermukhanJJ
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     *
     * @param userLoginDto
     * @return <b>Response code</b>: 200<br>
     * <b>Body</b>: {@link JwtResponseDto} object
     */
    @PostMapping("/login")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<JwtResponseDto> login(@RequestBody @Valid UserLoginDto userLoginDto) {
        return ResponseEntity.ok(authService.getToken(userLoginDto));
    }

    /**
     *
     * @param userCreateDto
     * @param result
     * <b>Response code</b>: 201<br>
     */
    @PostMapping("/sign-up")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createAccount(@RequestBody @Valid UserCreateDto userCreateDto, BindingResult result) {
        authService.registerUser(userCreateDto,result);
    }
}
