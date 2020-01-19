package pl.angler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.angler.dto.NotificationDto;
import pl.angler.entity.Notification;
import pl.angler.entity.User;
import pl.angler.exception.NotFoundException;
import pl.angler.repository.FriendRepository;
import pl.angler.repository.NotificationRepository;
import pl.angler.repository.UserRepository;
import pl.angler.service.NotificationService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<NotificationDto> getUserNotification(String userEmail) {
        return this.notificationRepository
                .findAllByUser_EmailOrderByReleaseDateDescReleaseTimeDesc(userEmail)
                .stream()
                .map(notification -> {
                    NotificationDto note = new NotificationDto();
                    note.setId(notification.getId());
                    note.setLooked(notification.getLooked());
                    note.setMessage(notification.getMessage());
                    note.setReleaseDate(notification.getReleaseDate());
                    note.setReleaseTime(notification.getReleaseTime());
                    return note;
                }).collect(Collectors.toList());
    }

    @Override
    public void readUserNotification(Long id, String userEmail) {
        Optional<Notification> findNotification = this.notificationRepository.findByIdAndUser_EmailAndLookedFalse(id, userEmail);
        if(!findNotification.isPresent())
            throw new NotFoundException("Notification not found.");

        Notification notification = findNotification.get();
        notification.setLooked(true);

        this.notificationRepository.save(notification);
    }

    @Override
    public void removeUserNotification(Long id, String userEmail) {
        Optional<Notification> findNotification = this.notificationRepository.findByIdAndUser_Email(id, userEmail);
        if(!findNotification.isPresent())
            throw new NotFoundException("Notification not found.");

        this.notificationRepository.delete(findNotification.get());
    }

    @Override
    public void sendNotificationToUser(String message, String toNick) {
        Optional<User> findUser = this.userRepository.findByNick(toNick);
        if(!findUser.isPresent())
            throw new NotFoundException("User not found.");

        this.sendNotification(findUser.get(), message);
    }

    @Override
    public void sendNotificationToUsers(String message, String fromUserEmail) {
        this.friendRepository.findAllByUser_emailAndAcceptedTrueOrInvitedUser_emailAndAcceptedTrue(fromUserEmail, fromUserEmail)
                .forEach(friend -> {
                    this.sendNotification(friend.getUser().getEmail().equals(fromUserEmail) ? friend.getInvitedUser() : friend.getUser(), message);
                });
    }

    private void sendNotification(User toUser, String message) {
        Notification newNotification = new Notification();
        newNotification.setMessage(message);
        newNotification.setReleaseDate(LocalDate.now());
        newNotification.setReleaseTime(LocalTime.now());
        newNotification.setUser(toUser);
        this.notificationRepository.save(newNotification);
    }
}
