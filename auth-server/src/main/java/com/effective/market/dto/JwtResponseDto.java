package com.effective.market.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Dto для исходящих JWT токена
 * @author YermukhanJJ
 */
@Getter
@Setter
@NoArgsConstructor
public class JwtResponseDto {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private Double balance;
    private List<String> roles;

    public JwtResponseDto(String accessToken, Long id, String username, String email, Double balance, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.balance = balance;
        this.roles = roles;
    }
}
