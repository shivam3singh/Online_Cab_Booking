package com.urbancab.repository;

import com.urbancab.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSessionRepo extends JpaRepository<UserSession, String> {

    public Optional<UserSession> findByUuid(String uuid);
}
