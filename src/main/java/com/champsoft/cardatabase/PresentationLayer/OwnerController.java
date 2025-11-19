package com.champsoft.cardatabase.PresentationLayer;


import com.champsoft.cardatabase.BusinessLogicLayer.CarService;
import com.champsoft.cardatabase.BusinessLogicLayer.OwnerService;
import com.champsoft.cardatabase.DataAccessLayer.Car;
import com.champsoft.cardatabase.DataAccessLayer.Owner;
import com.champsoft.cardatabase.DataAccessLayer.OwnerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OwnerController {

    private final OwnerService ownerService;
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owners")
    public ResponseEntity<List<OwnerResponseModel>> getAllOwners(){

        return ResponseEntity.ok(this.ownerService.getAllOwners());

    }
    @GetMapping("/owners/{id}")
    public ResponseEntity<OwnerResponseModel>  getOwnerById(@PathVariable String id){
        OwnerResponseModel ownerResponseModel = this.ownerService.getOwnerByIdNoCars(id);
        return ResponseEntity.ok(ownerResponseModel);
    }
    @PostMapping("/owners")
    public ResponseEntity<OwnerResponseModel> createNewOwner(@RequestBody OwnerRequestModel ownerData){
        OwnerResponseModel ownerResponseModel = this.ownerService.createNewOwner(ownerData);

        return ResponseEntity.ok(ownerResponseModel);
    }
    @PutMapping("/owners/{id}")
    public ResponseEntity<OwnerResponseModel>  updateOwner( @PathVariable String id,    @RequestBody OwnerRequestModel ownerData){
        OwnerResponseModel ownerResponseModel = this.ownerService.updateOwner(id,ownerData);
        return ResponseEntity.ok(ownerResponseModel);
    }
    @DeleteMapping("/owners/{id}")
    public ResponseEntity<Void> deleteOwnerById(@PathVariable String id){
        this.ownerService.deleteOwnerById(id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }

    // get the list cars
    @GetMapping("/owners/{id}/cars")
    public ResponseEntity<List<CarResponseModel>>  getCarsOfOwnerById(@PathVariable String id){
        return ResponseEntity.ok(this.ownerService.getCarsOfOwnerById(id));
    }



}
