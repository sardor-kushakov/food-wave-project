package sarik.dev.foodwaveproject.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.generator.EventType;
import sarik.dev.foodwaveproject.annotation.CurrentUserId;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class Auditable {

    @CurrentTimestamp(event = EventType.INSERT, source = SourceType.VM)
    private LocalDateTime createdAt;

    @CurrentUserId(event = EventType.INSERT)
    private Long createdBy;

    @CurrentTimestamp(event = EventType.UPDATE, source = SourceType.VM)
    private LocalDateTime updatedAt;

    @CurrentUserId(event = EventType.UPDATE)
    private Long updatedBy;
}
