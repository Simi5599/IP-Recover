package Eccezioni;
@SuppressWarnings("serial")
public class ConnessioneAlSitoFallitaException extends Exception{
	//La seguente eccezione viene lanciata solo se la connessione fallisce oppure se il sito specificato non e' raggiungibile e se la ligua scelta e' l'ITALIANO
	
	
	
	public ConnessioneAlSitoFallitaException() {    //Costruttore invocato se la lingua scelta e' l'italiano
		super("Impossibile recuperare l'IP, controlla la connessione alla rete o il sito specificato nelle impostazioni...");
	}
	
	
	
}
