package Avancement_module.example.Avancement.service;

import Avancement_module.example.Avancement.entity.*;
import Avancement_module.example.Avancement.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public Notification sendNotification(Notification notification) {
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRead(false);
        return notificationRepository.save(notification);
    }

    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.findByRecipientIdAndReadFalse(userId);
    }

    public List<Notification> getAllNotifications(Long userId) {
        return notificationRepository.findByRecipientId(userId);
    }

    public long countUnread(Long userId) {
        return notificationRepository.countUnreadByRecipientId(userId);
    }

    public void markAsRead(Long id) {
        Notification notif = notificationRepository.findById(id).orElse(null);
        if (notif != null) {
            notif.setRead(true);
            notificationRepository.save(notif);
        }
    }

    public void markAllAsRead(Long userId) {
        List<Notification> notifs = notificationRepository.findByRecipientIdAndReadFalse(userId);
        notifs.forEach(n -> n.setRead(true));
        notificationRepository.saveAll(notifs);
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
