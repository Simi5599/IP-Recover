package Lingue;

import Finestre.finestraDelleImpostazioni;
import Finestre.finestraPrincipale;
import Finestre.operazioniFinestre;
import Programma.classeDiAvvio;

public class LanguageAssign {

	private String linguaAssegnata=null;  //Stringa che indica la lingua attualmente selezionata..
	
	public LanguageAssign() { //Costruttore che imposta la lingua di default (ITALIANO)
		linguaAssegnata="ita";
	}
	
	public String getLinguaAssegnata() { //Funzione che ritorna il valore della stringa che indica la lingua selezionata
		return linguaAssegnata;
	}
	
	public void switchLanguages() {  //Metodo che cambia lingua da ITALIANO ad INGLESE e viceversa...
		if(getLinguaAssegnata().equals("eng")) {
			linguaAssegnata="ita";
		}else {
			linguaAssegnata="eng";
		}
	}
	
	public void ricostruisciProgrammaInBaseAllaLinguaSelezionata() { //Attiva i metodi di ricostruzione dei frame dopo un aggiornamento alla lingua
		operazioniFinestre opFinestre = new operazioniFinestre();   opFinestre=classeDiAvvio.getOggettoFinestre();
		finestraDelleImpostazioni frameImpostazioni = new finestraDelleImpostazioni();  frameImpostazioni=opFinestre.getFinestraImpostazioni();
		finestraPrincipale framePrincipale = new finestraPrincipale();  framePrincipale=opFinestre.getFinestraPrincipale();
		frameImpostazioni.aggiornaInBaseAllaLingua();
		framePrincipale.aggiornaInBaseAllaLingua();
		
	}
	
	
	
}
