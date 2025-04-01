package com.ideavest.server.Services;

import com.ideavest.server.models.Notification;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;
    private final List<Notification> notifications = new CopyOnWriteArrayList<>();

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Send notification to a specific user
    public void sendNotificationToUser(String userId, Notification notification) {
        notifications.add(notification);
        messagingTemplate.convertAndSendToUser(userId, "notifications", notification);
    }

    // Send a global notification
    public void sendGlobalNotification(Notification notification) {
        notifications.add(notification);
        messagingTemplate.convertAndSend("/topic/global-notifications", notification);
    }

    // Get all notifications for a user
    public List<Notification> getUserNotifications(String userId) {
        return notifications.stream()
                .filter(n -> n.getUserId().equals(userId))
                .toList();
    }
}
