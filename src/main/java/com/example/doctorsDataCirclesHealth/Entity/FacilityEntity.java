package com.example.doctorsDataCirclesHealth.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Facility")
public class FacilityEntity {
    @Id
    private long id;
    private String hospitalName;
    private String facilityName;
    private String physicalAddress;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String mainContactName;
    private String phoneNumber;
    private String emailid;
    private String adminUserid;
    private byte[] facilityImage;

}
