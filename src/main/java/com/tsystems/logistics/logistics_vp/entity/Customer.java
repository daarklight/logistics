package com.tsystems.logistics.logistics_vp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "customers", schema = "logistics")
@SQLDelete(sql = "UPDATE logistics.customers SET deleted = true WHERE customer_id=?")
@Where(clause = "deleted=false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    //CREATE TABLE logistics.customers (
    //customer_id INT NOT NULL AUTO_INCREMENT,
    //customer_authentication_id INT NOT NULL UNIQUE,
    //customer_name VARCHAR(50) NOT NULL,
    //phone VARCHAR(19) NOT NULL,
    //email VARCHAR(70) NOT NULL,
    //deleted BIT(1) NOT NULL DEFAULT 0,
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

    @Column(name = "phone", length = 19, nullable = false, unique = true)
    private String phone;

    @Email(message = "Email format is incorrect. Please input correct email")
    @Column(name = "email", length = 70, nullable = false, unique = true)
    private String email;
}
