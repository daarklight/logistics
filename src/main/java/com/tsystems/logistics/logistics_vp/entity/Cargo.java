package com.tsystems.logistics.logistics_vp.entity;

import com.tsystems.logistics.logistics_vp.enums.Loaded;
import com.tsystems.logistics.logistics_vp.enums.Unloaded;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table(name = "cargos", schema = "logistics")
@SQLDelete(sql = "UPDATE logistics.cargos SET deleted = true WHERE cargo_id=?")
@Where(clause = "deleted=false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cargo_id")
    private int cargoId;

    @ManyToOne
    @JoinColumn(name = "order_for_cargo_id", referencedColumnName = "order_id")
    private Order orderForCargoId;

    @Column(name = "cargo_name", length = 40, nullable = false)
    private String cargoName;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "start_city", length = 30, nullable = false)
    private String startCity;

    @Column(name = "start_state", length = 49, nullable = false)
    private String startState;

    @Column(name = "start_address", length = 50, nullable = false)
    private String startAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "loaded", columnDefinition = "ENUM('YES','NO') DEFAULT 'NO'")
    private Loaded loaded = Loaded.NO;

    @Column(name = "final_city", length = 30, nullable = false)
    private String finalCity;

    @Column(name = "final_state", length = 49, nullable = false)
    private String finalState;

    @Column(name = "final_address", length = 50, nullable = false)
    private String finalAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "unloaded", columnDefinition = "ENUM('YES','NO') DEFAULT 'NO'")
    private Unloaded unloaded = Unloaded.NO;

    @Column(name = "waypoint_index")
    private int waypointIndex;

    @Column(name = "ride_distance_from_start_point")
    private int rideDistanceFromStartPoint;

    @Column(name = "ride_duration_from_start_point")
    private int rideDurationFromStartPoint;

    @Column(name = "ride_distance_from_previous_point")
    private int rideDistanceFromPreviousPoint;

    @Column(name = "ride_duration_from_previous_point")
    private int rideDurationFromPreviousPoint;

    @Column(name = "expected_completion_date_time")
    private LocalDateTime expectedCompletionDateTime;

    @Column(name = "real_completion_date_time")
    private LocalDateTime realCompletionDateTime;
}
