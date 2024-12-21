package com.spy.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spy.springboot.dto.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	List<Doctor> findByDoctorName(String doc_name);
	List<Doctor> findByfeesGreaterThanEqual(double price);
	List<Doctor> findByfeesBetween(int min_fees, int max_fees);
	
}
