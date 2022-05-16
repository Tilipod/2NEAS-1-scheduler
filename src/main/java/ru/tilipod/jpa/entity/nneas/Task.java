package ru.tilipod.jpa.entity.nneas;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.tilipod.jpa.entity.nneas.enums.TaskStatusEnum;
import ru.tilipod.jpa.entity.security.User;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Table(schema = "public", name = "task")
@EntityListeners(AuditingEntityListener.class)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private UUID processId;

    @NotNull
    private String jsonNetworkStructure;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TaskStatusEnum status;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreatedDate
    private ZonedDateTime createdDateTime;

    @LastModifiedDate
    private ZonedDateTime lastUpdatedDateTime;
}
