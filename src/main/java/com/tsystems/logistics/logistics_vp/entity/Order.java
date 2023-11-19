package com.tsystems.logistics.logistics_vp.entity;

import com.tsystems.logistics.logistics_vp.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders", schema = "logistics")
@SQLDelete(sql = "UPDATE logistics.orders SET deleted = true WHERE order_id=?")
@Where(clause = "deleted=false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    //CREATE TABLE logistics.orders (
    //order_id INT NOT NULL AUTO_INCREMENT,
    //order_customer_id INT,
    //category VARCHAR(40) NOT NULL,
    //weight DECIMAL(3,1) NOT NULL,
    //status ENUM('NEW','EXPECT_DRIVERS_CONFIRMATION','CONFIRMED','DECLINED_BY_DRIVERS','ON_ROAD','COMPLETED') DEFAULT 'NEW',
    //start_date_time DATETIME,
    //limit_date_time DATETIME NOT NULL,
    //assigned_truck_number VARCHAR(7),
    //driver_comment VARCHAR(120),
    //deleted BIT(1) NOT NULL DEFAULT 0,
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
    @Column(name = "status", columnDefinition =
            "ENUM('NEW','EXPECT_DRIVERS_CONFIRMATION','CONFIRMED','DECLINED_BY_DRIVERS','ON_ROAD','COMPLETED') DEFAULT 'NEW'")
    private OrderStatus status = OrderStatus.NEW;

    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;

    @Column(name = "limit_date_time", nullable = false)
    private LocalDateTime limitDateTime;

    @Column(name = "assigned_truck_number", length = 7)
    private String assignedTruckNumber;

    @Column(name = "driver_comment", length = 120)
    private String driverComment;

    // Do we need fetch = FetchType.LAZY ???
    @OneToMany(mappedBy = "currentOrderId")
    private List<Driver> drivers;

    // Do we need fetch = FetchType.LAZY ???
    @OneToMany(mappedBy = "orderForCargoId")
    private List<Cargo> cargos;
}
