package Avancement_module.example.Avancement.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeNotification type;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String message;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "`read`", nullable = false) // Échapper le mot-clé réservé
    private boolean read;

    private Long senderId;

    @Column(length = 255)
    private String senderName;

    private Long chapitreId;

    @Column(length = 255)
    private String chapitreTitle;

    private Long recipientId;

    public enum TypeNotification {
        CHAPTER_VALIDATION,
        CHAPTER_DELAY,
        WARNING,
        INFO
    }
}
