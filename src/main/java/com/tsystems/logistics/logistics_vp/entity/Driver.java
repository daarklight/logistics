package com.tsystems.logistics.logistics_vp.entity;

import com.tsystems.logistics.logistics_vp.enums.Busy;
import com.tsystems.logistics.logistics_vp.enums.DriverStatus;
import com.tsystems.logistics.logistics_vp.enums.OrderAcceptance;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

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

    @Column(name = "driver_comment", length = 120)
    private String driverComment;

    @Column(name = "start_shift_date_time")
    private LocalDateTime startShiftDateTime;

    @Column(name = "end_shift_date_time")
    private LocalDateTime endShiftDateTime;
}
