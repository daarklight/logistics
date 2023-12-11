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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "order_customer_id", nullable = false)
    private int orderCustomerId;

    @Column(name = "cargos_number")
    private int numberOfCargos;

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

    @Column(name = "drivers_number")
    private int numberOfAssignedDrivers;

    @OneToMany(mappedBy = "currentOrderId")
    private List<Driver> drivers;

    @OneToMany(mappedBy = "orderForCargoId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cargo> cargos;
}
