package com.ideavest.server.Controllers;

import com.ideavest.server.Services.NotificationService;
import com.ideavest.server.models.Notification;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/my")
    public ResponseEntity<List<Notification>> getUserNotifications(@RequestParam String userId) {
        return ResponseEntity.ok(notificationService.getUserNotifications(userId));
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendGlobalNotification(@RequestBody Notification notification) {
        notificationService.sendGlobalNotification(notification);
        return ResponseEntity.ok().build();
    }
}


