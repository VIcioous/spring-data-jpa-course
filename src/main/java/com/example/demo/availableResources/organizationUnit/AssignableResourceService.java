package com.example.demo.availableResources.organizationUnit;

import java.util.List;
import java.util.Optional;

public interface AssignableResourceService<T, D> {

    Long add(T t);

    void update(T t, Long id);

    void delete(Long id);

    Optional<D> get(Long id);

    List<D> getAll();

    void assignToUnit(ResourceToUnitAssignmentData assignmentData);
}
