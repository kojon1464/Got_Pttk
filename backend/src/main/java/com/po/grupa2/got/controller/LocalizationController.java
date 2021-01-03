package com.po.grupa2.got.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.po.grupa2.got.model.Localization;
import com.po.grupa2.got.repository.LocalizationRepository;

@RestController
public class LocalizationController {

	@Autowired
	private LocalizationRepository localizationRepository;
	
	@GetMapping("/localizations")
	private ResponseEntity<List<Localization>> routes() throws Exception {
		return ResponseEntity.ok(localizationRepository.findAll());
	}
}
