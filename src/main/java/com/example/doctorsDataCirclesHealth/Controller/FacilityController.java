package com.example.doctorsDataCirclesHealth.Controller;

import com.example.doctorsDataCirclesHealth.Entity.FacilityEntity;
import com.example.doctorsDataCirclesHealth.Service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FacilityController {
    @Autowired
    private FacilityService hospitalService;

    @PostMapping(path = "/save/hospitals", consumes = "application/json")
    public ResponseEntity<FacilityEntity> saveHospital(@RequestBody FacilityEntity hospitalEntity) {
        return hospitalService.saveHospital(hospitalEntity);
    }

    @GetMapping("/hospitalsList")
    public ResponseEntity<List<String>> getAllHospitals() {
        List<String> hospitalNames = hospitalService.getAllHospitals();
        return ResponseEntity.ok(hospitalNames);
    }

    @GetMapping("/get/hospital/{name}")
    public ResponseEntity<FacilityEntity> getHospitalByName(@PathVariable String hospitalName) {
        Optional<FacilityEntity> hospital = hospitalService.getHospitalByName(hospitalName);
        return hospital.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }
}
