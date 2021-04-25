package Programma;
import Finestre.operazioniFinestre;
import Lingue.LanguageAssign;
import Thread.threadRecuperoIPEdIncrementoTempoEsecuzione;
public class classeDiAvvio {

	private static operazioniFinestre opFinestre = new operazioniFinestre();
	private static LanguageAssign objlingua = new LanguageAssign();
	
	
	public static operazioniFinestre getOggettoFinestre() { //Funzione che mi consente di usare l'oggetto opfinestre in altri casi SENZA DOVER ISTANZIARE classeDiAvvio
		return opFinestre;
	}
	
	public static LanguageAssign getOggettoLingua() {  //Funzione che mi consente di usare l'oggetto objlingua in altri casi SENZA DOVER ISTANZIARE classeDiAvvio
		return objlingua;
	}
	
	public static void main(String [] args) {   //Metodo main
		//Vai nella classe operazioniFinestre per dettagli sui metodi
		opFinestre.mostraFramePrincipale();			//Mostro il framePrincipale
		opFinestre.assegnareValoreDiDefaultAlleImpostazioni(); //Assegno alle impostazioni determinati valori PREDEFINITI
		opFinestre.assegnareValoreDiDefaultAlTimerDiEsecuzione(); //Assegno un tempo di default al timer di esecuzione della modalita automatica
		opFinestre.modificaFinestraPrincipale();  //In base alla modalita' scelta , la finestra principale viene ricostruita
		opFinestre.primoRecuperoDaImpostazioni(); //Effettua un recupero dati dalle impostazioni e imposta i parametri correttamente
		threadRecuperoIPEdIncrementoTempoEsecuzione thread1 = new threadRecuperoIPEdIncrementoTempoEsecuzione();	thread1.start();
		//Attivazione thread principale (RECUPERO IP)
		
	}
}