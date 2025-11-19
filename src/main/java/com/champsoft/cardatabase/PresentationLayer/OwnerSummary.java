package com.champsoft.cardatabase.PresentationLayer;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OwnerSummary {
    //Long id, String firstName, String lastName
    private Long ownerid;
    private String firstname, lastname;

}
