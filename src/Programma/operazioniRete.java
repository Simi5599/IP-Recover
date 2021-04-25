package Programma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import Eccezioni.ConnessioneAlSitoFallitaException;
import Eccezioni.ConnessioneAlSitoFallitaExceptionENG;
import Finestre.finestraDelleImpostazioni;
import Finestre.finestraDelleInformazioni;
import Finestre.finestraPrincipale;
import Finestre.operazioniFinestre;
import Lingue.LanguageAssign;

public class operazioniRete  { //Classe adibita alle operazioni di rete ed al recupero dei dati
	
	String ipPrivato,ipPubblico;  //Stringhe che memorizzano i valori dei due IP
	

	public void publicIpToString(String urlSito) throws IOException {  //Metodo che salva sulla stringa dedicata l'indirizzo IP pubblico effettuando il collegamento al sito
		URL sitorecuperaIP = new URL(urlSito);
		URLConnection connessione = sitorecuperaIP.openConnection();
		InputStream streamIP = connessione.getInputStream();
		InputStreamReader readerIP = new InputStreamReader(streamIP);
		BufferedReader br = new BufferedReader(readerIP);
		ipPubblico=br.readLine();
	}
	
	public void privateIpToString() throws IOException{ //Metodo che salva sulla stringa dedicata l'indirizzo IP privato
		ipPrivato=InetAddress.getLocalHost().getHostAddress();
	}
	
	
	
	public void recuperaIP() throws ConnessioneAlSitoFallitaException,ConnessioneAlSitoFallitaExceptionENG { //Metodo per effettuare il recupero dell'IP
		//Preparativi
		operazioniFinestre opFinestre = new operazioniFinestre();			opFinestre=classeDiAvvio.getOggettoFinestre();
		finestraDelleImpostazioni frameImpostazioni = new finestraDelleImpostazioni();	frameImpostazioni=opFinestre.getFinestraImpostazioni();
		finestraPrincipale framePrincipale =new finestraPrincipale();		framePrincipale=opFinestre.getFinestraPrincipale();
		finestraDelleInformazioni frameInformazioni = new finestraDelleInformazioni();	frameInformazioni=opFinestre.getFinestraConsole();
		scritturaSuFile scrivoip = new scritturaSuFile();
		//Inizio recupero
		LanguageAssign lingua = new LanguageAssign();	lingua=classeDiAvvio.getOggettoLingua();
		try {
				publicIpToString(frameImpostazioni.getUrlSito());  //Salvo l'IP pubblico
				privateIpToString();				//Salvo l'IP privato
				String result1,result2,result3;
				if(lingua.getLinguaAssegnata().equals("eng")) {
					result1="PUBLIC IP : "+ipPubblico+"\r\n";
					result2="PRIVATE IP : "+ipPrivato+"\r\n";
					result3="IP recover performed succesfully..."+"\r\n";
				}else {
					result1="IP PUBBLICO : "+ipPubblico+"\r\n";
					result2="IP PRIVATO : "+ipPrivato+"\r\n";
					result3="Recupero dell'indirizzo IP effettuato correttamente..."+"\r\n";
				}
				framePrincipale.aggiornaLabelIpPrivato(ipPrivato); //Aggiorno il valore della label relativa all'IP privato
				framePrincipale.aggiornaLabelIpPubblico(ipPubblico); //Aggiorno il valore della label relativa all'IP pubblico
				scrivoip.scriviGliIpSuFileTxt(ipPubblico,ipPrivato);  //Scrivo sul file di testo apposito i valori dei due indirizzi IP
				frameInformazioni.setStatoAsMessaggio(result1+result2+result3);  //Aggiorno il log
			}catch(IOException exc) {
				if(lingua.getLinguaAssegnata().equals("eng")) {
					throw new ConnessioneAlSitoFallitaExceptionENG();  //Lancio un eccezione in inglese
				}else {
					throw new ConnessioneAlSitoFallitaException();  //Lancio un eccezione in italiano
				}
			}
	}	
	}

