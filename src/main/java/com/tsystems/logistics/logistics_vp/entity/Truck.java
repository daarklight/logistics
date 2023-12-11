package com.tsystems.logistics.logistics_vp.entity;

import com.tsystems.logistics.logistics_vp.enums.Busy;
import com.tsystems.logistics.logistics_vp.enums.TechnicalCondition;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Table(name = "trucks", schema = "logistics")
@SQLDelete(sql = "UPDATE logistics.trucks SET deleted = true WHERE number=?")
@Where(clause = "deleted=false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Truck {
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
    @Column(name = "technical_condition", columnDefinition = "ENUM('OK','NOK') DEFAULT 'OK'")
    private TechnicalCondition technicalCondition = TechnicalCondition.OK;

    @Enumerated(EnumType.STRING)
    @Column(name = "busy", columnDefinition = "ENUM('YES','NO') DEFAULT 'NO'")
    private Busy busy = Busy.NO;

    @Column(name = "current_city", length = 30, nullable = false)
    private String currentCity;

    @Column(name = "current_state", length = 49, nullable = false)
    private String currentState;

    @OneToMany(mappedBy = "currentTruckNumber")
    private List<Driver> drivers;
}
