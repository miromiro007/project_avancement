package Avancement_module.example.Avancement.controller;


import Avancement_module.example.Avancement.entity.Notification;
import Avancement_module.example.Avancement.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<Notification> sendNotification(@RequestParam Long senderId,
                                                         @RequestParam Long recipientId,
                                                         @RequestParam Long chapitreId,
                                                         @RequestParam String type,
                                                         @RequestParam String message) {

        Notification notif = new Notification();
        notif.setSenderId(senderId);
        notif.setRecipientId(recipientId);
        notif.setChapitreId(chapitreId);
        notif.setType(Notification.TypeNotification.valueOf(type));
        notif.setMessage(message);
        notif.setTitle("Notification Chapitre");

        return ResponseEntity.ok(notificationService.sendNotification(notif));
    }

    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<Notification>> getUnread(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getUnreadNotifications(userId));
    }

    @GetMapping("/user/{userId}/all")
    public ResponseEntity<List<Notification>> getAll(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getAllNotifications(userId));
    }

    @GetMapping("/user/{userId}/count-unread")
    public ResponseEntity<Long> getUnreadCount(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.countUnread(userId));
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<?> markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/user/{userId}/read-all")
    public ResponseEntity<?> markAllAsRead(@PathVariable Long userId) {
        notificationService.markAllAsRead(userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.ok().build();
    }
}
