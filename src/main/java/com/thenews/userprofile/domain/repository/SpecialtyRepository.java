package com.thenews.userprofile.domain.repository;

import com.thenews.userprofile.domain.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
}
