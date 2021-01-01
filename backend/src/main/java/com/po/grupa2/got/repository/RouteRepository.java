package com.po.grupa2.got.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.po.grupa2.got.model.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

}
