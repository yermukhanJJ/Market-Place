package com.effective.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Dto для отправки уведомлений на указанных пользователей
 *
 * @author YermukhanJJ
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendPrivateNotificationDto {
    private List<Long> profileIds;

    @Size(min = 1, max = 50)
    private String header;

    @Size(min = 1, max = 255)
    private String content;
}
