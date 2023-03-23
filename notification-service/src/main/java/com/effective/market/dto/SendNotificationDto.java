package com.effective.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * Dto для отправки уведомлений для всех пользователей
 *
 * @author YermukhanJJ
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendNotificationDto {
    @Size(min = 1, max = 50)
    private String header;

    @Size(min = 1, max = 255)
    private String content;
}
