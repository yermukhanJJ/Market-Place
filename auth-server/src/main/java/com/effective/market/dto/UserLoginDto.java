package com.effective.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * Dto для входа в систему
 * @author YermukhanJJ
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
