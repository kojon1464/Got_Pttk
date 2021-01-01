package com.po.grupa2.got.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.po.grupa2.got.model.Localization;

@Repository
public interface LocalizationRepository extends JpaRepository<Localization, Long> {

}
