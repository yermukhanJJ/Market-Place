package com.effective.market.dto;

import com.effective.market.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Dto для вывода данных о аккаунтах {@link com.effective.market.entity.Profile}
 * @author YermukhanJJ
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private Long id;

    private String username;

    private String email;

    private Double balance;

    private Set<Role> roles;
}
