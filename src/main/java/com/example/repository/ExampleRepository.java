package com.example.repository;

import com.example.model.ExampleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<ExampleModel, Long> {
    // You can define custom query methods here if needed
}
