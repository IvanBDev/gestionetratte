package it.prova.gestionetratte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetratte.model.Tratta;

public interface TrattaRepository extends CrudRepository<Tratta, Long>{
	
	List<Tratta> findByCodiceAndDescrizione(String codice, String descrizione);
	
	@Query("SELECT t FROM Tratta t JOIN FETCH t.airbus")
	List<Tratta> findAllFilmEager();
	
	@Query("FROM Tratta t JOIN FETCH t.airbus WHERE t.id = ?1")
	Tratta findSingleTrattaEager(Long id);
	
}
