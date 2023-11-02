package com.tsystems.logistics.logistics_vp.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "logisticians", schema = "logistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Logistician {
    //CREATE TABLE logistics.logisticians (
    //personal_number INT NOT NULL AUTO_INCREMENT,
    //logistician_authentication_id INT NOT NULL UNIQUE,
    //name VARCHAR(35) NOT NULL,
    //surname VARCHAR(35) NOT NULL,
    //PRIMARY KEY personal_number_pk (personal_number),
    //FOREIGN KEY authentication_logistician_fk (logistician_authentication_id) REFERENCES logistics.authentication_info(id)
    //)
    //AUTO_INCREMENT = 1201;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personal_number")
    private int personalNumber;

//    @Column(name = "logistician_authentication_id")
//    private int logisticianAuthenticationId;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "logistician_authentication_id", referencedColumnName = "id")
    private AuthenticationInfo logisticianAuthenticationId;

    @Column(name = "name", length = 35, nullable = false)
    private String name;

    @Column(name = "surname", length = 35, nullable = false)
    private String surname;
}
