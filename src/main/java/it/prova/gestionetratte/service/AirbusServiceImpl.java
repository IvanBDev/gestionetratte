package it.prova.gestionetratte.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.repository.AirbusRepository;

@Service
public class AirbusServiceImpl implements AirbusService{
	
	@Autowired
	private AirbusRepository airbusRepository;
	
	@Autowired 
	private EntityManager entityManager;

	@Override
	public List<Airbus> listAllElements(boolean eager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Airbus caricaSingoloElemento(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Airbus caricaSingoloElementoEager(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Airbus aggiorna(Airbus airbusInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Airbus inserisciNuovo(Airbus airbusInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rimuovi(Airbus airbusInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Airbus> findByExample(Airbus example) {
		// TODO Auto-generated method stub
		return null;
	}

}