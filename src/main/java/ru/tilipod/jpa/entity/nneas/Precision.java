package ru.tilipod.jpa.entity.nneas;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Table(schema = "public", name = "precisions")
@EntityListeners(AuditingEntityListener.class)
public class Precision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Double precision;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @CreatedDate
    private ZonedDateTime createdDateTime;

    @LastModifiedDate
    private ZonedDateTime lastUpdatedDateTime;
}
