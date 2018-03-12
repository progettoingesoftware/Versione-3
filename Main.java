package it.ing.sw.v3.p3;

import java.io.File;

import it.ing.sw.*;
import it.ing.sw.v3.p1.AnagraficaFruitori;
import it.ing.sw.v3.p1.AnagraficaOperatori;
import it.ing.sw.v3.p2.Archivio;

public class Main 
{
	public static final String NOME_FILE = "gestoreRisorse.txt";								
	public static final String MSG_NO_CAST = "ATTENZIONE PROBLEMI CON IL CAST";			
	public static final String MSG_OK_FILE = "CARICAMENTO DA FILE EFFETTUATO";			
	public static final String MSG_NO_FILE = "CARICAMENTO DA FILE NON RIUSCITO. OCCORRE CREARE UNA NUOVA ANAGRAFICA DEI FRUITORI";			
	public static final String MSG_SALVA = "SALVATAGGIO DATI";
	public static final String ERRORE_CONVERSIONE_DATA = "Attenzione! Si e' verificato un errore di conversione della data";	
	
	/**
	 * Metodo main per l'esecuzione del software
	 * @param args
	 */
	public static void main(String[] args) 
	{
        File gestoreRisorse = new File(NOME_FILE);
        
        RaccoltaDati rd = null;
        AnagraficaFruitori af = null;
        AnagraficaOperatori ao = null;
        Archivio arc = null;
        ArchivioPrestiti ap = null;

        boolean caricamentoRiuscito = false;
		
		/**
		 * Tale istruzione verifica se il file in questione esiste all'interno del sistema di memorizzazione locale.
		 * In questo caso vengono estrapolate sia la RaccoltaDati sia l'AnagraficaFruitori, l'AnagraficaOperatori, l'Archivio e l'ArchivioPrestiti venendo salvati nelle variabili opportune.
		 * Le probabili eccezioni vengono gestite secondo la modalita' piu' adatta al tipo di eccezione ed infine viene mostrato un messaggio di conferma se il caricamento da file gia' esistente si e' concluso con successo
		 */
		if (gestoreRisorse.exists())
		{
			/**
			 * Si cercano di reperire le istanze delle classi AnagraficaFruitori, AnagraficaOperatori, Archivio e ArchivioPrestiti salvate su file. 
			 * Vengono inoltre opportunamente gestite le eccezioni di tipo ClassCast e NullPointer.
			 * Infine, nel caso in cui le istanze siano state correttamente inizializzate, viene mostrato a video un messaggio di conferma
			 * modificando al contempo una specifica variabile booleana per la segnalazione dell'avvenuto caricamento
			 */
			try 
			{
				rd = (RaccoltaDati)ServizioFile.caricaSingoloOggetto(gestoreRisorse);
				af = rd.getAnagraficaFruitori();
				ao = rd.getAnagraficaOperatori();
				arc = rd.getArchivio();
				ap = rd.getArchivioPrestiti();
			}
			catch (ClassCastException e)
			{
				System.out.println(MSG_NO_CAST);
			}
			catch(NullPointerException a)
			{
				System.out.println();
			}
			finally
			{
				if (af != null && ao != null && arc != null && ap != null)
				{
					System.out.println(MSG_OK_FILE);
					caricamentoRiuscito = true;
				}
			}		
		}
		
		/**
		 * Nel caso in cui il caricamento da file non sia andato a buon fine si provvedono a costruire ex novo le strutture dati richieste e a creare la struttura del sistema
		 */
		if (!caricamentoRiuscito)				
		{
			System.out.println(MSG_NO_FILE);				
			af = new AnagraficaFruitori();
			ao = new AnagraficaOperatori();
			arc = new Archivio();
			ap = new ArchivioPrestiti();
			
			StrutturaSistema.aggiuntaOperatoriPreimpostati(ao);
			StrutturaSistema.creazioneStrutturaArchivio(arc);
		}
		
		/**
		 * Viene costruito un nuovo gestore menu' che possa dare avvio alla logica del sistema applicativo 
		 */
		GestoreMenu g = new GestoreMenu();
		g.logicaMenu(af, ao, arc, ap);
		
		/**
		 * L'operazione di salvataggio prevede la costruzione di una nuova RaccoltaDati attraverso i parametri AnagraficaFruitori, AnagraficaOperatori, Archivio e ArchivioPrestiti e l'aggiornamento del file in gestoreRisorse
		 */
		System.out.println(MSG_SALVA);
		rd = new RaccoltaDati(af, ao, arc, ap);
	    ServizioFile.salvaSingoloOggetto(gestoreRisorse, rd);	
	}
	
}
