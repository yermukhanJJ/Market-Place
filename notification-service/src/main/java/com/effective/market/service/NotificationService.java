package com.effective.market.service;

import com.effective.market.dto.NotificationDto;
import com.effective.market.dto.SendNotificationDto;
import com.effective.market.dto.SendPrivateNotificationDto;

import java.security.Principal;
import java.util.List;

/**
 * Сервис уведомлений
 *
 * @author YermukhanJJ
 */
public interface NotificationService {
    /**
     * Отправка уведомлений только на указанных пользователей
     *
     * @param sendPrivateNotificationDto
     */
    void sendPrivateNotification(SendPrivateNotificationDto sendPrivateNotificationDto);

    /**
     * Отправка уведомлений для всех пользователей
     *
     * @param sendNotificationDto
     */
    void sendPublicNotification(SendNotificationDto sendNotificationDto);

    /**
     * Просмотр уведомлений
     *
     * @param principal
     * @return {@link NotificationDto}
     */
    List<NotificationDto> getProfileNotification(Principal principal);
}
