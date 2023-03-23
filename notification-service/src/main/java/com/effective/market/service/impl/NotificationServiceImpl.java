package com.effective.market.service.impl;

import com.effective.market.dao.NotificationRepository;
import com.effective.market.dao.ProfileRepository;
import com.effective.market.dto.NotificationDto;
import com.effective.market.dto.SendNotificationDto;
import com.effective.market.dto.SendPrivateNotificationDto;
import com.effective.market.entity.Notification;
import com.effective.market.entity.Profile;
import com.effective.market.exception.BadRequestException;
import com.effective.market.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final ProfileRepository profileRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public void sendPrivateNotification(SendPrivateNotificationDto sendPrivateNotificationDto) {
        List<Profile> profiles = new ArrayList<>();
        for (Long id : sendPrivateNotificationDto.getProfileIds()) {
            if (!profileRepository.existsById(id))
                continue;
            Profile profile = profileRepository.findById(id)
                    .orElseThrow(() -> new BadRequestException("Profile not found"));
            profiles.add(profile);
        }
        Notification notification = new Notification();
        notification.setProfiles(profiles);
        notification.setHeader(sendPrivateNotificationDto.getHeader());
        notification.setContent(sendPrivateNotificationDto.getContent());
        notification.setDate(LocalDateTime.now());

        if (!profiles.isEmpty())
            notificationRepository.save(notification);

    }

    @Override
    public void sendPublicNotification(SendNotificationDto sendNotificationDto) {
        List<Profile> profiles = profileRepository.findAll();
        Notification notification = new Notification();
        notification.setProfiles(profiles);
        notification.setHeader(sendNotificationDto.getHeader());
        notification.setContent(sendNotificationDto.getContent());
        notification.setDate(LocalDateTime.now());

        notificationRepository.save(notification);
    }

    @Override
    public List<NotificationDto> getProfileNotification(Principal principal) {
        Profile profile = profileRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new BadRequestException("Profile not found"));
        List<Profile> profiles = new ArrayList<>();
        profiles.add(profile);
        List<Notification> notifications = notificationRepository.findAllByProfilesIn(profiles);
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDto notificationDto = new NotificationDto(
                    notification.getHeader(),
                    notification.getContent(),
                    notification.getDate()
            );
            notificationDtoList.add(notificationDto);
        }

        return notificationDtoList;
    }
}
