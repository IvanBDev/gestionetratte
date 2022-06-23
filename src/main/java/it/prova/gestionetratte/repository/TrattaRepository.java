package it.prova.gestionetratte.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetratte.model.Tratta;

public interface TrattaRepository extends CrudRepository<Tratta, Long>{
	
	List<Tratta> findByCodiceAndDescrizione(String codice, String descrizione);
	
	@Query("SELECT t FROM Tratta t JOIN FETCH t.airbus")
	List<Tratta> findAllTratteEager();
	
	@Query("FROM Tratta t JOIN FETCH t.airbus WHERE t.id = ?1")
	Tratta findSingleTrattaEager(Long id);
	
	@Modifying
	@Query("update Tratta t set t.stato = 'CONCLUSA' where t.stato = 'ATTIVA' and t.data = ?1 and t.oraAtterraggio <= ?2")
	int chiudiTratte(LocalDate data, LocalTime orario);
	
}
