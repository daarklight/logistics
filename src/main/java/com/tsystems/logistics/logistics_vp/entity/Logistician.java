package com.tsystems.logistics.logistics_vp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "logisticians", schema = "logistics")
@SQLDelete(sql = "UPDATE logistics.logisticians SET deleted = true WHERE personal_number=?")
@Where(clause = "deleted=false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Logistician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personal_number")
    private int personalNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "logistician_authentication_id", referencedColumnName = "id")
    private AuthenticationInfo logisticianAuthenticationId;

    @Column(name = "name", length = 35, nullable = false)
    private String name;

    @Column(name = "surname", length = 35, nullable = false)
    private String surname;
}
