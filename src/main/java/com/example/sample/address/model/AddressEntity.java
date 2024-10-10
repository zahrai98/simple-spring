package com.example.sample.address.model;

import com.example.sample.user.model.ProfileEntity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "address")
public class AddressEntity {
    @Id
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    private Long id;
    @Column(name = "province")
    private String province;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "plate")
    private String plate;
    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private ProfileEntity profile;
    @Column(name = "profile_id", insertable = false, updatable = false)
    private Long profileId;
}
