package sarik.dev.foodwaveproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class Auditable {

    @CreationTimestamp // Obyekt yaratishda avtomatik tarzda vaqt qo'shadi
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt; // Yaratilgan vaqt

    @CreatedBy
    @Column(updatable = false, nullable = false)
    private Long createdBy; // Yaratuvchi foydalanuvchining ID raqami

    @UpdateTimestamp // Har safar yangilanganda avtomatik tarzda vaqt yangilanadi
    private LocalDateTime updatedAt; // Yangilangan vaqt

    @LastModifiedBy
    private Long updatedBy; // Yangilovchi foydalanuvchining ID raqami
}
