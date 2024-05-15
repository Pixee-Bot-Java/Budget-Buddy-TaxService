package com.skillstorm.taxservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxservice.models.FilingStatus;

@Repository
public interface FilingStatusRepository extends JpaRepository<FilingStatus, Integer> {
  Optional<FilingStatus> findByStatus(String status);
}

