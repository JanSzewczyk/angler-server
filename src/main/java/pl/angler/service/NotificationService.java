package pl.angler.service;

import pl.angler.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    List<NotificationDto> getUserNotification(String userEmail);
    void removeUserNotification(Long id, String userEmail);
    void readUserNotification(Long id, String userEmail);
}
