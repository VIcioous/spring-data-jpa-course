package com.example.demo.availableResources.organizationUnit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationUnitRepository extends JpaRepository<OrganizationUnit, Long> {
}
