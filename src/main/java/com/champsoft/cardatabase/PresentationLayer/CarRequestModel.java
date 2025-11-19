package com.champsoft.cardatabase.PresentationLayer;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarRequestModel {

    private String make;
    private String model;
    private String color;
    private Integer year;
    private String vin;
    private String  registrationNumber;
    private double price;
    private Long ownerId;
}
