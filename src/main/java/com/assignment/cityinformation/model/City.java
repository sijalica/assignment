package com.assignment.cityinformation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "CITY")
public class City {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "country")
    private String country;
    @Column(name = "state_region")
    private String stateRegion;
    @Column(name = "population")
    private String population;
}
