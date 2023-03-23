package com.effective.market.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Dto для вывода историй покупок
 *
 * @author YermukhanJJ
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseStoryDto {
    private Long id;

    private String organizationTitle;

    private String productTitle;

    private Double amount;

    private Integer count;

    private LocalDateTime date;
}
