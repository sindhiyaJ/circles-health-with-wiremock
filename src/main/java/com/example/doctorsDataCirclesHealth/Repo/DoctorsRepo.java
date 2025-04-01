package com.example.doctorsDataCirclesHealth.Repo;

import com.example.doctorsDataCirclesHealth.Entity.DoctorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface DoctorsRepo extends JpaRepository<DoctorsEntity,Long> {
    Optional<DoctorsEntity> findByEmail(String email);
}
