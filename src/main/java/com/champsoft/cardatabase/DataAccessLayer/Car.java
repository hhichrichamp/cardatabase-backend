package com.champsoft.cardatabase.DataAccessLayer;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.GeneratedReferenceTypeDelegate;
import org.springframework.data.jpa.util.JpaMetamodel;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "car_model")
    String model;
    private String brand, color,
            registrationNumber;
    private int modelYear;
    private double price;
    private String vin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_ownerid")
//    @JsonBackReference // This is the "back" reference
//    @JsonIgnore // When a Car is serialized, DO NOT include the owner's full object
    private Owner owner;

//@JsonIgnoreProperties({"cars"})  // prevent looping


    public Car(String model, String brand, String color, String registrationNumber,
               int modelYear, double price, String vin, Owner owner) {
        this.model = model;
        this.brand = brand;
        this.color = color;
        this.registrationNumber = registrationNumber;
        this.modelYear = modelYear;
        this.price = price;
        this.vin = vin;
        this.owner = owner;
    }
}
