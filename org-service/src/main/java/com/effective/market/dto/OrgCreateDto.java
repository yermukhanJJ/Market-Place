package com.effective.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * Dto для созданий организаций
 *
 * @author YermukhanJJ
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrgCreateDto {

    @Size(min = 5, max = 100)
    private String title;

    @Size(min = 5, max = 255)
    private String description;
}
