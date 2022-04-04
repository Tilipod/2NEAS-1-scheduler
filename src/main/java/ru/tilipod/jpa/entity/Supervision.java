package ru.tilipod.jpa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Table(name = "supervision")
public class Supervision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double harmony = 0.0;

    @CreatedDate
    private ZonedDateTime createdDateTime;

    @LastModifiedDate
    private ZonedDateTime lastUpdatedDateTime;
}
