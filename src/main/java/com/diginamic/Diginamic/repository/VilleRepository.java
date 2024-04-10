package com.diginamic.Diginamic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diginamic.Diginamic.model.Ville;

public interface VilleRepository extends JpaRepository<Ville, Integer> {
	List<Ville> findAll();
    Ville findByNom(String nom);
    Ville findById(Long id);
    Ville save(Ville villeAdded);
    void delete(Ville villeAdded);
}
