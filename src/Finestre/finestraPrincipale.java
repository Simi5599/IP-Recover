	package Finestre;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Eccezioni.ConnessioneAlSitoFallitaException;
import Eccezioni.ConnessioneAlSitoFallitaExceptionENG;
import Lingue.LanguageAssign;
import Programma.classeDiAvvio;
import Programma.operazioniRete;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
@SuppressWarnings("serial")
public class finestraPrincipale extends JFrame {
	
	private final String datadicompilazione="24/04/2018 12:05";  //Stringa che contiene data ed ora di compilazione del programma
	private final String titoloIta="Compilata il "+datadicompilazione;  //Stringa che mostra il titolo in italiano del programma
	private final String titoloEng="Compiled on "+datadicompilazione;   //Stringa che mostra il titolo in inglese del programma
	private JPanel contentPane;
	private JLabel lblIpRecover = new JLabel("IP Recover 3.0");   //Label che mostra il nome del programma
	private JLabel lblDisattivataManuale = new JLabel("(Disattivata)"); //Label che indica se la modalita manuale e' disattivata
	private JLabel lblModalitaAutomatica = new JLabel("Modalita' Automatica"); //Label normale
	private JLabel lblDisattivataAutomatica = new JLabel("(Disattivata)"); //label che indica se la modalita' automatica e' disattivata
	private JLabel lblModalitaManuale = new JLabel("Modalita' Manuale");//label normale
	private JLabel lblInterrotto = new JLabel("(Interrotto)"); //Label che fa comparire la scritta INTERROTTO vicino alla label che indica il tempo di esecuzione
	private JLabel lblProssimoAggiornamento = new JLabel("Prossimo aggiornamento in :  00 secondi"); //Label che indica il tempo rimanente prima dell'aggiornamento successivo
	private JLabel lblTempoEsecuzione = new JLabel("Tempo di esecuzione 0:0:0"); //Label che indica il tempo di esecuzione
	private JLabel lblIpPubblico = new JLabel("IP Pubblico :"); //Label che precede il valore dell'IP Pubblico
	private JLabel lblIpPrivato = new JLabel("IP Privato :"); //Label che precede il valore dell'IP Privato
	private JLabel lblvaloreipprivato = new JLabel("N/A");    //Label che contiene il valore dell'IP Privato 
	private JLabel lblvaloreippubblico = new JLabel("N/A"); 	//Label che contiene il valore dell'IP Pubblico
	private JButton btnImpostazioni = new JButton("Impostazioni");	//Un pulsante che apre le impostazioni
	private JButton btnPulisci = new JButton("Pulisci...");		//Un pulsante che imposta le label che contengono i valori degli IP ai valori di default (N/A)
	private JButton btnAvvia = new JButton("Avvia..."); //Pulsante Avvia in modalita automatica
	private JButton btnInterrompi = new JButton("Interrompi...");//Pulsante Interrompi in modalita' automatica
	private JButton btnRecupera = new JButton("Recupera..."); //Pulsante Recupera in modalita' manuale
	private JButton btnLingua = new JButton("Switch to english");		//Il pulsante che consente di cambiare lingua al programma
	private operazioniRete opRETE = new operazioniRete(); //Oggetto per eseguire le operazioni di rete
	
	
	
	//Metodi per l'aggiornamento del frame principale dopo un cambio di lingua e per il mantenimento dei dati
	
	public void aggiornaInBaseAllaLingua() {  //Metodo che ricorstruisce il frame dopo un cambio lingua rilevato
		LanguageAssign lingua = new LanguageAssign();	lingua=classeDiAvvio.getOggettoLingua();
		if(lingua.getLinguaAssegnata().equals("eng")) {
			setTitle(titoloEng);
			lblDisattivataManuale.setText("(Disabled)");
			lblModalitaAutomatica.setText("AUTO Mode");
			lblDisattivataAutomatica.setText("(Disabled)");
			lblModalitaManuale.setText("Manual Mode");
			lblInterrotto.setText("(Interrupted)");
			lblProssimoAggiornamento.setText("Next update in :  00 seconds");
			lblTempoEsecuzione.setText("Execution time 0:0:0");
			lblIpPubblico.setText("Public IP :");
			lblIpPrivato.setText("Private IP :");
			btnImpostazioni.setText("Settings");
			btnPulisci.setText("Clear...");
			btnAvvia.setText("Start...");
			btnInterrompi.setText("Stop...");
			btnRecupera.setText("Get Data...");
			btnLingua.setText("Mostra in italiano");
		}else {
			setTitle(titoloIta);
			lblDisattivataManuale.setText("(Disattivata)");
			lblModalitaAutomatica.setText("Modalita' Automatica");
			lblDisattivataAutomatica.setText("(Disattivata)");
			lblModalitaManuale.setText("Modalita' Manuale");
			lblInterrotto.setText("(Interrotto)");
			lblProssimoAggiornamento.setText("Prossimo aggiornamento in :  00 secondi");
			lblTempoEsecuzione.setText("Tempo di esecuzione 0:0:0");
			lblIpPubblico.setText("IP Pubblico :");
			lblIpPrivato.setText("IP Privato :");
			btnImpostazioni.setText("Impostazioni");
			btnPulisci.setText("Pulisci...");
			btnAvvia.setText("Avvia...");
			btnInterrompi.setText("Interrompi...");
			btnRecupera.setText("Recupera...");
			btnLingua.setText("Switch to english");
	}
	}
	
	//Metodi per l'attivazione/disattivazione dei pulsanti in base allo stato della modalita automatica
		
	public void enableStartButton() {
		btnAvvia.setEnabled(true);
		btnInterrompi.setEnabled(false);
	}
	
	public void disableStartButton() {
		btnAvvia.setEnabled(false);
		btnInterrompi.setEnabled(true);
	}
	
	
	
	//Metodi e variabili per conservare lo stato di interrotto vicino al timer di esecuzione dopo un aggiornamento del frame Principale
	
	private Boolean MostrarelblInterrotta=false;
	
	public void effettuaSalvataggioStatolblInterruzione(Boolean dato) {
		MostrarelblInterrotta=dato;
	}
	
	public Boolean controllareSeRipristinareLblInterruzione() {
		return MostrarelblInterrotta;
	}
	
	
	
	//Metodi e variabili per identificare la modalita di esecuzione del thread principale
	
	private Boolean isAutoModeEnabled=false;
	
	public Boolean getAutoModeState() {
		return isAutoModeEnabled;
	}
	
	public void setAutoModeState(Boolean par) {
		isAutoModeEnabled=par;
	}
	
	//Metodi per l'aggiornamento del frame principale in base alla modalita' scelta
	
	public void impostaModalitaAutomatica() {  //Operazioni da eseguire quando si imposta la modalita' automatica

		lblDisattivataManuale.setVisible(true); //Mostrare il DISATTIVATO sulla modalita' manuale
		lblProssimoAggiornamento.setVisible(true); //Mostrare la label relativa al countdown
		lblDisattivataAutomatica.setVisible(false);//Nasconde il DISATTIVATO sulla modalita' automatica
		btnAvvia.setVisible(true); //Mostra il pulsante avvia
		btnInterrompi.setVisible(true); //mostra il pulsante interrompi
		btnRecupera.setVisible(false); //Nasconde il pulsante recupera (MOD: Manuale)
		lblTempoEsecuzione.setVisible(true); //Mostra la label relativa al conteggio del tempo di esecuzione in MOD:Auto
		
		if(controllareSeRipristinareLblInterruzione()==false) { //Se in precedenza non avevo una label INTERROTTO vicino al timer...
		lblInterrotto.setVisible(false); //...non la mostro
		}else {
			lblInterrotto.setVisible(true); //...altrimenti la rendo visibile
		}
	}
	
	public void impostaModalitaManuale() { //Operazioni da eseguire quando si imposta la modalita' manuale
		
		lblDisattivataManuale.setVisible(false); //Nascondo il DISATTIVATO sulla modalita manuale
		lblProssimoAggiornamento.setVisible(false); //Nascondo la label relativa al countdown
		lblDisattivataAutomatica.setVisible(true); //Mostro il disattivato sulla modalita' automatica
		btnAvvia.setVisible(false); //Nasconde il pulsante avvia
		btnInterrompi.setVisible(false); //Nasconde il pulsante interrompi
		btnRecupera.setVisible(true); //Mostra il pulsante recupera
		lblTempoEsecuzione.setVisible(false); //Nasconde la label relativa al conteggio del tempo di esecuzione in MOD: Auto
		lblInterrotto.setVisible(false); //Nasconde la label INTERROTTO vicino al tempo di esecuzione
	}
	
	
	
	
	//Metodi per l'aggiornamento dei parametri relativi al tempo
	
	public void setTempoRimanente(int secondi) {
		LanguageAssign lingua = new LanguageAssign();	lingua=classeDiAvvio.getOggettoLingua();
		if(lingua.getLinguaAssegnata().equals("eng")) {
		lblProssimoAggiornamento.setText("Next update in :  "+String.valueOf(secondi)+" seconds");
		}else {
		lblProssimoAggiornamento.setText("Prossimo aggiornamento in :  "+String.valueOf(secondi)+" secondi");
		}
	}
	
	public void setTempoEsecuzione(int ore,int minuti,int secondi) {
		LanguageAssign lingua = new LanguageAssign();	lingua=classeDiAvvio.getOggettoLingua();
		if(lingua.getLinguaAssegnata().equals("eng")) {
		lblTempoEsecuzione.setText("Execution time "+String.valueOf(ore)+":"+String.valueOf(minuti)+":"+String.valueOf(secondi));	
		}else {
		lblTempoEsecuzione.setText("Tempo di esecuzione "+String.valueOf(ore)+":"+String.valueOf(minuti)+":"+String.valueOf(secondi));
	}
	}
	
	
	
	
	//Metodi per l'aggiornamento delle label relative agli IP
	
	public void aggiornaLabelIpPrivato(String IP) {
		lblvaloreipprivato.setText(IP);
	}
	
	public void aggiornaLabelIpPubblico(String IP) {
		lblvaloreippubblico.setText(IP);
	}
	
	public void impostareLabelIpComeDefault() {
		lblvaloreipprivato.setText("N/A");
		lblvaloreippubblico.setText("N/A");
		operazioniFinestre opFinestre = new operazioniFinestre();		opFinestre=classeDiAvvio.getOggettoFinestre();
		finestraDelleInformazioni frameConsole = new finestraDelleInformazioni();	frameConsole=opFinestre.getFinestraConsole();
		LanguageAssign lingue = new LanguageAssign();	lingue=classeDiAvvio.getOggettoLingua();
		if(lingue.getLinguaAssegnata().equals("eng")) {
		frameConsole.setStatoAsMessaggio("IP have been cleaned successfully!");	
		}else {
		frameConsole.setStatoAsMessaggio("Ho rimosso gli IP dalla schermata principale correttamente!");
		}
	}
	

	
	//Creazione del frame

	public finestraPrincipale() {  //Costruttore
		setTitle(titoloIta); //Preimpostato in ita
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lblIpRecover.setForeground(new Color(102, 153, 255));
		lblIpRecover.setFont(new Font("Dialog", Font.ITALIC, 20));
		lblIpRecover.setBounds(16, 17, 309, 52);
		contentPane.add(lblIpRecover);
		
		
		btnImpostazioni.setBackground(Color.LIGHT_GRAY);
		btnImpostazioni.addActionListener(new ActionListener() {
			
			private void apriLaFinestraDelleImpostazioniQuandoPremoIlPulsante() {	
				operazioniFinestre opFinestre = new operazioniFinestre();	opFinestre=classeDiAvvio.getOggettoFinestre();
				opFinestre.mostraFrameImpostazioni(); //mostro la finestra
			}
			public void actionPerformed(ActionEvent e) { 	//Apre la finestra delle impostazioni...
					apriLaFinestraDelleImpostazioniQuandoPremoIlPulsante();
					}
			
		});
		btnImpostazioni.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnImpostazioni.setBounds(511, 33, 117, 29);
		contentPane.add(btnImpostazioni);
		
		
		lblModalitaManuale.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblModalitaManuale.setBounds(16, 104, 167, 29);
		contentPane.add(lblModalitaManuale);
		
		
		lblDisattivataManuale.setForeground(new Color(255, 51, 51));
		lblDisattivataManuale.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblDisattivataManuale.setBounds(197, 104, 128, 29);
		contentPane.add(lblDisattivataManuale);
		
		
		lblModalitaAutomatica.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblModalitaAutomatica.setBounds(16, 274, 167, 29);
		contentPane.add(lblModalitaAutomatica);
		
		
		lblDisattivataAutomatica.setForeground(new Color(255, 51, 51));
		lblDisattivataAutomatica.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblDisattivataAutomatica.setBounds(197, 274, 128, 29);
		contentPane.add(lblDisattivataAutomatica);
		btnAvvia.setBackground(new Color(0, 255, 153));
		btnAvvia.addActionListener(new ActionListener() {
			
			private void avviareLaModalitaAutomatica() {

					//Prerequisiti
					operazioniFinestre opFinestre = new operazioniFinestre();	opFinestre=classeDiAvvio.getOggettoFinestre();
					finestraDelleInformazioni frameConsole = new finestraDelleInformazioni();	frameConsole=opFinestre.getFinestraConsole();
					//inizio
					LanguageAssign lingua = new LanguageAssign();	lingua=classeDiAvvio.getOggettoLingua();
					if(lingua.getLinguaAssegnata().equals("eng")) {
						frameConsole.setStatoAsMessaggio("Preparing to start AUTO mode...");
					}else {
						frameConsole.setStatoAsMessaggio("Preparazione all'avvio della modalita' automatica...");
					}
					setAutoModeState(true); //Faccio partire il thread
					//Operazioni grafiche
					lblInterrotto.setVisible(false); //Nascondo la label INTERROTTO vicino al tempo di esecuzione
					effettuaSalvataggioStatolblInterruzione(false); //Specifico che non devo salvare lo stato della label INTERROTTO in caso di aggiornamenti grafici
					btnLingua.setEnabled(false);
					btnImpostazioni.setEnabled(false);
					if(lingua.getLinguaAssegnata().equals("eng")) {
					frameConsole.setStatoAsMessaggio("AUTO mode started succesfully!");	
					}else {
					frameConsole.setStatoAsMessaggio("Modalita' automatica avviata con successo!");
					}
				
			}
			public void actionPerformed(ActionEvent e) { //Avvia la modalita' di recupero AUTOMATICA
				avviareLaModalitaAutomatica();
			}
			
		});
		
		
		btnAvvia.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnAvvia.setBounds(50, 341, 167, 74);
		contentPane.add(btnAvvia);
		btnInterrompi.setBackground(new Color(255, 51, 102));
		btnInterrompi.addActionListener(new ActionListener() {
			
			private void interrompiLaModalitaAutomatica() {
					//Prerequisiti
					operazioniFinestre opFinestre = new operazioniFinestre();	opFinestre=classeDiAvvio.getOggettoFinestre();
					finestraDelleInformazioni frameConsole = new finestraDelleInformazioni();	frameConsole=opFinestre.getFinestraConsole();
					LanguageAssign lingua = new LanguageAssign();	lingua=classeDiAvvio.getOggettoLingua();
					//inizio
					setAutoModeState(false);
					//Operazioni grafiche
					lblInterrotto.setVisible(true); //Mostro la label INTERROTTO vicino al tempo di esecuzione
					effettuaSalvataggioStatolblInterruzione(true); //Specifico che devo salvare lo stato della label INTERROTTO in caso di aggiornamenti grafici
					btnLingua.setEnabled(true);
					btnImpostazioni.setEnabled(true);
					if(lingua.getLinguaAssegnata().equals("eng")) {
						frameConsole.setStatoAsMessaggio("AUTO mode has been interrupted!");
					}else {
						frameConsole.setStatoAsMessaggio("Modalita' automatica interrotta!");
					}
				
			}
			public void actionPerformed(ActionEvent e) { //Spegne la modalita' di recupero AUTOMATICA
				interrompiLaModalitaAutomatica();
			}
		});
		
		
		btnInterrompi.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnInterrompi.setBounds(395, 341, 167, 74);
		contentPane.add(btnInterrompi);
		btnRecupera.setBackground(Color.LIGHT_GRAY);
		btnRecupera.addActionListener(new ActionListener() {
			
			private void effettuareRecuperoSingoloDegliIndirizzi() {
				try {
					opRETE.recuperaIP(); //recupero l'IP
					}catch(ConnessioneAlSitoFallitaException csfe) {
						JOptionPane.showMessageDialog(null, csfe.getMessage()); //Messaggio di errore in caso di connessione fallita
					}catch(ConnessioneAlSitoFallitaExceptionENG csfeeng) {
						JOptionPane.showMessageDialog(null, csfeeng.getMessage()); //Messaggio di errore in caso di connessione fallita
					}
			}
			public void actionPerformed(ActionEvent e) { //Effettua un singolo recupero dell'IP
				effettuareRecuperoSingoloDegliIndirizzi();
			}
			
		});
		
		
		btnRecupera.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnRecupera.setBounds(224, 166, 167, 74);
		contentPane.add(btnRecupera);
		
		
		lblProssimoAggiornamento.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblProssimoAggiornamento.setBounds(16, 435, 320, 26);
		contentPane.add(lblProssimoAggiornamento);
		
		JLabel lblNewLabel = new JLabel("Simone Zurlo");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 10));
		lblNewLabel.setBounds(547, 523, 96, 16);
		contentPane.add(lblNewLabel);
		
		
		lblTempoEsecuzione.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTempoEsecuzione.setBounds(6, 517, 199, 26);
		contentPane.add(lblTempoEsecuzione);
		lblInterrotto.setForeground(new Color(255, 51, 51));
		lblInterrotto.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblInterrotto.setBounds(190, 520, 96, 21);
		
		contentPane.add(lblInterrotto);
		
		JButton btnConsole = new JButton("Console ");
		btnConsole.addActionListener(new ActionListener() {
			
			private void apriLaFinestraDelleInformazioniQuandoPremoIlPulsante() {
				operazioniFinestre opFinestre = new operazioniFinestre();	opFinestre=classeDiAvvio.getOggettoFinestre();
				opFinestre.mostraFrameConsole();
			}
			public void actionPerformed(ActionEvent e) {
				apriLaFinestraDelleInformazioniQuandoPremoIlPulsante();
			}
			
		});
		btnConsole.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnConsole.setBackground(Color.LIGHT_GRAY);
		btnConsole.setBounds(409, 33, 103, 29);
		contentPane.add(btnConsole);
		
		
		lblIpPrivato.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblIpPrivato.setBounds(147, 482, 83, 26);
		contentPane.add(lblIpPrivato);
		
		
		lblIpPubblico.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblIpPubblico.setBounds(395, 482, 96, 26);
		contentPane.add(lblIpPubblico);
		
		
		btnPulisci.addActionListener(new ActionListener() {
			
			private void impostaGliIpAlValoreDiDefault() {
				impostareLabelIpComeDefault();
			}
			public void actionPerformed(ActionEvent e) {
				impostaGliIpAlValoreDiDefault();
			}
			
		});
		btnPulisci.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnPulisci.setBackground(new Color(255, 0, 51));
		btnPulisci.setBounds(18, 481, 117, 29);
		contentPane.add(btnPulisci);
		
		
		btnLingua.addActionListener(new ActionListener() {
			
			private void scambiaLingueQuandoPremoIlPulsante() {
				LanguageAssign lingua = new LanguageAssign();	lingua=classeDiAvvio.getOggettoLingua();
				operazioniFinestre opFinestre = new operazioniFinestre();		opFinestre=classeDiAvvio.getOggettoFinestre();
				finestraDelleInformazioni frameConsole = new finestraDelleInformazioni();	frameConsole=opFinestre.getFinestraConsole();
				lingua.switchLanguages();
				lingua.ricostruisciProgrammaInBaseAllaLinguaSelezionata();
				if(lingua.getLinguaAssegnata().equals("eng")) {
					frameConsole.setStatoAsMessaggio("Language has been changed to ENGLISH...");
				}else {
					frameConsole.setStatoAsMessaggio("Lingua modificata in ITALIANO...");
				}
			}
			public void actionPerformed(ActionEvent e) {
				scambiaLingueQuandoPremoIlPulsante();
			}
			
		});
		btnLingua.setBackground(Color.LIGHT_GRAY);
		btnLingua.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnLingua.setBounds(419, 62, 209, 29);
		contentPane.add(btnLingua);
		
		
		lblvaloreipprivato.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblvaloreipprivato.setBounds(225, 487, 140, 16);
		contentPane.add(lblvaloreipprivato);
		
		
		lblvaloreippubblico.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblvaloreippubblico.setBounds(487, 487, 141, 16);
		contentPane.add(lblvaloreippubblico);
		enableStartButton();	//Configurazione di avvio (PULSANTE START ABILITATO)
	}
}
