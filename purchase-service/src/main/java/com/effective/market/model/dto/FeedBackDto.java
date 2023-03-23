package com.effective.market.model.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Dto для отзыва
 *
 * @author YermukhanJJ
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedBackDto {
    @NotNull
    @Size(min = 1)
    private String description;

    @Min(1)
    @Max(10)
    private Integer score;
}
