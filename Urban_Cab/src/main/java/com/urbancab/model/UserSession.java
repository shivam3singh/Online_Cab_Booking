package com.urbancab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserSession {
    @Id
    private String userName;
    private Integer userId;
    private String uuid;
    private LocalDateTime userLogInDateTime;
}
