package com.effective.market.dto;

import com.effective.market.entity.Discount;
import com.effective.market.entity.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Dto для вывода товаров
 *
 * @author YermukhanJJ
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {

    private Long id;

    private String title;

    private String description;

    private Double price;

    private Integer count;

    private Organization organization;

    private Double score;

    private List<String> keyWords;

    private Discount discount;


}
