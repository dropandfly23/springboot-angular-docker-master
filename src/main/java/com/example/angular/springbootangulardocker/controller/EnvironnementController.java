package com.example.angular.springbootangulardocker.controller;

import com.example.angular.springbootangulardocker.ResourceNotFoundException;
import com.example.angular.springbootangulardocker.model.Environnement;
import com.example.angular.springbootangulardocker.repository.EnvironnementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/env")
@CrossOrigin(origins = "*")
public class EnvironnementController {

    @Autowired
    private EnvironnementRepository repo;
    
    @GetMapping("/{id}")
	public Environnement findById(@PathVariable Integer id) {
		Optional<Environnement> optional = repo.findById(id);
		if (optional.isPresent()) {
			Environnement c = optional.get();
			return c;
		} else {
			throw new RuntimeException("id introuvable : " + id);
		}
	};

	@GetMapping("/searchByName/{env}")
	public List<Environnement> findByCodePostalStartingWith(@PathVariable(name = "env") String env) {
		return repo.findByEnvIgnoreCase(env);

	}

	@GetMapping("/testmethode/{env}")
	public Environnement findTest(@PathVariable(name = "env") String env) {
		Optional<Environnement> optional=repo.findByEnv(env);
		if (optional.isPresent()) {
			Environnement c = optional.get();
			return c;
		} else {
			throw new RuntimeException("env introuvable : " + env);
		}

	}

	@GetMapping("/test")
	public List<Environnement> test() {
		return repo.findAll();
	}

	@PostMapping("/addEnvironnement")
	public String addEnvironnement(@RequestBody Environnement c) {
		repo.save(c);
		return c.getNom() + " Added Environnement " + c.getEnv();
	}
    @Transactional
	@DeleteMapping("/delete/{env}")
	public String delete(@PathVariable String env) {
		List<Environnement> envir= repo.findByEnvIgnoreCase(env);
		if(!envir.isEmpty()) {
			for(int i=0;i<envir.size();i++) {
				repo.delete(envir.get(i));
			}
			return "deleted";
		}
		else 
			return "error in delete method";
	}
}

