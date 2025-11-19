package com.champsoft.cardatabase.MappersLayer;

import com.champsoft.cardatabase.DataAccessLayer.Owner;
import com.champsoft.cardatabase.PresentationLayer.CarRequestModel;
import com.champsoft.cardatabase.PresentationLayer.CarResponseModel;
import com.champsoft.cardatabase.PresentationLayer.OwnerSummary;
import org.springframework.stereotype.Component;
import com.champsoft.cardatabase.DataAccessLayer.Car;

@Component
public class CarMapper {
    // thos method maps a car object into a CarResposnseModel DTO
    public CarResponseModel toResponse(Car car) {
        Owner owner = car.getOwner();
        OwnerSummary ownerSummary;
        if ( owner == null )
            ownerSummary = null;
        else
            ownerSummary = new OwnerSummary(owner.getOwnerid(), owner.getFirstname(), owner.getLastname());

        return new CarResponseModel(car.getId(), car.getModel(), car.getBrand(), car.getColor(),
                car.getRegistrationNumber(), car.getModelYear(),
                car.getPrice(), car.getVin(), ownerSummary);
    }
    public Car fromRequestModelToCarEntity (CarRequestModel carData) {

        // transfer the data from the Map to a Car entity/object
        Car newCar = new Car();
        newCar.setBrand(  carData.getMake() );
        newCar.setModel(  carData.getModel()  );
        newCar.setColor(  carData.getModel() );
        newCar.setRegistrationNumber(  carData.getRegistrationNumber() );
        newCar.setModelYear(  carData.getYear() );
        newCar.setPrice( carData.getPrice() );
        newCar.setVin( carData.getVin() );
        Owner owner = new Owner();
        owner.setOwnerid( carData.getOwnerId()  );
        newCar.setOwner( owner );
        return newCar;

    }


}
