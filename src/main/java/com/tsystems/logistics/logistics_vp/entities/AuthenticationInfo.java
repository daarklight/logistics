package com.tsystems.logistics.logistics_vp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "authentication_info", schema = "logistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationInfo {
    //CREATE TABLE logistics.authentication_info (
    //id INT,
    //login VARCHAR(40) NOT NULL,
    //password VARCHAR(40) NOT NULL,
    //PRIMARY KEY id_pk (id),
    //UNIQUE KEY login_uk (login)
    //);

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "login", length = 40, nullable = false, unique = true)
    private String login;

    @Column(name = "password", length = 40, nullable = false)
    private String password;

    @OneToOne(mappedBy = "customerAuthenticationId")
    private Customer customer;

    @OneToOne(mappedBy = "driverAuthenticationId")
    private Driver driver;

    @OneToOne(mappedBy = "logisticianAuthenticationId")
    private Logistician logistician;
}
