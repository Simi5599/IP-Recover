package Finestre;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Lingue.LanguageAssign;
import Programma.classeDiAvvio;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class finestraDelleImpostazioni extends JFrame {

	private JPanel contentPane;
	//Varie label che compongono il frame
	private JLabel lblSelezionareLaModalita = new JLabel("Selezionare la modalita' da impiegare...");
	private JLabel lblImpostazioniPerLa = new JLabel("Impostazioni per la modalita' automatica...");
	private JLabel lblTimeoutInSecondi = new JLabel("Timeout in secondi prima dell'aggiornamento successivo : ");
	private JLabel lblSecondi = new JLabel("secondi");
	private JLabel lblSitoWebDal = new JLabel("Sito WEB dal quale recuperare l'IP : ");
	//Fine label
	private JButton btnSalva = new JButton("Salva..."); //Il pulsante che consente di salvare i dati nel frame
	private JTextField TIME;  //TextField che consente di inserire il tempo in secondi relativo al countdown
	private JTextField URLSITO; //TextField che consente di inserire l'URL del sito adibito al recupero dell'IP (DA ELIMINARE E IMPOSTARE CON UNO PREDEFINITO PROPRIETARIO) 
	private ButtonGroup bg = new ButtonGroup(); //Buttongroup per i due pulsanti RADIO
	private JRadioButton rdbtnAutomatica = new JRadioButton("Automatica"); //Pulsante RADIO per la modalita' automatica
	private JRadioButton rdbtnManuale = new JRadioButton("Manuale"); //Pulsante RADIO per la modalita' manuale
	
	public void setUrlSito(String url) { //Imposta l'URL in base ad un dato prestabilito
		URLSITO.setText(url);
	}
	
	public String getUrlSito() { //Restituisce come STRING l'URL impostato nel frame
		return URLSITO.getText();
	}
	
	public void setTimeout(String timeInSeconds) { //Imposta il tempo in base ad un tempo prestabilito
		TIME.setText(timeInSeconds);
	}
	
	public String getTimeout() { //Restituisce come STRING il tempo inserito nel frame
		return TIME.getText();
	}
	
	public Boolean modalitaAutomaticaAbilitata() { //Metodo che serve a restituire la modalita' selezionata (TRUE se la modalita' automatica e' abilitata)
		Boolean risultato=null;
		if(rdbtnAutomatica.isSelected()) {
			risultato=true;
		}else {
			risultato=false;
		}
		return risultato;
	}
	
	public void assegnaValoreDiDefault() { //Metodo che assegna i valori di default all'avvio del programma
		setUrlSito("https://wtfismyip.com/text");
		setTimeout("3600");
		rdbtnManuale.doClick();
	}
	
	public void aggiornaInBaseAllaLingua() {  //Metodo che ricostruisce il frame una volta che viene rilevato un cambio di lingua
		LanguageAssign lingua = new LanguageAssign();	lingua=classeDiAvvio.getOggettoLingua();
		if(lingua.getLinguaAssegnata().equals("eng")) {  
		setTitle("Settings");
		lblSelezionareLaModalita.setText("Select the mode that you want to use...");
		lblImpostazioniPerLa.setText("Settings for the AUTO mode...");
		lblTimeoutInSecondi.setText("Time left before the next update : ");
		lblSecondi.setText("seconds");
		lblSitoWebDal.setText("Website used to recover the IP : ");
		btnSalva.setText("Save...");
		rdbtnAutomatica.setText("AUTO");
		rdbtnManuale.setText("MANUAL");
		}else {
			setTitle("Impostazioni");
			lblSelezionareLaModalita.setText("Selezionare la modalita' da impiegare...");
			lblImpostazioniPerLa.setText("Impostazioni per la modalita' automatica...");
			lblTimeoutInSecondi.setText("Timeout in secondi prima dell'aggiornamento successivo : ");
			lblSecondi.setText("secondi");
			lblSitoWebDal.setText("Sito WEB dal quale recuperare l'IP : ");
			btnSalva.setText("Salva...");
			rdbtnAutomatica.setText("Automatica");
			rdbtnManuale.setText("Manuale");
		}
	}
	
	//Creazione del frame
	
	public finestraDelleImpostazioni() {  //Costruttore
		setResizable(false);
		setTitle("Impostazioni");
		setBounds(100, 100, 559, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lblSelezionareLaModalita.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblSelezionareLaModalita.setBounds(135, 28, 313, 22);
		contentPane.add(lblSelezionareLaModalita);
		
		
		rdbtnAutomatica.setFont(new Font("Dialog", Font.PLAIN, 13));
		rdbtnAutomatica.setBounds(6, 62, 141, 23);
		contentPane.add(rdbtnAutomatica);
		
		
		rdbtnManuale.setFont(new Font("Dialog", Font.PLAIN, 13));
		rdbtnManuale.setBounds(399, 62, 141, 23);
		contentPane.add(rdbtnManuale);
		
		bg.add(rdbtnAutomatica);
		bg.add(rdbtnManuale);
		
		
		lblImpostazioniPerLa.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblImpostazioniPerLa.setBounds(122, 181, 313, 22);
		contentPane.add(lblImpostazioniPerLa);
		
		
		lblTimeoutInSecondi.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblTimeoutInSecondi.setBounds(6, 222, 402, 31);
		contentPane.add(lblTimeoutInSecondi);
		
		TIME = new JTextField();
		TIME.setFont(new Font("Dialog", Font.PLAIN, 13));
		TIME.setBounds(378, 224, 74, 26);
		contentPane.add(TIME);
		TIME.setColumns(10);
		
		
		lblSecondi.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblSecondi.setBounds(453, 229, 61, 16);
		contentPane.add(lblSecondi);
		
		
		btnSalva.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnSalva.setBackground(Color.LIGHT_GRAY);
		btnSalva.addActionListener(new ActionListener() { 
			
			private void salvaDatiSePremoIlPulsante() {
				//Preparativi
				operazioniFinestre opFinestre = new operazioniFinestre();	opFinestre=classeDiAvvio.getOggettoFinestre();
				opFinestre.modificaFinestraPrincipale(); //Ricostruisce il frame principale se rileva un cambio di modalita'
				opFinestre.aggiornaFinestraPrincipaleDopoModificheImpostazioni(); //Effettua le dovute modifiche al frame Principale dopo la sua ricostruzione (POSIZIONAMENTO DEL TIMER ECC)
			}
			@Override
			public void actionPerformed(ActionEvent e) {	//Effettua il salvataggio dei dati
				salvaDatiSePremoIlPulsante();
				}
			
		});
		btnSalva.setBounds(184, 281, 158, 29);
		contentPane.add(btnSalva);
		
		
		lblSitoWebDal.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblSitoWebDal.setBounds(6, 120, 231, 22);
		contentPane.add(lblSitoWebDal);
		
		URLSITO = new JTextField();
		URLSITO.setFont(new Font("Dialog", Font.PLAIN, 13));
		URLSITO.setBounds(233, 118, 307, 26);
		contentPane.add(URLSITO);
		URLSITO.setColumns(10);
	}
}
