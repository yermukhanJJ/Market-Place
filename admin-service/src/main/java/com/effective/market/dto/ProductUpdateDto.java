package com.effective.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto для обновлений данных о товарах {@link com.effective.market.entity.Product}
 * @author YermukhanJJ
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {
    private Long idProfile;
    private String title;
    private String description;
    private Double price;
    private Integer count;
    private Long idOrganization;
}
