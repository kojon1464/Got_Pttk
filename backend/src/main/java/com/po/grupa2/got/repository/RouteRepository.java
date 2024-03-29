package com.po.grupa2.got.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.po.grupa2.got.model.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

	public List<Route> findByStartIdOrEndId(long start_id, long end_id);
}
