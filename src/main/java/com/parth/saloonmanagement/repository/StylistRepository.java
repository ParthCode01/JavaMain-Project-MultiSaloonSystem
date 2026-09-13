package com.parth.saloonmanagement.repository;

import com.parth.saloonmanagement.entity.Stylist;
import com.parth.saloonmanagement.entity.Tenant;
import com.parth.saloonmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StylistRepository extends JpaRepository<Stylist, Long> {

    Optional<Stylist> findByIdAndTenant(Long id, Tenant tenant);

    Optional<Stylist> findByUserAndTenant(User user, Tenant tenant);

    List<Stylist> findByTenant(Tenant tenant);
}