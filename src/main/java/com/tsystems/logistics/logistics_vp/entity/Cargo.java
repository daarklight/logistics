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
    //CREATE TABLE logistics.cargos (
    //cargo_id INT NOT NULL AUTO_INCREMENT,
    //order_for_cargo_id INT,
    //cargo_name VARCHAR(40) NOT NULL,
    //weight DECIMAL(3,1) NOT NULL,
    //start_city VARCHAR(30) NOT NULL,
    //start_state VARCHAR(49) NOT NULL,
    //start_address VARCHAR(50) NOT NULL,
    //loaded ENUM('YES','NO') DEFAULT 'NO',
    //final_city VARCHAR(30) NOT NULL,
    //final_state VARCHAR(49) NOT NULL,
    //final_address VARCHAR(50) NOT NULL,
    //unloaded ENUM('YES','NO') DEFAULT 'NO',
    //expected_completion_date_time DATETIME,
    //real_completion_date_time DATETIME,
    //deleted BIT(1) NOT NULL DEFAULT 0,
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

    @Column(name = "expected_completion_date_time")
    private LocalDateTime expectedCompletionDateTime;

    @Column(name = "real_completion_date_time")
    private LocalDateTime realCompletionDateTime;
}
