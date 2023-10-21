package com.tsystems.logistics.logistics_vp.entities;

import com.tsystems.logistics.logistics_vp.enums.Loaded;
import com.tsystems.logistics.logistics_vp.enums.Unloaded;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "cargos", schema = "logistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cargo {
    //CREATE TABLE logistics.cargos (
    //cargo_id INT NOT NULL AUTO_INCREMENT,
    //order_for_cargo_id INT,
    //cargo_name VARCHAR(40) NOT NULL,
    //weight DECIMAL(3,1) NOT NULL,
    //start_city_and_state VARCHAR(50) NOT NULL,
    //start_address VARCHAR(50) NOT NULL,
    //loaded SET('yes','no') DEFAULT 'no',
    //final_city_and_state VARCHAR(50) NOT NULL,
    //final_address VARCHAR(50) NOT NULL,
    //unloaded SET('yes','no') DEFAULT 'no',
    //expected_completion_date_and_time DATETIME,
    //real_completion_date_and_time DATETIME,
    //PRIMARY KEY cargo_id_pk (cargo_id),
    //FOREIGN KEY order_id_fk (order_for_cargo_id) REFERENCES logistics.orders(order_id)
    //)
    //AUTO_INCREMENT = 1001;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cargo_id")
    private int cargoId;

//    @Column(name = "order_for_cargo_id")
//    private int orderForCargoId;
    // Do we need in @ManyToOne cascade = CascadeType.REFRESH, fetch = FetchType.EAGER ???
    @ManyToOne
    @JoinColumn(name = "order_for_cargo_id", referencedColumnName = "order_id")
    private Order orderForCargoId;

    @Column(name = "cargo_name", length = 40, nullable = false)
    private String cargoName;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "start_city_and_state", length = 50, nullable = false)
    private String startCityAndState;

    @Column(name = "start_address", length = 50, nullable = false)
    private String startAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "loaded", columnDefinition = "ENUM('yes','no') DEFAULT 'no'")
    private Loaded loaded;

    @Column(name = "final_city_and_state", length = 50, nullable = false)
    private String finalCityAndState;

    @Column(name = "final_address", length = 50, nullable = false)
    private String finalAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "unloaded", columnDefinition = "ENUM('yes','no') DEFAULT 'no'")
    private Unloaded unloaded;

    @Column(name = "expected_completion_date_and_time")
    private LocalDateTime expectedCompletionDateAndTime;

    @Column(name = "real_completion_date_and_time")
    private LocalDateTime realCompletionDateAndTime;
}
