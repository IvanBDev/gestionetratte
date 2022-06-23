package it.prova.gestionetratte.web.api.exception;

public class TrattaAncoraAttivaOConclusa extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TrattaAncoraAttivaOConclusa(String message) {
		super (message);
	}

}
