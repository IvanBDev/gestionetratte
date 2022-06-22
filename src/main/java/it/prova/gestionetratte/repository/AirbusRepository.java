package it.prova.gestionetratte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetratte.model.Airbus;

public interface AirbusRepository extends CrudRepository<Airbus, Long>{
	
	public Airbus findByCodiceAndDescrizione(String codice, String descrizione); 
	
	@Query("SELECT DISTINCT a FROM Airbus a LEFT JOIN FETCH a.tratte")
	public List<Airbus> findAllEager();
	
	@Query("FROM Airbus a LEFT JOIN FETCH a.tratte WHERE a.id=?1")
	Airbus findByIdEager(Long idAirbus);
	
}
