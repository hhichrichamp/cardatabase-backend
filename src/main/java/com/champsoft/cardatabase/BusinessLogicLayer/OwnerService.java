package com.champsoft.cardatabase.BusinessLogicLayer;

import com.champsoft.cardatabase.DataAccessLayer.Car;
import com.champsoft.cardatabase.DataAccessLayer.CarRepository;
import com.champsoft.cardatabase.DataAccessLayer.Owner;
import com.champsoft.cardatabase.DataAccessLayer.OwnerRepository;
import com.champsoft.cardatabase.MappersLayer.CarMapper;
import com.champsoft.cardatabase.MappersLayer.OwnerMapper;;
import com.champsoft.cardatabase.PresentationLayer.CarResponseModel;
import com.champsoft.cardatabase.PresentationLayer.OwnerRequestModel;
import com.champsoft.cardatabase.PresentationLayer.OwnerResponseModel;
import com.champsoft.cardatabase.utilities.CarNotFoundException;
import com.champsoft.cardatabase.utilities.OwnerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final CarRepository carRepository;
    private final OwnerMapper ownerMapper;
    private final CarMapper carMapper;
    public OwnerService(OwnerRepository ownerRepository, CarRepository carRepository, OwnerMapper ownerMapper, CarMapper carMapper) {
        this.ownerRepository = ownerRepository;
        this.carRepository = carRepository;
        this.ownerMapper = ownerMapper;
        this.carMapper = carMapper;
    }


    public List<OwnerResponseModel> getAllOwners() {
        List<Owner> owners = this.ownerRepository.findAll();
        // map the list of cars to list of CarResponseModels
        List<OwnerResponseModel> ownerResponseModels = new ArrayList<>();
        for (Owner owner : owners){
            ownerResponseModels.add(  this.ownerMapper.fromEntityToResponeModel(owner)   );
        }
        return ownerResponseModels;
    }

    public OwnerResponseModel getOwnerByIdNoCars(String id) {
        long longId = Long.parseLong(id);
        Owner existingOwner = this.ownerRepository.findById(longId)
                .orElseThrow(() -> new CarNotFoundException("Owner with id: " + longId + " not found."));
        return this.ownerMapper.fromEntityToResponeModel(existingOwner);
    }

//    public OwnerResponseModel getOwnerByIdWithCars(String id) {
//        long longId = Long.parseLong(id);
//        Owner existingOwner = this.ownerRepository.findById(longId)
//                .orElseThrow(() -> new CarNotFoundException("Owner with id: " + longId + " not found."));
//
//
//        return this.ownerMapper.fromEntityToResponeModel(existingOwner);
//    }


    public OwnerResponseModel createNewOwner(OwnerRequestModel ownerData) {
        Owner savedCar = this.ownerRepository.save(this.ownerMapper.fromRequestModelToEntity(ownerData));
        return this.ownerMapper.fromEntityToResponeModel(savedCar);
    }

    public OwnerResponseModel updateOwner(String id, OwnerRequestModel ownerData) {
        long longId = Long.parseLong(id);
        Owner existingOwner = this.ownerRepository.findById(longId)
                .orElseThrow(() -> new CarNotFoundException("Owner with id: " + longId + " not found."));
        Owner owner = this.ownerMapper.fromRequestModelToEntity(ownerData);
        owner.setOwnerid(longId);
        return this.ownerMapper.fromEntityToResponeModel(this.ownerRepository.save(owner));
    }

    public void deleteOwnerById(String id) {
        long longId = Long.parseLong(id);
        Owner existingOwner = ownerRepository.findById(longId)
                .orElseThrow(() -> new CarNotFoundException("Owner with id: " + longId + " not found."));

        // Set owner_id = null for related cars
        List<Car> cars = this.carRepository.findCarsByOwner(existingOwner);
        for (Car car : cars) {
            car.setOwner(null);
            carRepository.save(car);
        }
        ownerRepository.delete(existingOwner);
    }

    public List<CarResponseModel> getCarsOfOwnerById(String id) {
        List<CarResponseModel> carResponseModels = new ArrayList<>();
        long longId = Long.parseLong(id);
        Owner existingOwner = this.ownerRepository.findById(longId)
                .orElseThrow(() -> new CarNotFoundException("Owner with id: " + longId + " not found."));
        // search for his cars
        List<Car> cars = this.carRepository.findCarsByOwner(existingOwner);
        for (Car car : cars){
            carResponseModels.add( this.carMapper.toResponse(car)   );
        }
        return carResponseModels;
    }
}
