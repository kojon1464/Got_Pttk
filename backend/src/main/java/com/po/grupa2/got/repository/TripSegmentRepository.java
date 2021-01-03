package com.po.grupa2.got.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.po.grupa2.got.model.Trip;
import com.po.grupa2.got.model.TripSegment;
import com.po.grupa2.got.model.TripSegmentKey;

@Repository
public interface TripSegmentRepository extends JpaRepository<TripSegment, TripSegmentKey>{

	public void deleteByTrip(Trip trip);
}
