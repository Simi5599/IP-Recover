package Programma;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import Lingue.LanguageAssign;


public class scritturaSuFile {
	
	public void scriviGliIpSuFileTxt(String IP, String IPLocale) throws IOException{  //Metodo che scrive i valori degli IP forniti da altre funzioni su un file .txt nominato come : IP
		FileOutputStream scrivere = new FileOutputStream("IP.txt");
		PrintStream scrivo = new PrintStream(scrivere);
		LanguageAssign lingua = new LanguageAssign();	lingua=classeDiAvvio.getOggettoLingua();
		if(lingua.getLinguaAssegnata().equals("eng")) {
			scrivo.print("Last IP recovered:\r\nPublic IP: "+IP+"\r\n"+"Private IP : "+IPLocale);
		}else {
			scrivo.print("Ultimi IP rilevati:\r\nIP Pubblico: "+IP+"\r\n"+"IP Privato : "+IPLocale);
		}
		scrivo.close();
	}
	
	
	public void scrivereLog(String nuovoLog, String nome) throws IOException {  //Metodo che effettua l'aggiornamento del log nella finestra delle imformazioni e il salvataggio dello stesso su un file di testo
		String log="";
		File percorso = new File(nome+".txt"); //Percorso del file di log specificato con la data di creazione
		if(percorso.exists()==false) { //Se il file non esiste , ne creo uno vuoto
			FileOutputStream scrivere = new FileOutputStream(nome+".txt");
			PrintStream scrivo = new PrintStream(scrivere);
			scrivo.print("");
			scrivo.close();
		}
		String testo;
		BufferedReader input = new BufferedReader(new FileReader(percorso)); //Leggo il contenuto del file .txt
		StringBuffer buffer = new StringBuffer();
		while ((testo = input.readLine()) != null)
		buffer.append(testo + "\n");
		input.close();
		log=buffer.toString();
		FileOutputStream scrivereLog = new FileOutputStream(nome+".txt"); //Scrivo il file .txt
		PrintStream scrivolog = new PrintStream(scrivereLog);
		scrivolog.print(log+nuovoLog);
		scrivolog.close();
	}
	
}
