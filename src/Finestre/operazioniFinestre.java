package Finestre;

import javax.swing.JOptionPane;
import Lingue.LanguageAssign;
import Programma.classeDiAvvio;

public class operazioniFinestre { //Classe che gestisce le finestre
	
	private finestraPrincipale framePrincipale = new finestraPrincipale();  //Oggetto della finestra principale
	private finestraDelleImpostazioni frameImpostazioni = new finestraDelleImpostazioni(); //Oggetto della finestra delle impostazioni
	private finestraDelleInformazioni frameConsole = new finestraDelleInformazioni(); //Oggetto della finestra delle informazioni (Console)
	
	public void mostraFramePrincipale() {  //Metodo che mostra il frame principale
		framePrincipale.setVisible(true);
	}
	
	public void mostraFrameImpostazioni() { //Metodo che mostra la finestra delle impostazioni
		frameImpostazioni.setVisible(true);
	}
	
	public void mostraFrameConsole() {  //Metodo che mostra la console
		frameConsole.setVisible(true);
	}
	
	public void assegnareValoreDiDefaultAlleImpostazioni() { //Assegna i valori di default alle impostazioni, VEDI la classe della finestraDelleImpostazioni
		frameImpostazioni.assegnaValoreDiDefault();
	}
	
	public void assegnareValoreDiDefaultAlTimerDiEsecuzione() { //Assegna il tempo di default al timer di esecuzione, VEDI la classe della finestra principale
		framePrincipale.setTempoEsecuzione(0,0,0);
	}
	
	public void modificaFinestraPrincipale() { //Sceglie come far effettuare la ricostruzione del frame principale dopo aver cambiato la modalita' di esecuzione
		if(frameImpostazioni.modalitaAutomaticaAbilitata()==true) {
			framePrincipale.impostaModalitaAutomatica();
		}else {
			framePrincipale.impostaModalitaManuale();
		}
	}
	
	public finestraPrincipale getFinestraPrincipale() { //Metodo che serve per recuperare l'oggetto framePrincipale e utilizzzarlo in determinati casi
		return framePrincipale;
	}
	
	public finestraDelleImpostazioni getFinestraImpostazioni() { //Metodo che serve per recuperare l'oggetto frameImpostazioni e utilizzarlo in determinati casi
		return frameImpostazioni;
	}
	
	public finestraDelleInformazioni getFinestraConsole() { //Metodo che serve per recuperare l'oggetto frameConsole e utilizzarlo in determinati casi
		return frameConsole;
	}
	
	public void aggiornaFinestraPrincipaleDopoModificheImpostazioni() { //Effettuo il salvataggio dei dati dalle impostazioni
		Boolean successo=true;
		String temposalvato=frameImpostazioni.getTimeout(); //Salvo il tempo dalle impostazioni e mostro un messaggio in caso di successo / insuccesso
		LanguageAssign lingua = new LanguageAssign();	lingua=classeDiAvvio.getOggettoLingua();
		try { //Controllo se il tempo immesso e' corretto
		int tempodaassegnare=Integer.parseInt(temposalvato); //Controllo e trasformo in int
		framePrincipale.setTempoRimanente(tempodaassegnare); //Imposto il nuovo tempo
		}catch(NumberFormatException nfe) {
			successo=false;
			if(lingua.getLinguaAssegnata().equals("eng")) {
				JOptionPane.showMessageDialog(null, "You must provide a valid time format into settings !"); //messaggio di errore
			}else {
				JOptionPane.showMessageDialog(null, "Inserire un tempo valido nelle impostazioni !"); //messaggio di errore
			}
		}
		if(successo==true) {
		//Messaggio di successo
		if(lingua.getLinguaAssegnata().equals("eng")) {
			frameConsole.setStatoAsMessaggio("Settings have been saved successfully..."); //MOstra un messaggio nel frame principale (ATTENZIONE, DA ELIMINARE)
		}else {
			frameConsole.setStatoAsMessaggio("Le impostazioni sono state salvate correttamente..."); //MOstra un messaggio nel frame principale (ATTENZIONE, DA ELIMINARE)
		}
		}
	}
	
	public void primoRecuperoDaImpostazioni() { //Effettuo il recupero dei dati all'avvio del programma
		String temposalvato=frameImpostazioni.getTimeout(); //Salvo il tempo dalle impostazioni
		int tempodaassegnare=Integer.parseInt(temposalvato); //Controllo e trasformo in int
		framePrincipale.setTempoRimanente(tempodaassegnare); //Imposto il nuovo tempo
	}
	
}