package com.spy.springboot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spy.springboot.dto.Doctor;
import com.spy.springboot.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	public ResponseEntity<Object> createDoctor(Doctor doctor) {
		doctorRepository.save(doctor);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("message", "Data Added Success");
		map.put("Data", doctor);
		return new ResponseEntity<Object>(map,HttpStatus.CREATED);
	}

	public ResponseEntity<Object> createDoctors(List<Doctor> doctor) {
		doctorRepository.saveAll(doctor);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "Data added successfully");
		map.put("Data", doctor);
		return new ResponseEntity<Object>(map,HttpStatus.CREATED);
	}

	public ResponseEntity<Object> fetchAllDoctors() {
		List<Doctor> list = doctorRepository.findAll();
		if (list.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Doctors not available");
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Doctors available");
			map.put("Data", list);
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> fetchDoctorById(Long doc_id) {
		Optional<Doctor> optional = doctorRepository.findById(doc_id);
		if(optional.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Doctors available with Id: "+doc_id);

			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Doctors available");
			map.put("data", optional.get());

			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
		
		
	}

	public ResponseEntity<Object> fetchDoctorByName(String doc_name) {
		List<Doctor> doctors = doctorRepository.findByDoctorName(doc_name);
		if (doctors.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Doctors available with Name :"+doc_name);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Doctors available");
			map.put("data", doctors);
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> fetchByFeesGreater(double doc_fees) {
		List<Doctor> doctors = doctorRepository.findByfeesGreaterThanEqual(doc_fees);
		if (doctors.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Doctors Found Fees Greater Than: "+doc_fees);

			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Doctors available");
			map.put("data", doctors);

			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> fetchByFeesBetween(int min_fees, int max_fees) {
		List<Doctor> list = doctorRepository.findByfeesBetween(min_fees,max_fees);
		if (list.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Doctors Found Fees Between: "+min_fees+" and "+max_fees);

			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Doctors available");
			map.put("data", list);

			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> deleteDoctorById(Long doc_id) {
		Optional<Doctor> optional = doctorRepository.findById(doc_id);
		if(optional.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Doctor Found with Id: "+doc_id);

			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			doctorRepository.deleteById(doc_id);
			map.put("message", "Doctor Deleted Successfully");
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}


	public ResponseEntity<Object> updateDoctor(Doctor doc_id) {
		doctorRepository.save(doc_id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "Doctor Updated Successfully");

		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}

	public ResponseEntity<Object> updateDoctor(Long doc_id, Doctor doctor) {
		Optional<Doctor> optional = doctorRepository.findById(doc_id);
		if(optional.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Product Found with Id: "+doc_id);

			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			
			Doctor existingProduct = optional.get();
			if(doctor.getDoctorName()!=null)
				existingProduct.setDoctorName(doctor.getDoctorName());
			if(doctor.getSpecialization()!=null)
				existingProduct.setSpecialization(doctor.getSpecialization());
			if(doctor.getFees()!=0)
				existingProduct.setFees(doctor.getFees());
			if(doctor.getEmail()!=null)
				existingProduct.setEmail(doctor.getEmail());
			doctorRepository.save(existingProduct);
			map.put("message", "Doctor Updated Successfully");
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	
	
}
