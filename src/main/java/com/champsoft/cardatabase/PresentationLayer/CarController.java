package com.champsoft.cardatabase.PresentationLayer;

import com.champsoft.cardatabase.BusinessLogicLayer.CarService;
import com.champsoft.cardatabase.DataAccessLayer.Car;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CarController {
    private final CarService carService;
    public CarController(CarService carService) {
        this.carService = carService;
    }
    // Simple GET endpoint
    @GetMapping("/cars")
    public ResponseEntity<List<CarResponseModel>>  getCars() {
        List<CarResponseModel> cars = this.carService.getCars(  );
        return  ResponseEntity.ok(cars);
    }
    @GetMapping("/cars/{id}")
    public ResponseEntity<CarResponseModel> getCarById(@PathVariable String id) {
        return  ResponseEntity.ok(this.carService.getCarById( id  ));
    }
    @PostMapping("/cars")
    public ResponseEntity<CarResponseModel> createCar(@RequestBody CarRequestModel carData) {
        CarResponseModel savedCar = this.carService.createCar( carData);
        return ResponseEntity.ok(savedCar);
    }
    @PutMapping("/cars/{id}")
    public ResponseEntity<CarResponseModel> updateCar(
            @PathVariable String id,
            @RequestBody CarRequestModel carData) {
        CarResponseModel updated = carService.updateCar(id, carData);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable String id) {
        this.carService.deleteCar( id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }



}