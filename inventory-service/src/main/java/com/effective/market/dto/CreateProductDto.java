package com.effective.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Dto для созданий товаров
 *
 * @author YermukhanJJ
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDto {

    @Size(min = 5, max = 50)
    private String title;

    @Size(min = 5, max = 255)
    private String description;

    private Double price;

    private Integer count;

    private String organizationTitle;

    private List<String> keyWords;

}
