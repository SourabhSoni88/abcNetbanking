package com.example.backend.repository;

import com.example.backend.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    // Add custom repository methods for beneficiary-related operations if needed
}