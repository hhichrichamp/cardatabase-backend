package com.champsoft.cardatabase.DataAccessLayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ownerid;
    private String firstname, lastname, email, phone, username, password;


//    @OneToMany(cascade = CascadeType.ALL , mappedBy = "owner")
//    private List<Car> cars;


    public Owner(String firstname, String lastname, String email,
                 String phone, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }
}
