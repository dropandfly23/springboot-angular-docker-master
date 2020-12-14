package com.example.angular.springbootangulardocker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.angular.springbootangulardocker.model.Environnement;

@Repository
public interface EnvironnementRepository extends JpaRepository<Environnement,Integer> {
	Environnement findEnvironnementById(int id);
	List<Environnement> findByEnvIgnoreCase(String nom);
	Optional<Environnement> findByEnv(String nom);
}
