package com.example.doctorsDataCirclesHealth.Service;

import com.example.doctorsDataCirclesHealth.Entity.FacilityEntity;
import com.example.doctorsDataCirclesHealth.Repo.FacilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacilityService {
    @Autowired
    private FacilityRepo hospitalRepo;

    public ResponseEntity<FacilityEntity> saveHospital(FacilityEntity hospitalEntity){
        FacilityEntity saveHospital = hospitalRepo.save(hospitalEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveHospital);
    }
    public List<String> getAllHospitals() {
        return hospitalRepo.findAll()
                .stream()
                .map(FacilityEntity::getHospitalName)
                .collect(Collectors.toList());
    }
    public Optional<FacilityEntity> getHospitalByName(String hospitalName) {
        return hospitalRepo.findByhospitalName(hospitalName);
    }
}
