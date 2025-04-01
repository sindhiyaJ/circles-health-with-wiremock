package com.example.doctorsDataCirclesHealth.Service;

import com.example.doctorsDataCirclesHealth.Entity.DoctorsEntity;
import com.example.doctorsDataCirclesHealth.Repo.DoctorsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorsService {

    @Autowired
    private DoctorsRepo doctorsRepo;

    public ResponseEntity<DoctorsEntity> saveDoctor(DoctorsEntity doctorsEntity) {
        Optional<DoctorsEntity> existingDoctor = doctorsRepo.findByEmail(doctorsEntity.getEmail());

        if (existingDoctor.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(existingDoctor.get());
        }

        DoctorsEntity savedDoctor = doctorsRepo.save(doctorsEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor);
    }

    public List<DoctorsEntity> getAllDoctors() {
        return new ArrayList<>(doctorsRepo.findAll());
    }
   public Optional<DoctorsEntity> getDoctorById(Long id) {
        return doctorsRepo.findById(id);
    }
    public ResponseEntity<String> updateDoctor(Long id, DoctorsEntity updatedDoctor) {
        Optional<DoctorsEntity> existingDoctor = doctorsRepo.findById(id);

        if (existingDoctor.isPresent()) {
            DoctorsEntity doctor = existingDoctor.get();
            doctor.setName(updatedDoctor.getName());
            doctor.setEmail(updatedDoctor.getEmail());
            doctor.setHospital(updatedDoctor.getHospital());
            doctor.setAddress(updatedDoctor.getAddress());
            doctor.setSpeciality(updatedDoctor.getSpeciality());
            doctorsRepo.save(doctor);
            return ResponseEntity.ok("Doctor updated successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }
    }
}
