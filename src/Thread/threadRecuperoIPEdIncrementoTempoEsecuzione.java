package Thread;

import javax.swing.JOptionPane;

import Eccezioni.ConnessioneAlSitoFallitaException;
import Eccezioni.ConnessioneAlSitoFallitaExceptionENG;
import Finestre.finestraDelleImpostazioni;
import Finestre.finestraDelleInformazioni;
import Finestre.finestraPrincipale;
import Finestre.operazioniFinestre;
import Lingue.LanguageAssign;
import Programma.classeDiAvvio;
import Programma.operazioniRete;

public class threadRecuperoIPEdIncrementoTempoEsecuzione extends Thread {
	
	private int oreD=0;
	private int minutiD=0;
	private int secondiD=0;
	private Boolean threadAttivato=false;
	private Boolean threadInPausa=false;
	
	
	public void disable() {
		threadAttivato=false;
	}
	
	
	private void incrementaContatoreTempoDiEsecuzione(finestraPrincipale framePrincipale) {
		try {
			secondiD++; //Incremento la variabile secondi di 1
			if(secondiD==60) { //Se ho 60 secondi...
				minutiD++; //...Incremento la variabile minuti di 1
				secondiD=0; //Azzero i secondi
				if(minutiD==60) { //Se ho 60 minuti...
					oreD++; //...Incremento la variabile ore di 1
					minutiD=0; //Azzero i minuti
				}
			}//Fine IF
			framePrincipale.setTempoEsecuzione(oreD,minutiD,secondiD); //Aggiorno il tempo di esecuzione sul frame principale
			}catch(Exception e) {
				LanguageAssign lingua = new LanguageAssign();	lingua=classeDiAvvio.getOggettoLingua();
				if(lingua.getLinguaAssegnata().equals("eng")) {
					JOptionPane.showMessageDialog(null, "Unknown Error, Program Failure!");
				}else {
					JOptionPane.showMessageDialog(null, "Errore sconosciuto... Il programma si chiudera'...");
				}
				System.exit(1);
				}
	}
	
	
	@Override
	public void run() {
		threadAttivato=true;
		while(threadAttivato) {
		//Oggetti per la gestione delle finestre, del frame principale, della finestra delle impostazioni e delle operazioni di rete
		operazioniFinestre opFinestre = new operazioniFinestre();   opFinestre=classeDiAvvio.getOggettoFinestre();
		finestraPrincipale framePrincipale = new finestraPrincipale();  framePrincipale=opFinestre.getFinestraPrincipale();
		finestraDelleInformazioni frameConsole = new finestraDelleInformazioni();  frameConsole=opFinestre.getFinestraConsole();
		finestraDelleImpostazioni frameImpostazioni = new finestraDelleImpostazioni();  frameImpostazioni = opFinestre.getFinestraImpostazioni();
		operazioniRete opRETE = new operazioniRete();
		LanguageAssign lingua = new LanguageAssign();	lingua=classeDiAvvio.getOggettoLingua();
		int secondi=0; //Il tempo prima dell'aggiornamento successivo specificato nelle impostazioni
		final int secondiPrimaDelTentativo=10; //Il tempo in secondi prima del tentativo successivo in caso di connessione fallita
		
		if(framePrincipale.getAutoModeState()) { //Esegue le operazioni finche' la variabile di controllo relativa alla modalita AUTOMATICCA e' true
			try {
			if(threadInPausa) {
				secondiD=0;
				minutiD=0;
				oreD=0;
				framePrincipale.setTempoEsecuzione(0,0,0);
				threadInPausa=false;
			}
			framePrincipale.disableStartButton(); //Disabilita il pulsante di avvio (THREAD IN ESECUZIONE)
			secondi=Integer.parseInt(frameImpostazioni.getTimeout());  //Recupero il valore dei secondi dalle impostazioni
			int Condizione=secondi; //Variabile che non deve essere modificata (E' la condizione del FOR) e coincide con i secondi
				opRETE.recuperaIP();  //Recupero l'IP
				if(lingua.getLinguaAssegnata().equals("eng")) {
					frameConsole.setStatoAsMessaggio("Done! Waiting for the timeout");
				}else {
					frameConsole.setStatoAsMessaggio("Operazione effettuata!  Timeout programmato");
				}
				//Procedure per il countdown
				for(int i=0;i<Condizione;i++) { //Inizio FOR per il COUNTDOWN
					if(framePrincipale.getAutoModeState()==false) { //Se il thread deve fermarsi...
						break;
					}
					Thread.sleep(1000);  //Attendo 1 secondo
					secondi--;  //Diminuisco di uno i secondi prima del prossimo aggiornamento
					framePrincipale.setTempoRimanente(secondi); //Mostro il tempo rimanente prima del prossimo aggiornamento
					incrementaContatoreTempoDiEsecuzione(framePrincipale); //Incremento il tempo di esecuzione
				} //Fine FOR
				
		//Prima Exception lanciata in caso di lingua italiana		
		}catch(ConnessioneAlSitoFallitaException csfe) { //Se la connessione fallisce...
			//...effettuo un tentativo dopo 10 secondi
			Boolean mostraMsg=true;	//Indica se mostrare il messaggio alla fine del timer
			for(int i=0;i<secondiPrimaDelTentativo;i++) {
			frameConsole.setStatoAsMessaggio(csfe.getMessage()+" Prossimo tentativo in : "+(secondiPrimaDelTentativo-(i))+" secondi"); //Messaggio che mostra il tempo rimanente prima dell'aggiornamento successivo nella barra di stato
			try {
				if(framePrincipale.getAutoModeState()==false) { //Se il thread deve fermarsi...
					mostraMsg=false; //...Non devo mostrare il messaggio relativo ad un nuovo tentativo
					break;
				}	
			Thread.sleep(1000); //Attendo 1 secondo
			}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Errore sconosciuto... Il programma si chiudera'...");
				System.exit(1);
			}
			}//Fine FOR
			if(mostraMsg==true) { //Se il thread non e' stato interrotto durante il countdown di 10 secondi...
					frameConsole.setStatoAsMessaggio("Eseguo nuovamente un tentativo..."); //mostro il seguente messaggio relativo ad un nuovo tentativo
			}
			
		}//Fine Prima Exception
		
		//Seconda exception lanciata in caso di lingua inglese
		catch(ConnessioneAlSitoFallitaExceptionENG csfeeng) {
			Boolean mostraMsg=true;	//Indica se mostrare il messaggio alla fine del timer
			for(int i=0;i<secondiPrimaDelTentativo;i++) {
			frameConsole.setStatoAsMessaggio(csfeeng.getMessage()+" Next attempt in : "+(secondiPrimaDelTentativo-(i))+" seconds"); //Messaggio che mostra il tempo rimanente prima dell'aggiornamento successivo nella barra di stato
			try {
				if(framePrincipale.getAutoModeState()==false) { //Se il thread deve fermarsi...
					mostraMsg=false; //...Non devo mostrare il messaggio relativo ad un nuovo tentativo
					break;
				}	
			Thread.sleep(1000); //Attendo 1 secondo
			}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Unknown Error, Program Failure!");
				System.exit(1);
			}
			}//Fine FOR
			if(mostraMsg==true) { //Se il thread non e' stato interrotto durante il countdown di 10 secondi...
					frameConsole.setStatoAsMessaggio("Performing a new attempt..."); //mostro il seguente messaggio relativo ad un nuovo tentativo
				}
		}//Fine seconda exception
		
		//Terza Exception
		catch(Exception e) { //Se incappo in un errore sconosciuto...
			if(lingua.getLinguaAssegnata().equals("eng")) {
				JOptionPane.showMessageDialog(null, "Unknown Error, Program Failure!");
			}else {
				JOptionPane.showMessageDialog(null, "Errore sconosciuto... Il programma si chiudera'...");
			}
			System.exit(1);
		}//Fine terza exception
		}else {
		framePrincipale.enableStartButton(); //IL THREAD SI CONCLUDE, ABILITO IL PULSANTE START E DISABILITO IL PULSANTE INTERROMPI
		threadInPausa=true;
		}
		}//Fine WHILE
	}//FINE metodo
}//Fine classe
	

