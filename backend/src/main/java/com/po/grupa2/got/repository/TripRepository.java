package com.po.grupa2.got.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.po.grupa2.got.model.Trip;
import com.po.grupa2.got.model.User;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

	public List<Trip> findByUser(User user);
}
