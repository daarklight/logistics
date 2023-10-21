package com.tsystems.logistics.logistics_vp.entities;

import com.tsystems.logistics.logistics_vp.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders", schema = "logistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    //CREATE TABLE logistics.orders (
    //order_id INT NOT NULL AUTO_INCREMENT,
    //order_customer_id INT,
    //category VARCHAR(40) NOT NULL,
    //weight DECIMAL(3,1) NOT NULL,
    //status ENUM('new','expect driver(s) confirmation','confirmed','declined by driver(s)','on road','completed') DEFAULT 'new',
    //start_date_and_time DATETIME,
    //limit_date_and_time DATETIME NOT NULL,
    //assigned_truck_number VARCHAR(7),
    //PRIMARY KEY order_id_pk (order_id),
    //FOREIGN KEY truck_number_fk (assigned_truck_number) REFERENCES logistics.trucks(number),
    //FOREIGN KEY order_customer_id_fk (order_customer_id) REFERENCES logistics.customers(customer_id)
    //)
    //AUTO_INCREMENT = 12001;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "order_customer_id", nullable = false)
    private int orderCustomerId;

    @Column(name = "category", length = 40, nullable = false)
    private String category;

    @Column(name = "weight")
    private double weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('new','expect driver(s) confirmation','confirmed','declined by driver(s)','on road','completed') DEFAULT 'new'")
    private OrderStatus status;

    @Column(name = "start_date_and_time")
    private LocalDateTime startDateAndTime;

    @Column(name = "limit_date_and_time", nullable = false)
    private LocalDateTime limitDateAndTime;

    @Column(name = "assigned_truck_number", length = 7)
    private String assignedTruckNumber;

    // Do we need fetch = FetchType.LAZY ???
    @OneToMany(mappedBy = "currentOrderId")
    private List<Driver> drivers;

    // Do we need fetch = FetchType.LAZY ???
    @OneToMany(mappedBy = "orderForCargoId")
    private List<Cargo> cargos;
}
