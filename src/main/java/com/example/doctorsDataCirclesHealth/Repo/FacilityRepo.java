package com.example.doctorsDataCirclesHealth.Repo;

import com.example.doctorsDataCirclesHealth.Entity.FacilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacilityRepo extends JpaRepository<FacilityEntity,Long> {
    Optional<FacilityEntity> findByhospitalName(String hospitalName);
}
