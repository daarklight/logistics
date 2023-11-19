package com.tsystems.logistics.logistics_vp.entity;

import com.tsystems.logistics.logistics_vp.enums.Busy;
import com.tsystems.logistics.logistics_vp.enums.DriverStatus;
import com.tsystems.logistics.logistics_vp.enums.OrderAcceptance;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "drivers", schema = "logistics")
@SQLDelete(sql = "UPDATE logistics.drivers SET deleted = true WHERE personal_number=?")
@Where(clause = "deleted=false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
    //CREATE TABLE logistics.drivers (
    //personal_number INT NOT NULL AUTO_INCREMENT,
    //driver_authentication_id INT NOT NULL UNIQUE,
    //name VARCHAR(35) NOT NULL,
    //surname VARCHAR(35) NOT NULL,
    //phone VARCHAR(19) NOT NULL,
    //email VARCHAR(70) NOT NULL,
    //work_experience INT,
    //working_hours_in_current_month INT,
    //status ENUM('REST','DRIVING') DEFAULT 'REST',
    //busy ENUM('YES','NO') DEFAULT 'NO',
    //current_city VARCHAR(30) NOT NULL,
    //current_state VARCHAR(49) NOT NULL,
    //current_truck_number VARCHAR(7),
    //current_order_id INT,
    //order_acceptance ENUM('YES','NO'),
    //deleted BIT(1) NOT NULL DEFAULT 0,
    //CONSTRAINT chk_working_hours CHECK (working_hours_in_current_month BETWEEN 0 AND 176),
    //PRIMARY KEY personal_number_pk (personal_number),
    //FOREIGN KEY authentication_driver_fk (driver_authentication_id) REFERENCES logistics.authentication_info(id),
    //FOREIGN KEY current_truck_number_fk (current_truck_number) REFERENCES logistics.trucks(number),
    //FOREIGN KEY current_order_id_fk (current_order_id) REFERENCES logistics.orders(order_id)
    //)
    //AUTO_INCREMENT = 153001;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personal_number")
    private int personalNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "driver_authentication_id", referencedColumnName = "id")
    private AuthenticationInfo driverAuthenticationId;

    @Column(name = "name", length = 35, nullable = false)
    private String name;

    @Column(name = "surname", length = 35, nullable = false)
    private String surname;

    @Column(name = "phone", length = 19, nullable = false, unique = true)
    private String phone;

    @Column(name = "email", length = 70, nullable = false, unique = true)
    private String email;

    @Column(name = "work_experience")
    private int workExperience;

    @Column(name = "working_hours_in_current_month")
    private int workingHoursInCurrentMonth;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('REST','DRIVING') DEFAULT 'REST'")
    private DriverStatus status = DriverStatus.REST;

    @Enumerated(EnumType.STRING)
    @Column(name = "busy", columnDefinition = "ENUM('YES','NO') DEFAULT 'NO'")
    private Busy busy = Busy.NO;

    @Column(name = "current_city", length = 30, nullable = false)
    private String currentCity;

    @Column(name = "current_state", length = 49, nullable = false)
    private String currentState;

    @ManyToOne
    @JoinColumn(name = "current_truck_number", referencedColumnName = "number")
    private Truck currentTruckNumber;

    @ManyToOne
    @JoinColumn(name = "current_order_id", referencedColumnName = "order_id")
    private Order currentOrderId;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_acceptance", columnDefinition = "ENUM('YES','NO')")
    private OrderAcceptance orderAcceptance;
}
