package com.effective.market.controller;

import com.effective.market.dto.NotificationDto;
import com.effective.market.dto.SendNotificationDto;
import com.effective.market.dto.SendPrivateNotificationDto;
import com.effective.market.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * REST controller для операций с уведомлениями
 *
 * @author YermukhanJJ
 */
@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    /**
     * @param sendPrivateNotificationDto <b>Response code</b>: 200<br>
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/private")
    @ResponseStatus(value = HttpStatus.OK)
    public void sendPrivateNotification(@RequestBody @Valid SendPrivateNotificationDto sendPrivateNotificationDto) {
        notificationService.sendPrivateNotification(sendPrivateNotificationDto);
    }

    /**
     * @param sendNotificationDto <b>Response code</b>: 200<br>
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/public")
    @ResponseStatus(value = HttpStatus.OK)
    public void sendNotification(@RequestBody @Valid SendNotificationDto sendNotificationDto) {
        notificationService.sendPublicNotification(sendNotificationDto);
    }

    /**
     * @param principal
     * @return <b>Response code</b>: 200<br>
     * <b>Body</b>: {@link List<NotificationDto>}
     */
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<NotificationDto>> getNotification(Principal principal) {
        return ResponseEntity.ok(notificationService.getProfileNotification(principal));
    }

}
