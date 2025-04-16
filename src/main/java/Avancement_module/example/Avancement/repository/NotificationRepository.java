package Avancement_module.example.Avancement.repository;

import Avancement_module.example.Avancement.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long>{
    List<Notification> findByRecipientIdAndReadFalse(Long recipientId);

    List<Notification> findByRecipientId(Long recipientId);

    @Query("SELECT COUNT(n) FROM Notification n WHERE n.recipientId = :recipientId AND n.read = false")
    long countUnreadByRecipientId(@Param("recipientId") Long recipientId);
}
