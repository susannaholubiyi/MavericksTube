package com.maverickstube.maverickshub.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private  String email;
    private String password;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;

    @PrePersist
    private void setTimeCreated(){
        timeCreated = LocalDateTime.now();
    }
    @PreUpdate
    public void setTimeUpdated() {
        timeUpdated = LocalDateTime.now();
    }

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
    }
}
