package ru.tilipod.jpa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.tilipod.controller.dto.distributor.CloudImagesDownloadRequest;
import ru.tilipod.controller.dto.teacher.TrainingDto;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Table(name = "distribution")
@EntityListeners(AuditingEntityListener.class)
public class Distribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TrainingDto.DatasetTypeEnum datasetType;

    @NotNull
    private String pathToDataset;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CloudImagesDownloadRequest.CloudTypeEnum cloudType;

    @NotNull
    private String cloudToken;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id")
    private Task task;

    @CreatedDate
    private ZonedDateTime createdDateTime;

    @LastModifiedDate
    private ZonedDateTime lastUpdatedDateTime;
}
