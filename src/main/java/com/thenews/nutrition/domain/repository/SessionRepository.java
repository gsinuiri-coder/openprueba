package com.thenews.nutrition.domain.repository;

import com.thenews.nutrition.domain.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByAdviceId(Long adviceId);
    Optional<Session> findByIdAndAdviceId(Long id, Long adviceId);
}
