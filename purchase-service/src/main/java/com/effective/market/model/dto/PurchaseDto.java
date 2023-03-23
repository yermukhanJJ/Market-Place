package com.effective.market.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Dto для совершений покупки
 *
 * @author YermukhanJJ
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDto {

    private Long idProduct;

    @NotNull
    @Min(1)
    private Integer count;

}
