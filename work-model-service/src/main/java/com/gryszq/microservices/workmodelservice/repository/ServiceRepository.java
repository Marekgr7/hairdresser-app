package com.gryszq.microservices.workmodelservice.repository;

import com.gryszq.microservices.workmodelservice.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    @Query(value = "SELECT name FROM hairdresser.service", nativeQuery = true)
    List<String> selectAllNames();
}
