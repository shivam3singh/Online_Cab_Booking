package com.urbancab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class UserSession {
    @Id
    private Integer userId;
    private String userType;
    private String uuid;
    private LocalDateTime userLogInDateTime;
}
