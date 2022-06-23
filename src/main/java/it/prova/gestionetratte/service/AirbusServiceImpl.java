package it.prova.gestionetratte.service;

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

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.repository.AirbusRepository;

@Service
public class AirbusServiceImpl implements AirbusService{
	
	@Autowired
	private AirbusRepository airbusRepository;
	
	@Autowired 
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public List<Airbus> listAllElements(boolean eager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Airbus caricaSingoloElemento(Long id) {
		// TODO Auto-generated method stub
		return airbusRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Airbus aggiorna(Airbus airbusInstance) {
		// TODO Auto-generated method stub
		return airbusRepository.save(airbusInstance);
	}

	@Override
	@Transactional
	public Airbus inserisciNuovo(Airbus airbusInstance) {
		// TODO Auto-generated method stub
		return airbusRepository.save(airbusInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Airbus airbusInstance) {
		// TODO Auto-generated method stub
		airbusRepository.delete(airbusInstance);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Airbus> findByExample(Airbus example) {
		// TODO Auto-generated method stub
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("SELECT a FROM Airbus a WHERE a.id = a.id ");

		if (StringUtils.isNotEmpty(example.getCodice())) {
			whereClauses.add(" a.codice  LIKE :codice ");
			paramaterMap.put("codice", "%" + example.getCodice() + "%");
		}
		if (StringUtils.isNotEmpty(example.getDescrizione())) {
			whereClauses.add(" a.descrizione LIKE :descrizione ");
			paramaterMap.put("descrizione", "%" + example.getDescrizione() + "%");
		}
		if (example.getDataInizioServizio() != null) {
			whereClauses.add("a.dataInizioServizio >= :dataInizioServizio ");
			paramaterMap.put("dataInizioServizio", example.getDataInizioServizio());
		}
		if (example.getNumeroPasseggeri() != null && example.getNumeroPasseggeri() > 1) {
			whereClauses.add(" a.numeroPasseggeri >= :numeroPasseggeri ");
			paramaterMap.put("numeroPasseggeri", example.getNumeroPasseggeri());
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Airbus> typedQuery = entityManager.createQuery(queryBuilder.toString(), Airbus.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Airbus findByCodiceAndDescrizione(String codice, String descrizione) {
		// TODO Auto-generated method stub
		return airbusRepository.findByCodiceAndDescrizione(codice, descrizione);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Airbus> listAllElementsEager() {
		// TODO Auto-generated method stub
		return airbusRepository.findAllEager();
	}

	@Override
	@Transactional(readOnly = true)
	public Airbus caricaSingoloElementoConTratte(Long id) {
		// TODO Auto-generated method stub
		return airbusRepository.findByIdEager(id);
	}

}
