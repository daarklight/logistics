package com.tsystems.logistics.logistics_vp.entities;

import com.tsystems.logistics.logistics_vp.enums.Busy;
import com.tsystems.logistics.logistics_vp.enums.TechnicalCondition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "trucks", schema = "logistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Truck {
    //CREATE TABLE logistics.trucks (
    //number VARCHAR(7) NOT NULL,
    //model VARCHAR(30) NOT NULL,
    //capacity DECIMAL(3,1) NOT NULL,
    //total_operating_time INT,
    //technical_condition ENUM('ok','nok') DEFAULT 'ok',
    //busy ENUM('yes','no') DEFAULT 'no',
    //current_city_and_state VARCHAR(50) NOT NULL,
    //PRIMARY KEY number_pk (number)
    //);

    @Id
    @Column(name = "number", length = 7, nullable = false)
    private String number;

    @Column(name = "model", length = 30, nullable = false)
    private String model;

    @Column(name = "capacity", nullable = false)
    private double capacity;

    @Column(name = "total_operating_time")
    private int totalOperatingTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "technical_condition", columnDefinition = "ENUM('ok','nok') DEFAULT 'ok'")
    private TechnicalCondition technicalCondition;

    @Enumerated(EnumType.STRING)
    @Column(name = "busy", columnDefinition = "ENUM('yes','no') DEFAULT 'no'")
    private Busy busy;

    @Column(name = "current_city_and_state", columnDefinition = "ENUM('yes','no') DEFAULT 'no'", length = 50, nullable = false)
    private String currentCityAndState;

    // Do we need fetch = FetchType.LAZY ???
    @OneToMany(mappedBy = "currentTruckNumber")
    private List<Driver> drivers;
}
