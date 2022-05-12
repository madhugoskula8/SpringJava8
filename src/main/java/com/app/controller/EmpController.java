package com.app.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Employee;
import com.app.repository.EmpRepository;

@RestController
public class EmpController {
	
	@Autowired
	private EmpRepository repo;
	
	@GetMapping("/getAll")
	public ResponseEntity<?> alll(){
		//List<Employee> e=repo.findAll();
	List<String> e=repo.findAll().stream().map(emp->emp.getName()).collect(Collectors.toList());
	Map<String, Long> m=e.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
	List<String> s=m.entrySet().stream().filter(entry->entry.getValue()>1).map(entry->entry.getKey())
			.collect(Collectors.toList());
	return new ResponseEntity<>(s,HttpStatus.OK);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll(){
	List<String> namesInRepo=repo.findAll().stream().map(emp->emp.getName()).collect(Collectors.toList());
	Set<String> names=namesInRepo.stream().filter(name->Collections.frequency(namesInRepo, name)>1)
			.collect(Collectors.toSet());
	return new ResponseEntity<>(names,HttpStatus.OK);
	}
	
	@GetMapping("/groupByAge")
	public ResponseEntity<?> all(){
	   Map<Integer,List<Employee>> empMap=repo.findAll().stream()
			   .collect(Collectors.groupingBy(emp->emp.getAge(),Collectors.toList()));
	return new ResponseEntity<>(empMap,HttpStatus.OK);
	}
	
	@GetMapping("/groupByAgeUnique")
	public ResponseEntity<?> groupByAgeWithUnique(){
	   Map<Integer,Set<Employee>> empMap=repo.findAll().stream()
			   .collect(Collectors.groupingBy(emp->emp.getAge(),Collectors.toSet()));
	return new ResponseEntity<>(empMap,HttpStatus.OK);
	}
	@GetMapping("/groupByAgeSorting")
	public ResponseEntity<?> groupByAgeWithSorting(){
	   Map<Integer,Set<Employee>> empMap=repo.findAll().stream()
			   .collect(Collectors.groupingBy(emp->emp.getAge(),TreeMap::new,Collectors.toSet()));
	return new ResponseEntity<>(empMap,HttpStatus.OK);
	}
	
	@GetMapping("/getOne/{id}")
	public ResponseEntity<Employee> getEmp(@PathVariable Integer id){
		Employee e=repo.getById(id);
		return new ResponseEntity<Employee>(e,HttpStatus.OK);
	}
	
	@PostMapping("/addEmp")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp){
		repo.save(emp);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}

}
