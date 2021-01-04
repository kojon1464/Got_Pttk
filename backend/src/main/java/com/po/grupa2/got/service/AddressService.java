package com.po.grupa2.got.service;

import org.springframework.stereotype.Service;

import com.po.grupa2.got.dto.ErrorDTO;
import com.po.grupa2.got.model.Address;

@Service
public class AddressService {

	public ErrorDTO validateAddress(Address address) {
		if(address.getTown() == null || address.getTown().equals(""))
			return new ErrorDTO("Town field is empty!");
		
		if(address.getStreet() == null || address.getStreet().equals(""))
			return new ErrorDTO("Street field is empty!");
		
		if(address.getPostalCode() == null || address.getPostalCode().equals(""))
			return new ErrorDTO("Postal code field is empty!");
		
		if(!address.getPostalCode().matches("[0-9]{2}-[0-9]{3}"))
			return new ErrorDTO("Postal code should be in poland (**-***)!");
		
		if(address.getBuildingNumber() == null || address.getBuildingNumber().equals(""))
			return new ErrorDTO("Bulding number field is empty!");
		
		if(address.getFlatNumber() == null || address.getFlatNumber().equals(""))
			return new ErrorDTO("Flat number field is empty!");
		
		
		return null;
	}
}
