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
    //start_city VARCHAR(30) NOT NULL,
    //start_state VARCHAR(30) NOT NULL,
    //start_address VARCHAR(50) NOT NULL,
    //loaded ENUM('YES','NO') DEFAULT 'NO',
    //final_city VARCHAR(30) NOT NULL,
    //final_state VARCHAR(30) NOT NULL,
    //final_address VARCHAR(50) NOT NULL,
    //unloaded ENUM('YES','NO') DEFAULT 'NO',
    //expected_completion_date_time DATETIME,
    //real_completion_date_time DATETIME,
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

    @Column(name = "start_city", length = 30, nullable = false)
    private String startCity;

    @Column(name = "start_state", length = 30, nullable = false)
    private String startState;

    @Column(name = "start_address", length = 50, nullable = false)
    private String startAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "loaded", columnDefinition = "ENUM('YES','NO') DEFAULT 'NO'")
    private Loaded loaded;

    @Column(name = "final_city", length = 30, nullable = false)
    private String finalCity;

    @Column(name = "final_state", length = 30, nullable = false)
    private String finalState;

    @Column(name = "final_address", length = 50, nullable = false)
    private String finalAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "unloaded", columnDefinition = "ENUM('YES','NO') DEFAULT 'NO'")
    private Unloaded unloaded;

    @Column(name = "expected_completion_date_time")
    private LocalDateTime expectedCompletionDateTime;

    @Column(name = "real_completion_date_time")
    private LocalDateTime realCompletionDateTime;
}
