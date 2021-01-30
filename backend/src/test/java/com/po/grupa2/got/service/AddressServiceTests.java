package com.po.grupa2.got.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.po.grupa2.got.dto.ErrorDTO;
import com.po.grupa2.got.model.Address;
import com.po.grupa2.got.model.BadgeRank;

@RunWith(SpringRunner.class)
public class AddressServiceTests {
	
    @TestConfiguration
    static class AddressServiceTestContextConfiguration {
 
        @Bean
        public AddressService addressService() {
            return new AddressService();
        }
    }
	
	@Autowired
	private AddressService addressService;
	
	
    @Test
    public void validateAddress_pass() {     
    	String town = "Wrocław";
    	String postalCode = "40-600";
    	String street = "Nadodzre";
    	String buildingNumber = "1";
    	String flatNumber = "1";
    	
    	Address address = new Address(town, postalCode, street, buildingNumber, flatNumber);
    	
    	ErrorDTO result = addressService.validateAddress(address);
    	
    	assertEquals(null, result);
    }
    
    @Test
    public void validateAddress_wrong_code() {     
    	String town = "Wrocław";
    	String postalCode = "40600";
    	String street = "Nadodzre";
    	String buildingNumber = "1";
    	String flatNumber = "1";
    	
    	Address address = new Address(town, postalCode, street, buildingNumber, flatNumber);
    	
    	ErrorDTO result = addressService.validateAddress(address);
    	
    	assertEquals("Postal code should be in poland (**-***)!", result.getError());
    }
    
    @Test
    public void validateAddress_posatl_code_empty() {     
    	String town = "Wrocław";
    	String postalCode = "";
    	String street = "Nadodzre";
    	String buildingNumber = "1";
    	String flatNumber = "1";
    	
    	Address address = new Address(town, postalCode, street, buildingNumber, flatNumber);
    	
    	ErrorDTO result = addressService.validateAddress(address);
    	
    	assertEquals("Postal code field is empty!", result.getError());
    }
    
    @Test
    public void validateAddress_town_empty() {     
    	String town = "";
    	String postalCode = "40-600";
    	String street = "Nadodzre";
    	String buildingNumber = "1";
    	String flatNumber = "1";
    	
    	Address address = new Address(town, postalCode, street, buildingNumber, flatNumber);
    	
    	ErrorDTO result = addressService.validateAddress(address);
    	
    	assertEquals("Town field is empty!", result.getError());
    }
    
    @Test
    public void validateAddress_street_empty() {     
    	String town = "Wrocław";
    	String postalCode = "40-600";
    	String street = "";
    	String buildingNumber = "1";
    	String flatNumber = "1";
    	
    	Address address = new Address(town, postalCode, street, buildingNumber, flatNumber);
    	
    	ErrorDTO result = addressService.validateAddress(address);
    	
    	assertEquals("Street field is empty!", result.getError());
    }
    
    @Test
    public void validateAddress_building_number_empty() {     
    	String town = "Wrocław";
    	String postalCode = "40-600";
    	String street = "Nadodzre";
    	String buildingNumber = "";
    	String flatNumber = "1";
    	
    	Address address = new Address(town, postalCode, street, buildingNumber, flatNumber);
    	
    	ErrorDTO result = addressService.validateAddress(address);
    	
    	assertEquals("Bulding number field is empty!", result.getError());
    }
    
    @Test
    public void validateAddress_falt_number_empty() {     
    	String town = "Wrocław";
    	String postalCode = "40-600";
    	String street = "Nadodzre";
    	String buildingNumber = "1";
    	String flatNumber = "";
    	
    	Address address = new Address(town, postalCode, street, buildingNumber, flatNumber);
    	
    	ErrorDTO result = addressService.validateAddress(address);
    	
    	assertEquals("Flat number field is empty!", result.getError());
    }

}
