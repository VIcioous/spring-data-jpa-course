package com.example.demo.availableResources.organizationUnit.desk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeskRepository extends JpaRepository<DeskRecord, Long> {
}
