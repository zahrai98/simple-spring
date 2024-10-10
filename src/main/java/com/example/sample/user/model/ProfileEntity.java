package com.example.sample.user.model;

import com.example.sample.address.model.AddressEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "profile")
public class ProfileEntity {
    @Id
    private Long id;
    @Column(name = "bio")
    private String bio;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private UserEntity user;
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AddressEntity> address;

}

