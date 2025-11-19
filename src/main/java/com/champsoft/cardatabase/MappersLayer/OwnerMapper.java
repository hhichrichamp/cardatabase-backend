package com.champsoft.cardatabase.MappersLayer;


import com.champsoft.cardatabase.DataAccessLayer.Owner;
import com.champsoft.cardatabase.PresentationLayer.OwnerRequestModel;
import com.champsoft.cardatabase.PresentationLayer.OwnerResponseModel;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {
    public OwnerResponseModel fromEntityToResponeModel(Owner owner) {
        OwnerResponseModel ownerResponseModel = new OwnerResponseModel();
        ownerResponseModel.setId(owner.getOwnerid());
        ownerResponseModel.setFirstname(owner.getFirstname());
        ownerResponseModel.setLastname(owner.getLastname());
        ownerResponseModel.setPhone(owner.getPhone());
        ownerResponseModel.setEmail(owner.getEmail());
        return ownerResponseModel;
    }

    public Owner fromRequestModelToEntity(OwnerRequestModel ownerData) {
        Owner owner = new Owner();
        owner.setFirstname(ownerData.getFirstname());
        owner.setLastname(ownerData.getLastname());
        owner.setPhone(ownerData.getPhone());
        owner.setEmail(ownerData.getEmail());
        owner.setUsername(ownerData.getUsername());
        owner.setPassword(ownerData.getPassword());
        return owner;
    }
}
