package com.example.doctorsDataCirclesHealth.Controller;

import com.example.doctorsDataCirclesHealth.Entity.DoctorsEntity;
import com.example.doctorsDataCirclesHealth.Service.DoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DoctorsController {

    @Autowired
    private DoctorsService doctorsService;

    @PostMapping(path = "/save/doctors", consumes = "application/json")
    public ResponseEntity<DoctorsEntity> saveDoctor(@RequestBody DoctorsEntity doctorsEntity) {
        return doctorsService.saveDoctor(doctorsEntity);
    }

    @GetMapping("/")
    public ResponseEntity<List<DoctorsEntity>> getAllDoctors() {
        return ResponseEntity.ok(doctorsService.getAllDoctors());
    }
    @GetMapping("/get/doctor/{id}")
    public ResponseEntity<Optional<DoctorsEntity>> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorsService.getDoctorById(id));
    }
    @PutMapping("/update/doctor/{id}")
    public ResponseEntity<String> updateDoctor(@PathVariable Long id, @RequestBody DoctorsEntity updatedDoctor) {
        return doctorsService.updateDoctor(id, updatedDoctor);
    }
}
