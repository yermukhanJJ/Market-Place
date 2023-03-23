package com.effective.market.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Dto для просмотра уведомлений
 *
 * @author YermukhanJJ
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private String header;

    private String content;

    private LocalDateTime date;
}
