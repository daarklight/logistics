package com.tsystems.logistics.logistics_vp.entity;

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
    //login VARCHAR(30) NOT NULL,
    //password VARCHAR(40) NOT NULL,
    //deleted BIT(1) NOT NULL DEFAULT 0,
    //PRIMARY KEY id_pk (id),
    //UNIQUE KEY login_uk (login)
    //);

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "login", length = 30, nullable = false, unique = true)
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
