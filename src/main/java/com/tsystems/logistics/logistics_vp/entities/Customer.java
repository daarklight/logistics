package com.tsystems.logistics.logistics_vp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers", schema = "logistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    //CREATE TABLE logistics.customers (
    //customer_id INT NOT NULL AUTO_INCREMENT,
    //customer_authentication_id INT NOT NULL UNIQUE,
    //customer_name VARCHAR(50) NOT NULL,
    //phone VARCHAR(25) NOT NULL,
    //email VARCHAR(50) NOT NULL,
    //PRIMARY KEY customer_id_pk (customer_id),
    //FOREIGN KEY authentication_customer_fk (customer_authentication_id) REFERENCES logistics.authentication_info(id)
    //)
    //AUTO_INCREMENT = 5001;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

//    @Column(name = "customer_authentication_id")
//    private int customerAuthenticationId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "customer_authentication_id", referencedColumnName = "id")
    private AuthenticationInfo customerAuthenticationId;

    @Column(name = "customer_name", length = 50, nullable = false)
    private String customerName;

    @Column(name = "phone", length = 25, nullable = false, unique = true)
    private String phone;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;
}
