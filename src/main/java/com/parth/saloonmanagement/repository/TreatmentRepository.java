package com.parth.saloonmanagement.repository;

import com.parth.saloonmanagement.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
}
