package com.champsoft.cardatabase.utilities;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(String message){
        super(message);
    }
}
