package com.effective.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Dto для {@link com.effective.market.entity.Organization}
 * @author YermukhanJJ
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrganizationDto {

    @NotEmpty
    private List<String> organizations;
}
