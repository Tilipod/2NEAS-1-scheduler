package ru.tilipod.jpa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import ru.tilipod.jpa.entity.enums.TaskStatusEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private UUID processId;

    @NotNull
    private String jsonClient;

    private Boolean withMentoring = false;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TaskStatusEnum status;

    @CreatedDate
    private ZonedDateTime createdDateTime;

    @LastModifiedDate
    private ZonedDateTime lastUpdatedDateTime;
}
