package com.example.doctorsDataCirclesHealth.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DoctorsTable")
public class DoctorsEntity {

    @Id
    private long id;
    private String name;
    private String email;
    private String hospital;
    private String address;
    private String speciality;

}
