package com.parth.saloonmanagement.repository;

import com.parth.saloonmanagement.entity.Tenant;
import com.parth.saloonmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
}

