package com.effective.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * Dto для управлений балансом пользователя
 * @author YermukhanJJ
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDto {
    @Min(0)
    private Double balance;
}
