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
    //CREATE TABLE logistics.authentication_info (
    //id INT,
    //username VARCHAR(30) NOT NULL,
    //password VARCHAR(90) NOT NULL,
    //role ENUM('ADMIN','LOGISTICIAN','DRIVER','CUSTOMER') DEFAULT 'LOGISTICIAN' NOT NULL,
    //deleted BIT(1) NOT NULL DEFAULT 0,
    //PRIMARY KEY id_pk (id),
    //UNIQUE KEY username_uk (username)
    //);

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "username", length = 30, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 40, nullable = false)
    private String password;


    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "ENUM('ADMIN','LOGISTICIAN','DRIVER','CUSTOMER') DEFAULT 'LOGISTICIAN'")
    private Role role;

    @OneToOne(mappedBy = "customerAuthenticationId")
    private Customer customer;

    @OneToOne(mappedBy = "driverAuthenticationId")
    private Driver driver;

    @OneToOne(mappedBy = "logisticianAuthenticationId")
    private Logistician logistician;
}
