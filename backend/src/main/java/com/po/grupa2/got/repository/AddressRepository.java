package com.po.grupa2.got.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.po.grupa2.got.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
