package com.champsoft.cardatabase.PresentationLayer;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarResponseModel {
    private Long id;
    String model;
    private String brand, color,
            registrationNumber;
    private int modelYear;
    private double price;


    private String vin;
    OwnerSummary owner;
}
