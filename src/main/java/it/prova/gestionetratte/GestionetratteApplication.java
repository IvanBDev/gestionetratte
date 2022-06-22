package it.prova.gestionetratte;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.model.Stato;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.service.AirbusService;
import it.prova.gestionetratte.service.TrattaService;

@SpringBootApplication
public class GestionetratteApplication implements CommandLineRunner {

	@Autowired
	private AirbusService airbusService;

	@Autowired
	private TrattaService trattaService;

	public static void main(String[] args) {
		SpringApplication.run(GestionetratteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		String codiceAirbus = "TRV15001930";
		String descrizioneAirbus = "FastTravel";

		Airbus airbusRV = airbusService.findByCodiceAndDescrizione(codiceAirbus, descrizioneAirbus);
		if (airbusRV == null) {

			airbusRV = new Airbus(codiceAirbus, descrizioneAirbus,
					new SimpleDateFormat("dd/MM/yyyy").parse("14/08/1995"), 350);
			airbusService.inserisciNuovo(airbusRV);

		}
		
		LocalDate data = LocalDate.parse("1995-08-14");
		LocalTime oraDecollo = LocalTime.of(15, 0);
		LocalTime oraAtterraggio = LocalTime.of(19, 30);
		Tratta trattaRV = new Tratta("T250-150Km", "Roma-Venezia", data, oraDecollo, oraAtterraggio, Stato.CONCLUSA, airbusRV);
		
		if(trattaService.findByCodiceAndDescrizione(trattaRV.getCodice(), trattaRV.getDescrizione()).isEmpty()) {
			
			trattaService.inserisciNuovo(trattaRV);
			
		}
		
		
	}

}
