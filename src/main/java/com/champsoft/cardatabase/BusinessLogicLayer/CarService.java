package com.champsoft.cardatabase.BusinessLogicLayer;

import com.champsoft.cardatabase.DataAccessLayer.Car;
import com.champsoft.cardatabase.DataAccessLayer.CarRepository;
import com.champsoft.cardatabase.MappersLayer.CarMapper;
import com.champsoft.cardatabase.PresentationLayer.CarRequestModel;
import com.champsoft.cardatabase.PresentationLayer.CarResponseModel;
import com.champsoft.cardatabase.utilities.CarNotFoundException;
import com.champsoft.cardatabase.utilities.InvalidCarDataException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.aspectj.apache.bcel.classfile.InnerClass;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper) {

        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }


    public List<CarResponseModel> getCars() {
        List<Car> cars = this.carRepository.findAll();
        // map the list of cars to list of CarResponseModels
        List<CarResponseModel> carResponseModels = new ArrayList<>();
        for (Car car : cars){

            carResponseModels.add(  this.carMapper.toResponse(car)   );

        }
        return carResponseModels;
    }

    public CarResponseModel getCarById(String id) {
        long idLong;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid ID format: " + id
            );
        }
        Car existingCar = carRepository.findById(idLong)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Car with id: " + idLong + " not found."
                        )
                );
        return this.carMapper.toResponse(existingCar);
    }

//    public CarResponseModel createCar(CarRequestModel carData) {
//        int modelYear =  carData.getYear()  ;
//        // validate data
//        if (modelYear<1900 || modelYear>LocalDate.now().getYear() )
//            throw new InvalidCarDataException("Model Year out of range.");
//        double price =  carData.getPrice() ;
//        if (price <0)
//            throw new InvalidCarDataException("Price cannot be negative.");
//        // convert the CarRequestModel object into a Car object
//        Car newCar = this.carMapper.fromRequestModelToCarEntity(carData);
//        // save teh Car object into the repository
//        Car SavedCar = this.carRepository.save(newCar);
//        // Convert the saved Car object into a CarResponseModel object,
//        // because that is what we have to return
//        CarResponseModel carRequestModel = this.carMapper.toResponse(SavedCar);
//        return carRequestModel;
//    }

    public CarResponseModel createCar(CarRequestModel carData) {
        int currentYear = LocalDate.now().getYear();

        if (carData.getYear() < 1900 || carData.getYear() > currentYear)
            throw new InvalidCarDataException("Model year out of range.");
        if (carData.getPrice() < 0)
            throw new InvalidCarDataException("Price cannot be negative.");
        Car car = carMapper.fromRequestModelToCarEntity(carData);
        Car savedCar = carRepository.save(car);
        return carMapper.toResponse(savedCar);
    }


//    public CarResponseModel updateCar(String id, CarRequestModel carData) {
//        long idLong = Long.parseLong(id);
//        Optional<Car> car = this.carRepository.findById(idLong);
//        if (car.isEmpty())
//            throw new CarNotFoundException("Car with id: " + idLong + " not found.");
//        Car newCar = this.carMapper.fromRequestModelToCarEntity(carData);
//        /////////// IMPORTANT ///////////////////////
//        // make sure you set the id to the same id (idLong), Otherwise the database will generate another new id
//        newCar.setId(   idLong  );
//        ////////////////////////////////////////////
//        // save the new updated car
//        Car updatedCar = this.carRepository.save(newCar);
//        return carMapper.toResponse(updatedCar);
//    }

    public CarResponseModel updateCar(String id, CarRequestModel carData) {
        long idLong ;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid ID format: " + id
            );
        }
        Car existingCar = carRepository.findById(idLong)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Car with id: " + idLong + " not found."
                        )
                );
        Car updatedCar = carMapper.fromRequestModelToCarEntity(carData);
        updatedCar.setId(idLong);
        return carMapper.toResponse(this.carRepository.save(updatedCar));
    }

//    public CarResponseModel updateCar(String id, CarRequestModel carData) {
//        long idLong = Long.parseLong(id);
//        Car existingCar = carRepository.findById(idLong)
//                .orElseThrow(() -> new CarNotFoundException("Car with id: " + idLong + " not found."));
//        Car updatedCar = carMapper.fromRequestModelToCarEntity(carData);
//        updatedCar.setId(idLong);
//        return carMapper.toResponse(carRepository.save(updatedCar));
//    }

    public void deleteCar(String id) {
        long idLong;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid ID format: " + id
            );
        }
        Car existingCar = carRepository.findById(idLong)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Car with id: " + idLong + " not found."
                        )
                );
        this.carRepository.delete(existingCar);
    }
}
