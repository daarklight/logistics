package com.tsystems.logistics.logistics_vp.entity;

import com.tsystems.logistics.logistics_vp.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "authentication_info", schema = "logistics")
@SQLDelete(sql = "UPDATE logistics.authentication_info SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationInfo {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "username", length = 30, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 40, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "ENUM('ROLE_ADMIN','ROLE_LOGISTICIAN','ROLE_DRIVER','ROLE_CUSTOMER') DEFAULT 'ROLE_LOGISTICIAN'")
    private Role role;

    @OneToOne(mappedBy = "customerAuthenticationId")
    private Customer customer;

    @OneToOne(mappedBy = "driverAuthenticationId")
    private Driver driver;

    @OneToOne(mappedBy = "logisticianAuthenticationId")
    private Logistician logistician;
}
