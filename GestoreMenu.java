package it.ing.sw.v3;

import java.io.Serializable;
import java.time.DateTimeException;
import it.ing.sw.*;
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
	public static final String [] OPZIONI_F = {"Visualizza anagrafica fruitori", "Visualizza archivio", "Aggiungi risorsa", "Rimuovi risorsa", "Logout"};
	
    public static final String INS_NOME = "Inserisci il tuo nome: ";
    public static final String INS_COGNOME = "Inserisci il tuo cognome: ";
    public static final String INS_USERNAME = "Inserisci il tuo username: ";
    public static final String INS_PASSWORD = "Inserisci la tua password: ";
    public static final String INS_GIORNO_NASCITA = "Inserisci il tuo giorno di nascita: ";
    public static final String INS_MESE_NASCITA = "Inserisci il tuo mese di nascita: ";
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

    public static final String INS_NOME_C = "Inserisci il nome della categoria a cui aggiungere la risorsa:\n";
	public static final String INS_SUCCESSO = "L'inserimento e' avvenuto con successo\n";
    public static final String RISORSA_PRESENTE = "Attenzione! La risorsa e' gia'� presente nell'archivio";
    public static final String INS_IN_SOTTO = "La categoria %s presenta queste sottocategorie:\n%s";
    public static final String INS_COMUNQUE = "Vuoi inserire la risorsa in una sottocategoria (S/N)\n";
    public static final String INS_NUMERO_SOTTOC =  "Inserisci il numero della sottocategoria a cui aggiungere la risorsa:\n";
    public static final String INS_NON_VALIDO = "Il nome della categoria inserita non e' presente in archivio\n";
    public static final String INS_NOME_SOTTOC = "Inserisci il nome della sottocategoria a cui aggiungere la risorsa:\n";
    
    
    
    
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
    	     Libro nuovol = null;
    	     SottoCategoria sc = null;
    	
    	     String n = InputDati.leggiStringaNonVuota(INS_NOME_C);
    	    	       
    	    	 if(arc.verificaPresenzaCategoria(n))
    	    	 {
    	    	    	  Categoria c = arc.getCategoria(n);
    	    	    	       
    	    	    	  if(c.getElencoSottoCategorie() == null)
    	    	    	  {
    	    	    	    	   if(n.equalsIgnoreCase("Libri")) //forse caso inutile perchè nel main abbiamo messo delle sottocategorie alla categoria Libro
    	    	    	    	   {
    	    	    	    	    	    nuovol = InserimentoRisorsa.inserisciLibro();
    	    	    	    	    	    
    	    	    	    	    	    if((c.getRisorsa(nuovol.getNome())) == null) //la risorsa non c'è nell'archivio
    	    	    	    	    	    {
    	    	    	    	    	        	op.aggiungiRisorsaCategoria(nuovol, c);
    	    	    	    	    	        	System.out.println(INS_SUCCESSO);
    	    	    	    	    	    }
    	    	    	    	    	    else
    	    	    	    	    	        System.out.println(RISORSA_PRESENTE);
    	    	    	    	    }
    	    	    	    	    //cosa simile per film nelle successive versioni
    	    	    	   }
    	    	    	   else //sono presenti sottocategorie, devo aggiungere a queste
    	    	    	   {
    	    	    	    	    System.out.printf(INS_IN_SOTTO, c.getNome(), c.stampaElencoSottocategorie(c));
    	    	    	    	       
    	    	    	    	    if(InputDati.leggiUpperChar(INS_COMUNQUE, "SN") == 'S')
    	    	    	    	    {
    	    	    	    	    	    int num = InputDati.leggiIntero(INS_NUMERO_SOTTOC, 1, (c.getElencoSottoCategorie()).size());
    	    	    	    	    	    sc = (c.getElencoSottoCategorie()).get(num-1);
    	    	    	    	    	    
    	    	    	    	    	    if(n.equalsIgnoreCase("Libri"))  //mi serve per sapere che metodo di InserimentoRisorsa invocare
    	    	    	    	    	    {
    	    	    	    	    	        nuovol = InserimentoRisorsa.inserisciLibro();
    	    	    	    	    	       
    	    	    	    	    	        if((sc.getRisorsa(nuovol.getNome()) == null) && ((nuovol.getGenere()).equalsIgnoreCase(sc.getNome()))) //se il libro non c'è nella sottocategoria e il suo genere è uguale al nome della sottocategoria
    	    	    	    	    	        {
  	    	    	    	    	        	    op.aggiungiRisorsaCategoria(nuovol, sc);
  	    	    	    	    	        	    System.out.println(INS_SUCCESSO);
    	    	    	    	    	        }
    	    	    	    	    	        else
    	    	    	    	    	        	System.out.println(RISORSA_PRESENTE);
    	    	    	    	    	    }
    	    	    	    	    	    //cosa simile per i film nelle successive versioni
    	    	    	    	     }
    	    	    	    	     //se digita N, esco da tutto senza fare più nulla
    	    	    	     }
    	    	 }
    	    	 else
    	    	    	  System.out.println(INS_NON_VALIDO);
    	    	    	       
    }
    
    /**
     * Vengono inizialmente creati i vari menu' con le relative intestazioni ed opzioni. 
     * In seguito l'andamento del programma e' scandito attraverso l'aggiornamento della variabile letteraMenu e l'uso di switch-case innestati,
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
    public void logicaMenu(AnagraficaFruitori af, AnagraficaOperatori ao, Archivio arc)
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
       
        System.out.println(SALUTO_INIZIALE);
          
        do
        {
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
 	        	         case 1: if(af.rinnovoIscrizioneFruitore(attualef.getUsername()))
 	        	                     	System.out.println(RINNOVO_OK);
 	        	                 else
 	        	                  	    System.out.println(RINNOVO_NON_OK);
     	        				
 	        	                 letteraMenu = 'd';
 	        	                 break;
 	        	                
 	        	        case 2: System.out.println(attualef.toString());
 	        	        		    letteraMenu = 'd';
 	        	                break;
 	        	        
 	        	        case 3: attualef.visualizzaPrestitiInCorso(ap)/////
 	        	        			letteraMenu = 'd';
 	        	        			break;
 	        	        case 4: 
 	        	        case 6: letteraMenu = 'a';
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
 	        	     	case 1: attualeop.visualizzaElencoFruitori(af);
 	        	     			letteraMenu = 'f';
 	        	                break;
 	        	                
 	        	     	case 2: attualeop.visualizzaArchivio(arc);
 	        	     	        letteraMenu = 'f';
 	        	     	        break;
 	        	     	        
 	        	     	case 3: aggiungiRisorsa(attualeop, arc);
 	        	     	        letteraMenu = 'f';
 	        	     	        break;
 	        	     		
 	        	     	case 4: 
 	        	                
 	        	        case 5: letteraMenu = 'a';
 	        	                break;
 	        	     }
 	        	     break;
    	        }
    	        
    	    }
    	      
       }while(!esci);   
       
       System.out.println(SALUTO_FINALE);       
    }
     
}
