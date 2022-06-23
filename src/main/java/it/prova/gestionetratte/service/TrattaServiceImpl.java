package it.prova.gestionetratte.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.model.Stato;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.repository.TrattaRepository;

@Service
public class TrattaServiceImpl implements TrattaService{
	
	@Autowired
	private TrattaRepository trattaRepository;
	
	@Autowired 
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public List<Tratta> listAllElements(boolean eager) {
		// TODO Auto-generated method stub
		if (eager)
			return (List<Tratta>) trattaRepository.findAllTratteEager();

		return (List<Tratta>) trattaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Tratta caricaSingoloElemento(Long id) {
		// TODO Auto-generated method stub
		return trattaRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Tratta caricaSingoloElementoConAirbus(Long id) {
		// TODO Auto-generated method stub
		return trattaRepository.findSingleTrattaEager(id);
	}

	@Override
	@Transactional
	public Tratta aggiorna(Tratta trattaInstance) {
		// TODO Auto-generated method stub
		return trattaRepository.save(trattaInstance);
	}

	@Override
	@Transactional
	public Tratta inserisciNuovo(Tratta trattaInstance) {
		// TODO Auto-generated method stub
		return trattaRepository.save(trattaInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Tratta trattaInstance) {
		// TODO Auto-generated method stub
		trattaRepository.delete(trattaInstance);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tratta> findByExample(Tratta example) {
		// TODO Auto-generated method stub
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("SELECT t FROM Tratta t WHERE t.id = t.id ");
		
		if (StringUtils.isNotEmpty(example.getCodice())) {
			whereClauses.add(" t.codice  LIKE :codice ");
			paramaterMap.put("codice", "%" + example.getCodice() + "%");
		}
		if (StringUtils.isNotEmpty(example.getDescrizione())) {
			whereClauses.add(" t.descrizione  LIKE :descrizione ");
			paramaterMap.put("descrizione", "%" + example.getDescrizione() + "%");
		}
		if (example.getData() != null) {
			whereClauses.add("t.data >= :data ");
			paramaterMap.put("data", example.getData());
		}
		if (example.getOraDecollo() != null) {
			whereClauses.add("t.oraDecollo >= :oraDecollo ");
			paramaterMap.put("oraDecollo", example.getOraDecollo());
		}
		if (example.getOraDecollo() != null) {
			whereClauses.add("t.oraAtterraggio >= :oraAtterraggio ");
			paramaterMap.put("oraAtterraggio", example.getOraAtterraggio());
		}
		if(example.getStato() != null) {
			whereClauses.add("t.stato >= :stato");
			paramaterMap.put("stato", example.getStato());
		}
		if(example.getAirbus() != null && example.getAirbus().getId() >= 1) {
			whereClauses.add("t.airbus.id = :airbus");
			paramaterMap.put("airbus", example.getAirbus().getId());
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Tratta> typedQuery = entityManager.createQuery(queryBuilder.toString(), Tratta.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tratta> findByCodiceAndDescrizione(String codice, String descrizione) {
		// TODO Auto-generated method stub
		return trattaRepository.findByCodiceAndDescrizione(codice, descrizione);
	}

	@Override
	@Transactional
	public boolean concludiTratte() {
		// TODO Auto-generated method stub
		if(trattaRepository.chiudiTratte(LocalDate.now(), LocalTime.now()) >= 1) {
			return true;
		}
		
		return false;
		
	}

}
