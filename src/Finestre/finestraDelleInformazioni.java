package Finestre;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Lingue.LanguageAssign;
import Programma.classeDiAvvio;
import Programma.scritturaSuFile;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.io.IOException;
import java.util.Calendar;
import javax.swing.ScrollPaneConstants;
@SuppressWarnings("serial")
public class finestraDelleInformazioni extends JFrame {

	private JPanel contentPane;
	private final JTextArea stato = new JTextArea();  //Area di testo NON SCRIVIBILE che mostra in tempo reale le operazioni che vengono effettuate nel programma
	
	public String getStato() {  //Funzione che ritorna l'intero contenuto dell'area di testo 'stato'
		return stato.getText();
	}
	
	public void setStatoAsMessaggio(String msg) {   //Metodo che ci consente di registrare una nuova azione nell'area di testo, inoltre viene effettuata anche una scrittura dei dati...(VEDI LA CLASSE scritturaSuFile per maggiori dettagli)
		scritturaSuFile opScrittura = new scritturaSuFile();
		try {
		//Raccoglimento della data	
		Calendar cal = Calendar.getInstance();
		int giorno = cal.get(Calendar.DAY_OF_MONTH), mese = (cal.get(Calendar.MONTH)+1), anno= cal.get(Calendar.YEAR), ora=cal.get(Calendar.HOUR_OF_DAY), minuti= cal.get(Calendar.MINUTE), secondi=cal.get(Calendar.SECOND);
		String DATA=String.valueOf(giorno)+"/"+String.valueOf(mese)+"/"+String.valueOf(anno)+" "+String.valueOf(ora)+":"+String.valueOf(minuti)+":"+String.valueOf(secondi);
		//Fine raccoglimento data
		String vecchioMessaggio=stato.getText();  //Stringa che contiene il vecchio log
		String nuovoMessaggio=vecchioMessaggio+"\r\n"+DATA+" :     "+msg;  //Stringa che contiene il nuovo ed il vecchio messaggio
		String nuovoMessaggioPerLog="\r\n"+DATA+" :     "+msg;  //Stringa che contiene il nuovo ed il vecchio messaggio ottimizzata per essere scritta su un file .txt
		stato.setText(nuovoMessaggio); //Aggiorno l'area di testo
		//Scrittura LOG
		String NOMEPERLOG="Log "+String.valueOf(giorno)+"-"+String.valueOf(mese)+"-"+String.valueOf(anno); //Una stringa che contiene il nome da assegmare al file .txt che contiene il log
		opScrittura.scrivereLog(nuovoMessaggioPerLog, NOMEPERLOG);  //Scrivo il log su un file di testo
		}catch(IOException ioe) {
			LanguageAssign lingua = new LanguageAssign();  lingua=classeDiAvvio.getOggettoLingua();
			if(lingua.getLinguaAssegnata().equals("eng")) {
				JOptionPane.showMessageDialog(null, "Warning, error writing the log file... The application is closing");
			}else {
			JOptionPane.showMessageDialog(null, "Attenzione, errore nella scrittura del log... Il programma si chiudera' ");
			}
			System.exit(1);
		}
	}
	

    //Creazione del frame 
	
	public finestraDelleInformazioni() {
		setTitle("Console");
		setBounds(100, 100, 587, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(6, 6, 563, 365);
		contentPane.add(scrollPane);
		stato.setFont(new Font("Dialog", Font.PLAIN, 13));
		stato.setEditable(false);
		
		scrollPane.setViewportView(stato);
	}
}
