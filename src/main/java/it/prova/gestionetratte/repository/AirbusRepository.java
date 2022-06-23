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
	
	@Query(value = "select distinct a.*\r\n"
			+ "from Tratta t1, Tratta t2, Airbus a\r\n"
			+ "where t1.airbus_id = a.id and t1.airbus_id = t2.airbus_id and t1.id <> t2.id\r\n"
			+ "and ((t1.oraatterraggio between t2.oradecollo and t2.oraatterraggio) or (t1.oradecollo between t2.oradecollo and t2.oraatterraggio))", nativeQuery = true)
	List<Airbus> airbusConSovrapposizioni();
	
}
