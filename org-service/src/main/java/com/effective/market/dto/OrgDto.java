package com.effective.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto для вывода организаций
 *
 * @author YermukhanJJ
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrgDto {
    private String title;
    private String description;
    private String status;
}
