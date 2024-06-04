package com.maverickstube.maverickshub.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String description;
    private Category category;
    @Setter(AccessLevel.NONE)
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
    @ManyToOne
    private User user;
@PrePersist
    private void setTimeCreated() {
        this.timeCreated = LocalDateTime.now();
    }
}
