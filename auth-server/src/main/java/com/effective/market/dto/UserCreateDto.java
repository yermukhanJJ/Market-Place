package com.effective.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Dto входящих данных для регистраций в систему
 * @author YermukhanJJ
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

    @Size(min = 3, max = 100)
    private String username;

    @Size(min = 5, max = 100)
    private String email;

    @Size(min = 8, max = 100)
    private String password;

    private Set<String> role;
}
