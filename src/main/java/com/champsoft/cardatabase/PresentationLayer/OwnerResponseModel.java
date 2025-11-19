package com.champsoft.cardatabase.PresentationLayer;


import com.champsoft.cardatabase.DataAccessLayer.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OwnerResponseModel {
    private Long id;
    private String firstname, lastname, email, phone;


}
