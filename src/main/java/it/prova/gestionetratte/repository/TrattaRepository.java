package it.prova.gestionetratte.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetratte.model.Tratta;

public interface TrattaRepository extends CrudRepository<Tratta, Long>{
	
	List<Tratta> findByCodiceAndDescrizione(String codice, String descrizione);
	
}
