package com.effective.market.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Dto для созданий скидки {@link com.effective.market.entity.Discount}
 * @author YermukhanJJ
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCreateDto {

    @NotNull
    @Min(1)
    @Max(100)
    private Integer amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

}
