package it.prova.gestionetratte.repository;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetratte.model.Airbus;

public interface AirbusRepository extends CrudRepository<Airbus, Long>{
	
	public Airbus findByCodiceAndDescrizione(String codice, String descrizione); 
	
}
