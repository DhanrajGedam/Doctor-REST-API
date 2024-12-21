package com.spy.springboot.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spy.springboot.dto.Doctor;
import com.spy.springboot.service.DoctorService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@PostMapping
	public ResponseEntity<Object> createDoctor(@RequestBody Doctor doctor){
		return doctorService.createDoctor(doctor);
	}
	
	@PostMapping("/many")
	public ResponseEntity<Object> createDoctors(@RequestBody List<Doctor> doctor){
		return doctorService.createDoctors(doctor);
	}
	
	@GetMapping
	public ResponseEntity<Object> fetchAllDoctors(){
		return doctorService.fetchAllDoctors();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> fetchDoctorById(@PathVariable("id") Long doc_id){
		return doctorService.fetchDoctorById(doc_id);
	}
	
	@GetMapping("/name/{doctorName}")
	public ResponseEntity<Object> fetchDoctorByName(@PathVariable("doctorName") String doc_name){
		return doctorService.fetchDoctorByName(doc_name);
	}
	
	@GetMapping("/fees/greater/{fees}")
	public ResponseEntity<Object> fetchByPriceGreater(@PathVariable("fees") double doc_fees){
		return doctorService.fetchByFeesGreater(doc_fees);
	}
	
	@GetMapping("/fees/{min}/{max}")
	public ResponseEntity<Object> fetchByFeesBetween(@PathVariable("min") int min_fees,
													  @PathVariable("max") int max_fees){
		return doctorService.fetchByFeesBetween(min_fees,max_fees);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteDoctorbyId(@PathVariable("id") Long doc_id){
		return doctorService.deleteDoctorById(doc_id);
	}
	
	@PutMapping
	public ResponseEntity<Object> updateDoctor(@RequestBody Doctor doc_id){
		return doctorService.updateDoctor(doc_id);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Object> updateDoctor(@PathVariable("id") Long doc_id,
											   @RequestBody Doctor doctor){
		return doctorService.updateDoctor(doc_id,doctor);
	}
}
