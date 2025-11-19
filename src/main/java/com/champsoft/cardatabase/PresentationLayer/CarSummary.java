package com.champsoft.cardatabase.PresentationLayer;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarSummary {

    //ong id, String make, String model, Integer year
    private Long id;

    String model;
    private String brand, color;
    private int modelYear;

}
