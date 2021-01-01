package com.po.grupa2.got.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.po.grupa2.got.model.RouteState;

@Repository
public interface RouteStateRepository extends JpaRepository<RouteState, Long>{

	public List<RouteState> findByRouteId(long id);
}
