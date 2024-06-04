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
    @Enumerated(value = EnumType.STRING)
    private Category category;
    @Setter(AccessLevel.NONE)
    private LocalDateTime timeCreated;
    @Setter(AccessLevel.NONE)
    private LocalDateTime timeUpdated;
    @ManyToOne
    private User uploader;
@PrePersist
    private void setTimeCreated() {
        this.timeCreated = LocalDateTime.now();
    }
    @PreUpdate
    private void setTimeUpdated(){
    this.timeUpdated = LocalDateTime.now();
    }
}
