package Eccezioni;

@SuppressWarnings("serial")
public class ConnessioneAlSitoFallitaExceptionENG extends Exception{
	//La seguente eccezione viene lanciata solo se la connessione fallisce oppure se il sito specificato non e' raggiungibile e se la lingua scelta e' l'INGLESE
	
	
	public ConnessioneAlSitoFallitaExceptionENG() {  //Costruttore invocato se la lingua scelta e' l'inglese
		super("Unable to recover your IP, check the network connection or the URL into the settings...");
	}
	
}
