package com.diginamic.Diginamic.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.diginamic.Diginamic.model.Departement;

@Repository
public interface DepartementRepository extends CrudRepository<Departement, Long> {
    Optional<Departement> findByCode(String code);
}
