package it.ing.sw.v3;

import java.io.Serializable;
import java.time.DateTimeException;
import java.util.Vector;

import it.ing.sw.*;
import it.ing.sw.v1.Anagrafica;
import it.ing.sw.v1.AnagraficaFruitori;
import it.ing.sw.v1.AnagraficaOperatori;
import it.ing.sw.v1.Fruitore;
import it.ing.sw.v1.Menu;
import it.ing.sw.v1.Operatore;
import it.ing.sw.v1.Utente;
import it.ing.sw.v2.Archivio;
import it.ing.sw.v2.Categoria;
import it.ing.sw.v2.InserimentoRisorsa;
import it.ing.sw.v2.Libro;
import it.ing.sw.v2.Risorsa;
import it.ing.sw.v2.SottoCategoria;

import java.time.*;

/**
 * Questa classe permette una corretta gestione dell'uso dei menu'. E' essenzialmente suddivisa in tre parti:
 * 1 - Elenco delle costanti che costituiscono le intestazioni dei menu' e le varie opzioni che li compongono
 * 2 - Metodi ausiliari per la gestione delle funzionalita' basilari del software (iscrizione, accesso)
 * 3 - Metodo logicaMenu per la realizzazione delle connessioni tra i vari menu'
 */
public class GestoreMenu implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String SALUTO_INIZIALE = "Benvenuto nell'applicazione per la gestione di risorse multimediali\n";
    public static final String SALUTO_FINALE = "Arrivederci, alla prossima!\n";
    public static final String INTESTAZIONE_A = "IN QUALE MODALITA VUOI ACCEDERE?";
	public static final String [] OPZIONI_A = {"Fruitore", "Operatore", "Esci"};
	public static final String INTESTAZIONE_B = "SCEGLI UN'OPZIONE";
	public static final String [] OPZIONI_B = {"Iscriviti come nuovo fruitore", "Accedi", "Indietro"};
	public static final String INTESTAZIONE_C = "ACCESSO FRUITORE";
	public static final String [] OPZIONI_C = {"Inserisci username e password", "Indietro"};
	public static final String INTESTAZIONE_D= "COSA DESIDERI FARE?";
	public static final String [] OPZIONI_D = {"Rinnova iscrizione", "Visualizza profilo", "Logout"};
	public static final String INTESTAZIONE_E = "ACCESSO OPERATORE";
	public static final String [] OPZIONI_E = {"Inserisci username e password", "Indietro"};
	public static final String INTESTAZIONE_F = "COSA DESIDERI FARE?";
	public static final String [] OPZIONI_F = {"Visualizza anagrafica fruitori", "Visualizza archivio", "Aggiungi risorsa", "Rimuovi risorsa", "Ricerca risorsa", "Valuta disponibilita' risorsa", "Logout"};
    
	public static final String INS_NOME = "Inserisci il tuo nome: ";
    public static final String INS_COGNOME = "Inserisci il tuo cognome: ";
    public static final String INS_USERNAME = "Inserisci il tuo username: ";
    public static final String INS_PASSWORD = "Inserisci la tua password: ";
    public static final String INS_GIORNO_NASCITA = "Inserisci il tuo giorno di nascita (in cifre): ";
    public static final String INS_MESE_NASCITA = "Inserisci il tuo mese di nascita (in cifre): ";
    public static final String INS_ANNO_NASCITA = "Inserisci il tuo anno di nascita (indicare 4 cifre): ";

    public static final String ISCRIZIONE_OK = "Complimenti, iscrizione avvenuta con successo!\n";
    public static final String ISCRIZIONE_NON_OK = "Non e' stato possibile iscrivere alcun utente\n";
    public static final String ISCRIZIONE_NON_OK_OMONIMIA_FRUITORI = "ATTENZIONE! Le credenziali inserite non sono valide poiche' gia' in uso.\n";
    public static final String ISCRIZIONE_NON_OK_STESSO_USERNAME = "ATTENZIONE! Lo username indicato non e' valido poiche' gia' in uso.\n";
    public static final String ISCRIZIONE_NON_OK_MAGGIORE_ETA = "ATTENZIONE! L'utente indicato non puo' iscriversi in quanto non e' maggiorenne.\n";

    public static final String RINNOVO_OK = "Il rinnovo dell'iscrizione e' avvenuto con successo.\n";
    public static final String RINNOVO_NON_OK = "Non e' possibile effettuare il rinnovo dell'iscrizione.\n";
    
    public static final String USERNAME = "Username: ";
	public static final String PASSWORD = "Password: ";
	public static final String CREDENZIALI_ERRATE = "ATTENZIONE! Lo username e/o la password non sono validi.\n";
	public static final String DATA_DI_NASCITA_ERRATA = "ATTENZIONE! La data di nascita inserita non e' valida.\n";
	
	public static final String RICHIESTA_PROSECUZIONE = "Si desidera riprovare? (S/N)\n";
	public static final String ERRORE = "Si e' verificato un errore\n";

	public static final String CONTENUTO_ARC = "L'archivio contiene le seguenti categorie:\n%s";
	public static final String CONTENUTO_CAT = "La categoria %s contiene queste risorse:\n%s";
	public static final String CONTENUTO_SOTTO = "La sottocategoria %s contiene queste risorse:\n%s"; 
	
	public static final String OP_SUCCESSO = "L'operazione e' avvenuta con successo\n";
    public static final String OP_NO_SUCCESSO_AGGIUNTA = "Attenzione! La risorsa e' gia'  presente nell'archivio oppure la risorsa non e' compatibile con la sottocategoria dove si vuole inserirla\n";
    public static final String OP_NO_SUCCESSO_PRESTITO = "Attenzione! La risorsa indicata non e' presente in archivio e/o non e' disponibile\n";
    public static final String OP_NO_SUCCESSO_PROROGA = "Attenzione! Non e' stato possibile effettuare la proroga richiesta\n";

    public static final String INS_NUMERO_CAT = "Inserisci il numero della categoria a cui aggiungere/rimuovere la risorsa:";
    public static final String INS_IN_SOTTO = "La categoria %s contiene queste sottocategorie:\n%s";
    public static final String INS_PROCEDERE_SOTTO = "Vuoi proseguire nell'inserimento/rimozione della risorsa in/da una sottocategoria? (S/N)\n";
    public static final String INS_NUMERO_SOTTOC =  "Inserisci il numero della sottocategoria a cui aggiungere/rimuovere la risorsa:";
    public static final String INS_NUMERO_RISORSA = "Inserisci il numero della risorsa da rimuovere:";    
    public static final String INS_PROCEDERE_RISORSA = "Vuoi proseguire nella rimozione della risorsa? (S/N)\n";
        
    public static final String INS_NUMERO_CAT_PRESTITO = "Inserisci il numero della categoria di cui vuoi richiedere un prestito:";
    public static final String INS_NUMERO_SOTTOC_PRESTITO =  "Inserisci il numero della sottocategoria in cui cercare la risorsa:";
    public static final String INS_NUMERO_RISORSA_PRESTITO = "Inserisci il numero della risorsa da richiedere in prestito:";      
    public static final String INS_PROCEDERE_PRESTITO_SCELTA_SOTTO = "Vuoi proseguire nella scelta della sottocategoria? (S/N)\n";
    public static final String INS_PROCEDERE_PRESTITO_SCELTA_RISORSA = "Vuoi proseguire nella scelta della risorsa? (S/N)\n";

    public static final String INS_NUMERO_PRESTITO_PROROGA = "Inserisci il numero del prestito di cui vuoi richiedere la proroga:";      
    
    public static final String INS_TITOLO_RISORSA = "Inserisci il titolo: ";      
    public static final String INS_AUTORE_RISORSA = "Inserisci il nome di almeno un autore: ";      
    public static final String INS_ANNOPUB_RISORSA = "Inserisci l'anno di pubblicazione: ";      
    public static final String INS_CASAED_RISORSA = "Inserisci la casa editrice: "; 
    
	public static final String AVVIO_RICERCA_RISORSE= "Come intendi ricercare la risorsa?\n1-Per titolo\n2-Per autore\n3-Per anno di pubblicazione\n4-Per casa editrice\nDigitare un numero";
	public static final String INTESTAZIONE_RICERCA_RISORSE = "Elenco delle risorse trovate: \n";
	public static final String RICHIESTA_DIGITAZIONE_VALUTAZIONE = "Digitare il numero della risorsa scelta: ";
	public static final String RISORSA_DISPONIBILE = "La risorsa indicata e' disponibile\n";
	public static final String RISORSA_NON_DISPONIBILE = "La risorsa indicata non e' disponibile\n";

	public static final int NUM_MINIMO = 1;

    /**
	 * Metodo per l'aggiunta di un nuovo fruitore all'elenco dei fruitori gia' presenti all'interno di af.
	 * Vengono effettuati dei controlli sulla correttezza della data di nascita inserita e sulla possibile presenza di fruitori gia' iscritti in possesso delle medesime credenziali indicate
	 * 
	 * Pre : af != null
	 * Pre : af.elenco != null
	 * 
	 * @param af : oggetto di tipo AnagraficaFruitori contenente l'elenco dei fruitori presenti ed i metodi per l'esecuzione dei vari controlli
	 */
	public void iscrizione(AnagraficaFruitori af)
	{
		String nome = "";
		String cognome = "";
		String use = "";
		String pwd = "";
		int giorno = 0;
		int mese = 0;
		int anno = 0;

		boolean end = true;
		
		boolean ins_nome = true;
		boolean ins_cognome = true;
		boolean ins_use = true;
		boolean ins_pwd = true;
		boolean ins_data = true;
		
		/**
		 * Ciclo globale per l'inserimento di un nuovo fruitore nel sistema in accordo con le condizioni indicate.
		 * E' possibile suddividere tale ciclo in 4 parti:
		 * 1 - Inserimento dei parametri richiesti
		 * 2 - Controllo sulla correttezza lessicale della data di nascita inserita
		 * 3 - Controlli sulle condizioni necessarie per l'iscrizione
		 * 4 - Completamento iscrizione o richiesta di perfezionamento della stessa
		 */
	    do
	    {
	    	/**
	    	 * Inserimento parametri
	    	 */
	    	if(ins_nome)
	    	{
				nome = InputDati.leggiStringaNonVuota(INS_NOME);
	    	}
	    	
	    	if(ins_cognome)
	    	{
				cognome = InputDati.leggiStringaNonVuota(INS_COGNOME);
	    	}
	    	
	    	if(ins_use)
	    	{
				use = InputDati.leggiStringaNonVuota(INS_USERNAME);
	    	}
	    	
	    	if(ins_pwd)
	    	{
				pwd = InputDati.leggiStringaNonVuota(INS_PASSWORD);
	    	}
	    	
			Fruitore f = null;	
			boolean exc = false;			
			end = true;
			
			/**
			 * Il controllo per la correttezza della data di nascita inserita viene gestito autonomamente dalla classe LocalDate.
			 * Nel caso in cui quest'ultima generi un'eccezione, e dunque la data inserita non sia lessicalmente corretta, viene modificata un'opportuna
			 * variabile booleana che impedisce la fuoriuscita dal ciclo do-while fintanto che non viene digitata una data valida
			 */
			while(!exc) {
			
				try 
				{
					if(ins_data)
					{
						giorno = InputDati.leggiIntero(INS_GIORNO_NASCITA);
						mese = InputDati.leggiIntero(INS_MESE_NASCITA);
						anno = InputDati.leggiIntero(INS_ANNO_NASCITA);
					}
					
					f = new Fruitore(nome, cognome, anno, mese, giorno, use, pwd);
					
					exc = true;
				}
				catch(DateTimeException e)
				{
					System.out.println(DATA_DI_NASCITA_ERRATA);
				}
				
			};
			
			ins_nome = false;
			ins_cognome = false;
			ins_use = false;
			ins_pwd = false;
			ins_data = false;

			/**
			 * I metodi di controllo verificano se non vi sono casi di omonimia tra diversi fruitori, se non vi sono casi di condivisione di username
			 * e se l'utente e' maggiorenne. In caso di inesattezze vengono reimpostati i parametri di inserimento e viene impedita la fuoriuscita dal ciclo globale
			 */
			if(af.verificaOmonimiaFruitori(f.getNome(), f.getCognome(), f.getDataDiNascita()) == true)
			{
				System.out.println(ISCRIZIONE_NON_OK_OMONIMIA_FRUITORI);
				ins_nome = true;
				ins_cognome = true;
				ins_data = true;
				end = false;
			}
			
			if(af.verificaStessoUsername(f.getUsername()) == true)
			{
				System.out.println(ISCRIZIONE_NON_OK_STESSO_USERNAME);
				ins_use = true;
				end = false;
			}
			
			if(Period.between(f.getDataDiNascita(), LocalDate.now()).getYears() < 18)
			{
				System.out.println(ISCRIZIONE_NON_OK_MAGGIORE_ETA);
				ins_nome = true;
				ins_cognome = true;
				ins_data = true;
				end = false;
			}
			
			/**
			 * Se non sono stati segnalati errori, l'iscrizione si conclude con successo.
			 * Altrimenti, a meno che l'utente non esprima la volonta' di terminare l'operazione, si procede con le modifiche necessarie sui dati inseriti
			 */
			if(end)
			{
				af.aggiungiFruitore(f);
				System.out.println(ISCRIZIONE_OK);
			}
			else
			{
	
				if(InputDati.leggiUpperChar(RICHIESTA_PROSECUZIONE, "SN") == 'N')
				{
					end = true;				
					System.out.println(ISCRIZIONE_NON_OK);
				}
				
			}
			
		}while(!end);
	    
	}
	
	/**
	 * Metodo per l'accesso di un utente al sistema.
	 * Vengono effettuati dei controlli sulla correttezza dello username e della password indicati
	 * 
	 * Pre : ag != null
	 * Pre : ag.elenco != null
	 * 
	 * @param ag : oggetto di tipo Anagrafica contenente l'elenco degli utenti presenti ed il metodo per l'accesso
	 * @return l'utente specificato dalle credenziali indicate
	 */
    public Utente accesso(Anagrafica ag)
    {
		String use = "";
		String pwd = "";
		boolean end = false;
		Utente ut = null;
		
	    do
	    {
			use = InputDati.leggiStringaNonVuota(USERNAME);
			pwd = InputDati.leggiStringaNonVuota(PASSWORD);

			/**
			 * Se viene effettivamente reperito l'utente indicato, l'accesso si conclude con successo.
			 * Altrimenti, a meno che l'utente non esprima la volonta' di terminare l'operazione, si procede con le modifiche necessarie sui dati inseriti
			 */
			if(ag.accedi(use, pwd))
			{
				ut = ag.getUtente(use, pwd);
				end = true;
			}
			else
			{
				System.out.println(CREDENZIALI_ERRATE);
				 
				if(InputDati.leggiUpperChar(RICHIESTA_PROSECUZIONE, "SN") == 'N')
				{
					end = true;
				}
					
			}
	
		}while(!end);
		
	    return ut;
	}
    
    /**
    * Metodo per l'aggiunta di una risorsa ad una (sotto)categoria dell'archivio
    * 
    * Pre: (op != null) && (arc != null)
    * 
    * @param op: l'operatore che effettua l'aggiunta della risorsa
    * @param arc: l'archivio a cui aggiungere la risorsa
    */
    public void aggiungiRisorsa(Operatore op, Archivio arc)
    {
    	Categoria c = null;
    	SottoCategoria sc = null;
    	Libro nuovol = null;
    	     
    	System.out.printf(CONTENUTO_ARC, arc.stampaElencoCategorie());
    	int num1 = InputDati.leggiIntero(INS_NUMERO_CAT, NUM_MINIMO, (arc.getElencoCategorie()).size());
    	c = (arc.getElencoCategorie()).get(num1-1);
    	    	
    	System.out.printf(INS_IN_SOTTO, c.getNome(), c.stampaElencoSottocategorie());

    	if(c.getElencoSottoCategorie() == null)
    	{
      	    if((c.getNome()).equalsIgnoreCase("Libri"))  
    	    {
    	    	nuovol = InserimentoRisorsa.inserisciLibro();
    	    	    	    	    	       
  	    	   	if((c.getRisorsa(nuovol.getTitolo()) == null))
  	    	   	{
  	    	   		op.aggiungiRisorsaCategoria(nuovol, c);
  	    	    	System.out.println(OP_SUCCESSO);
  	    	   	}
  	    	   	else
  	    	    	System.out.println(OP_NO_SUCCESSO_AGGIUNTA);
    	    }
      	    
    	}
    	else if(InputDati.leggiUpperChar(INS_PROCEDERE_SOTTO, "SN") == 'S')
    	{
    	    int num2 = InputDati.leggiIntero(INS_NUMERO_SOTTOC, NUM_MINIMO, (c.getElencoSottoCategorie()).size());
    	    sc = (c.getElencoSottoCategorie()).get(num2-1);
    	    	    	    	    	    
    	    if((c.getNome()).equalsIgnoreCase("Libri"))  
    	    {
    	    	nuovol = InserimentoRisorsa.inserisciLibro();
    	    	    	    	    	       
  	    	   	if((sc.getRisorsa(nuovol.getTitolo()) == null) && ((nuovol.getGenere()).equalsIgnoreCase(sc.getNome())))
  	    	   	{
  	    	   		op.aggiungiRisorsaCategoria(nuovol, sc);
  	    	    	System.out.println(OP_SUCCESSO);
  	    	   	}
  	    	   	else
  	    	    	System.out.println(OP_NO_SUCCESSO_AGGIUNTA);
    	    }
    	    
    	}
    	
    }
    
    /**
     * Metodo per la rimozione di una risorsa da una (sotto)categoria dell'archivio
     * 
     * Pre: (op != null) && (arc != null)
     * 
     * @param op: l'operatore che effettua la rimozione della risorsa
     * @param arc: l'archivio da cui rimuovere la risorsa
     */
    public void rimuoviRisorsa(Operatore op, Archivio arc)     ////////Da ampliare con il metodo generico di ricerca della risorsa
    {
       	Categoria c = null;
	    SottoCategoria sc = null;
	    Risorsa daEliminare = null;
	    
	    System.out.printf(CONTENUTO_ARC, arc.stampaElencoCategorie());
	    int num1 = InputDati.leggiIntero(INS_NUMERO_CAT, NUM_MINIMO, (arc.getElencoCategorie()).size());
   	    c = (arc.getElencoCategorie()).get(num1-1);
   	       	 
    	System.out.printf(INS_IN_SOTTO, c.getNome(), c.stampaElencoSottocategorie());

  	    if(c.getElencoSottoCategorie() == null)
	    {
	    	   System.out.printf(CONTENUTO_CAT, c.getNome(), c.stampaElencoRisorse());
	    	  
	    	   int num3 = InputDati.leggiIntero(INS_NUMERO_RISORSA, NUM_MINIMO, (c.getElencoRisorse()).size());
	    	   daEliminare = (c.getElencoRisorse()).get(num3-1);
	    	   op.rimuoviRisorsaCategoria(daEliminare, c);
	    } 
  	    else if(InputDati.leggiUpperChar(INS_PROCEDERE_SOTTO, "SN") == 'S')
	    {
	    	   int num2 = InputDati.leggiIntero(INS_NUMERO_SOTTOC, NUM_MINIMO, (c.getElencoSottoCategorie()).size());
	    	   sc = (c.getElencoSottoCategorie()).get(num2-1);

	    	   System.out.printf(CONTENUTO_SOTTO, sc.getNome(), sc.stampaElencoRisorse());
	    	   
	    	   if(InputDati.leggiUpperChar(INS_PROCEDERE_RISORSA, "SN") == 'S')
	    	   {
	    		   int num3 = InputDati.leggiIntero(INS_NUMERO_RISORSA, NUM_MINIMO, (sc.getElencoRisorse()).size());
	    		   daEliminare = (sc.getElencoRisorse()).get(num3-1);
	    		   op.rimuoviRisorsaCategoria(daEliminare, sc);
	    	   }
	    }   
   	    
    }
    
    public void aggiungiPrestito(Fruitore f, Archivio arc, ArchivioPrestiti a)    ///////
    {
    	Categoria c = null;
    	SottoCategoria sc = null;
    	Risorsa r = null;
    	Prestito nuovo = null;
    	
    	System.out.printf(CONTENUTO_ARC, arc.stampaElencoCategorie());
    	int num1 = InputDati.leggiIntero(INS_NUMERO_CAT_PRESTITO, NUM_MINIMO, (arc.getElencoCategorie()).size());
    	c = (arc.getElencoCategorie()).get(num1-1);

    	System.out.printf(INS_IN_SOTTO, c.getNome(), c.stampaElencoSottocategorie());

    	if(c.getElencoSottoCategorie() == null)
    	{
      	    if((c.getNome()).equalsIgnoreCase("Libri"))  
    	    {
      	    	System.out.printf(c.stampaElencoRisorse());
      	    	
    	    	int num = InputDati.leggiIntero(INS_NUMERO_RISORSA_PRESTITO, NUM_MINIMO, c.getElencoRisorse().size());
    	    	
    	    	r = c.getElencoRisorse().get(num-1);
    	    	
  	    	   	if(r != null && a.controlloDisponibilitaRisorsa(r) && a.controlloPrestito(c, f))
  	    	   	{
  	    	   		nuovo = new Prestito(c, f, r);
  	    	   		f.registraNuovoPrestito(a, nuovo);
  	    	    	System.out.println(OP_SUCCESSO);
  	    	   	}
  	    	   	else
  	    	    	System.out.println(OP_NO_SUCCESSO_PRESTITO);
    	    }
      	    
    	}
    	else if(InputDati.leggiUpperChar(INS_PROCEDERE_SOTTO, "SN") == 'S')
    	{
    	    int num2 = InputDati.leggiIntero(INS_NUMERO_SOTTOC_PRESTITO, NUM_MINIMO, (c.getElencoSottoCategorie()).size());
    	    sc = (c.getElencoSottoCategorie()).get(num2-1);
    	    	    	    	    	    
  	    	System.out.println(sc.stampaElencoRisorse());
  	    	
	    	int num = InputDati.leggiIntero(INS_NUMERO_RISORSA_PRESTITO, NUM_MINIMO, c.getElencoRisorse().size());
	    	
	    	r = sc.getElencoRisorse().get(num-1);

	    	   	if(r != null && a.controlloDisponibilitaRisorsa(r) && a.controlloPrestito(c, f))
	    	   	{
  	    	   		nuovo = new Prestito(c, f, r);
  	    	   		f.registraNuovoPrestito(a, nuovo);
	    	    	System.out.println(OP_SUCCESSO);
	    	   	}
	    	   	else
	    	    	System.out.println(OP_NO_SUCCESSO_PRESTITO);
    	    
    	}
    	
    }
    
    public void richiediProroga(Fruitore f, Archivio arc, ArchivioPrestiti a)    ///////
    {
    	System.out.println(f.visualizzaPrestitiInCorso(a));
    	
    	int num = InputDati.leggiIntero(INS_NUMERO_PRESTITO_PROROGA, NUM_MINIMO, a.getVettorePrestiti(f.getUsername()).size());
    	Prestito nuovo = a.getElencoPrestiti().get(num-1);
    	
    	if(f.registraProrogaPrestito(a, nuovo))
    	{
        	System.out.println(OP_SUCCESSO);
    	}
    	else
    	{
        	System.out.println(OP_NO_SUCCESSO_PROROGA);
    	}
    	
    }
    
    public Vector<Risorsa> ricercaRisorsa(Utente ut, ArchivioPrestiti a)
    {
    	 int numScelta = InputDati.leggiIntero(AVVIO_RICERCA_RISORSE, 1, 4);
    	
	     switch(numScelta)
	    {
	    	case(1): return( ut.ricercaRisorsaPerTitolo( a, InputDati.leggiStringa(INS_TITOLO_RISORSA) ) );
	    		
	    	case(2): return( ut.ricercaRisorsaPerAutore( a, InputDati.leggiStringa(INS_AUTORE_RISORSA) ) );
	    		
	    	case(3): return( ut.ricercaRisorsaPerAnnoPubblicazione( a, InputDati.leggiIntero(INS_ANNOPUB_RISORSA) ) );
	    		
	    	case(4): return( ut.ricercaRisorsaPerCasaEditrice( a, InputDati.leggiStringa(INS_CASAED_RISORSA) ) );
	    	
	    	default: return null;
	    }
	     
    }
    
    public String ricercaRisorsaFormatoStringa(Utente ut, Vector <Risorsa> elencoRisorse)
    {
   	    StringBuffer ris = new StringBuffer();
   	    ris.append(INTESTAZIONE_RICERCA_RISORSE);
   	    
		for(int i = 0; i < elencoRisorse.size(); i++)
		{
			Risorsa r = elencoRisorse.get(i);
			ris.append(i+1 + ")" + r.toString());
		}
		
		return ris.toString();
    }
    
    public boolean valutazioneDisponibilita(Utente ut, ArchivioPrestiti a)
    {
    	System.out.println( ricercaRisorsaFormatoStringa(ut, ricercaRisorsa(ut, a) ) );
    	
    	int num = InputDati.leggiIntero( RICHIESTA_DIGITAZIONE_VALUTAZIONE, NUM_MINIMO, ricercaRisorsa(ut, a).size() );
    	
    	if( a.controlloDisponibilitaRisorsa( ricercaRisorsa(ut, a).get(num) ))
    		return true;
    	else
    		return false;
    }
    
    /**
     * Vengono inizialmente creati i vari menu' con le relative intestazioni ed opzioni. 
     * In seguito l'andamento del programma Ã¨ scandito attraverso l'aggiornamento della variabile letteraMenu e l'uso di switch-case innestati,
     * in cui il primo livello (contraddistinto dalle variabili letterali) indica gli specifici menu', mentre il secondo livello (evidenziato
     * dall'uso della variabile intera 'scelta') indica le opzioni relative ad ogni menu' e le operazioni che vengono indi svolte
     * 
     * Pre : af != null
     * Pre : ao != null
     * Pre : arc != null
     * 
     * @param af : oggetto di tipo AnagraficaFruitori
     * @param ao : oggetto di tipo AnagraficaOperatori
     * @param arc : oggetto di tipo Archivio
     */
    public void logicaMenu(AnagraficaFruitori af, AnagraficaOperatori ao, Archivio arc, ArchivioPrestiti ap)
    {
     	Menu a = new Menu(INTESTAZIONE_A, OPZIONI_A);
	    Menu b = new Menu(INTESTAZIONE_B, OPZIONI_B);
	    Menu c = new Menu(INTESTAZIONE_C, OPZIONI_C);
	    Menu d = new Menu(INTESTAZIONE_D, OPZIONI_D);
	    Menu e = new Menu(INTESTAZIONE_E, OPZIONI_E);
	    Menu f = new Menu(INTESTAZIONE_F, OPZIONI_F);

      	boolean esci = false;
      	char letteraMenu =  'a';
        int scelta = 0;
        
        Fruitore attualef = null;
        Operatore attualeop = null;
        Utente attualeut = null;
       
        System.out.println(SALUTO_INIZIALE);
          
        do
        {
        	af.decadenzaFruitore();
        	ap.scadenzaPrestito();

        	switch(letteraMenu)
    	    {
    	      	case('a'):
    	        {
    	    		   scelta = a.scegli();
	        	     
    	    		   switch(scelta)
	        	   {
	        	     	case 1: letteraMenu = 'b';
	        	                break;
  	        	
	        	        case 2: letteraMenu = 'e';
  	                    		break;
  	                    		
	        	        case 3: esci = true;
	        	        		    break;
	        	    }
    	    		    break;
    	        }
    	          
    	        case('b'):
    	        {
    	          	scelta = b.scegli();
	        	     
	        	    switch(scelta)
	        	    {
	        	    	    case 1: iscrizione(af);
	        	                letteraMenu = 'a';
	        	                break;
  	        	
	        	        case 2: letteraMenu = 'c';
  	                    		break;
  	                    		
	        	        case 3: letteraMenu = 'a';
                  		    break;
	        	    }
	        	    break;
    	        }
    	          
    	        case('c'):
    	        {
    	          	scelta = c.scegli();
    	        	     
    	         	switch(scelta)
    	        	    {
    	        		     case 1: attualef = (Fruitore) accesso(af);
        	        	        
    	                         if(attualef != null)
    	        				     {
    	        					     letteraMenu = 'd';
    	        				     }
    	        				     else
    	        				     {
    	        					     System.out.println(ERRORE);
    	        					     letteraMenu = 'c';
    	        				     }
    	        				     break;
      	        	
    	        	         case 2: letteraMenu = 'b';
	        	        			break;
    	        	     }
    	         	 break;
    	        }
    	          
    	        case('d'):
    	        {
    	         	scelta = d.scegli();
 	        	     
 	        	    switch(scelta)
 	        	    {
 	        	        case 1: if(attualef.rinnovaIscrizione(af))
 	        	                     	System.out.println(RINNOVO_OK);
 	        	                 else
 	        	                  	    System.out.println(RINNOVO_NON_OK);
     	        				
 	        	                 letteraMenu = 'd';
 	        	                 break;
 	        	                
 	        	        case 2: System.out.println(attualef.toString());
 	        	        		letteraMenu = 'd';
 	        	        		break;
 	        	                
 	        	        case 3: System.out.println(attualef.visualizzaPrestitiInCorso(ap));
 	        	        		letteraMenu = 'd';
 	        	        		break;
 	        	        		
 	        	        case 4: aggiungiPrestito(attualef, arc, ap);  /////
 	        	        		letteraMenu = 'd';
 	        	        		break;
 	        	        		
 	        	        case 5: richiediProroga(attualef, arc, ap);     /////
 	        	        		letteraMenu = 'd';
 	        	        		break;
 	        	        		
 	        	        case 6: System.out.println( ricercaRisorsaFormatoStringa( attualef, ricercaRisorsa(attualef, ap) ) );
 	        	        		letteraMenu = 'd';
 	        	        		break;
 	        	        
 	        	        case 7: if ( valutazioneDisponibilita(attualef, ap) )
     	     						System.out.println(RISORSA_DISPONIBILE);
     	     					else
     	     						System.out.println(RISORSA_NON_DISPONIBILE);
 	        					letteraMenu = 'd';
     	     					break;
 	        	        	
 	        	        case 8: letteraMenu = 'a';
 	        	        		attualef = null;
 	        	                break;
 	        	    }
 	        	    break;
    	        }
    	        
    	        case('e'):
    	        {
    	        	    scelta = e.scegli();
 	        	     
 	        	    switch(scelta)
 	        	    {
 	        	    	     case 1: attualeop = (Operatore) accesso(ao);
 	        	    				
 	                         if(attualeop != null)
 	        	    			     {
 	        	    				    letteraMenu = 'f';
 	        	    			     }
 	        	    			     else
 	        	    			     {
 	        	    				    System.out.println(ERRORE);
 	        	    				    letteraMenu = 'e';
 	        	    			     }
 	        	    	             break;
 	        	                
 	        	        case 2: letteraMenu = 'a';
 	        	                break;
 	        	    }
 	        	    break;
    	        }
    	        
    	        case('f'):
    	        {
    	        	     scelta = f.scegli();
 	        	     
 	        	     switch(scelta)
 	        	     {
 	        	     	case 1: System.out.println(attualeop.visualizzaElencoFruitori(af));
 	        	     			letteraMenu = 'f';
 	        	                break;
 	        	                
 	        	     	case 2: System.out.println(attualeop.visualizzaArchivio(arc));
 	        	     	        letteraMenu = 'f';
 	        	     	        break;
 	        	     	        
 	        	     	case 3: aggiungiRisorsa(attualeop, arc);
 	        	     	        letteraMenu = 'f';
 	        	     	        break;
 	        	     		
 	        	     	case 4: rimuoviRisorsa(attualeop, arc);
 	        	     	        letteraMenu = 'f';
 	        	     	        break;
 	        	     	        
 	        	     	case 5: System.out.println( ricercaRisorsaFormatoStringa( attualeop, ricercaRisorsa(attualeop, ap) ) );
     	     	        		letteraMenu = 'f';
 	        	     			break;
 	        	     
 	        	     	case 6: if ( valutazioneDisponibilita(attualeop, ap) )
 	        	     				System.out.println(RISORSA_DISPONIBILE);
 	        	     			else
 	        	     				System.out.println(RISORSA_NON_DISPONIBILE);
	     	        			letteraMenu = 'f';
 	        	     			break;
 	        	     	    
 	        	        case 7: letteraMenu = 'a';
 	        	        		attualeop = null;
 	        	                break;
 	        	     }
 	        	     break;
    	        }
    	        
    	     /*   case('g'):
    	        {
    	        	     scelta = g.scegli();

    	        	     if(attualef == null)
    	        	    	 attualeut = attualeop;
    	        	     else
    	        	    	 attualeut = attualef;
    	        	     
    	        	     switch(scelta)
    	        	    {
    	        	    	case(1): System.out.println( attualeut.ricercaRisorsaPerTitolo( ap, InputDati.leggiStringa(INS_TITOLO_RISORSA) ) );
    	        	    			break;
    	        	    		
    	        	    	case(2): System.out.println( attualeut.ricercaRisorsaPerAutore( ap, InputDati.leggiStringa(INS_AUTORE_RISORSA) ) );
    	        				 	break;
    	        	    		
    	        	    	case(3): System.out.println( attualeut.ricercaRisorsaPerAnnoPubblicazione( ap, InputDati.leggiIntero(INS_ANNOPUB_RISORSA) ) );
    	        				 	break;
    	        	    		
    	        	    	case(4): System.out.println( attualeut.ricercaRisorsaPerCasaEditrice( ap, InputDati.leggiStringa(INS_CASAED_RISORSA) ) );
    	        	    			break;
    	        	    }
    	        	     break;
    	        }*/
    	        
    	    }
    	      
       }while(!esci);   
       
       System.out.println(SALUTO_FINALE);       
    }
     
}
