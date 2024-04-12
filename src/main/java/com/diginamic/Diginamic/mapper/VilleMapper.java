package com.diginamic.Diginamic.mapper;

import com.diginamic.Diginamic.DTO.VilleDto;
import com.diginamic.Diginamic.exception.GestionExceptions;
import com.diginamic.Diginamic.model.Departement;
import com.diginamic.Diginamic.model.Ville;
import com.diginamic.Diginamic.service.DepartementService;

public class VilleMapper {


    DepartementService departementService;

    public VilleDto toDto(Ville ville) {
        VilleDto villeDto = new VilleDto();
        villeDto.setNom(ville.getNom());
        villeDto.setNbHabitants(ville.getNbHabitants());
        villeDto.setCodeDepartement(ville.getDepartement().getCode());
        return villeDto;
    }

    public Ville toBean(VilleDto villeDto) throws GestionExceptions {
        Ville ville = new Ville();
        Departement departement = departementService.getDepartementByCode(villeDto.getCodeDepartement());
        ville.setNom(villeDto.getNom());
        ville.setNbHabitants(villeDto.getNbHabitants());
        ville.setDepartement(departement);
        return ville;
    }
}