package interazione_3;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Vector;

import dominio_3.Libro;
import utility.InputDati;

/**
 * Questa classe e' di appoggio per l'inserimento dei dati relativi ad una risorsa nel metodo aggiungiRisorsa() 
 * della classe GestoreMenu
 */
public class InserimentoRisorsa implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final String INS_TITOLO = "Inserisci il titolo del libro:\n";
    public static final String INS_NUMLICENZE = "Inserisci il numero delle licenze del libro:\n";
    public static final String INS_AUTORE = "Inserisci nome e cognome di un autore del libro:\n";
    public static final String INS_ALTRO_AUTORE = "Vuoi inserire un altro autore (S/N)?\n";
    public static final String INS_NUMPAGINE = "Inserisci il numero delle pagine del libro:\n";
    public static final String INS_ANNOP = "Inserisci l'anno di pubblicazione del libro:\n";
    public static final int ANNO_CORRENTE = LocalDate.now().getYear();
    public static final String INS_CASAED = "Inserisci la casa editrice del libro:\n";
    public static final String INS_LINGUA = "Inserisci la lingua in cui e' scritto il libro:\n";
    public static final String INS_GENERE = "Inserisci il genere del libro:\n";
    
    public static final int MIN_LICENZE = 1;
    public static final int MAX_LICENZE = 20;
    public static final int MIN_ANNOP = 1900;
    
    /**
     * Metodo per l'acquisizione dei dati relativi ad un libro
     * @return l'oggetto libro costruito sulla base dei dati inseriti
     */
	public static Libro inserisciLibro()
    {
    	    String t = InputDati.leggiStringaNonVuota(INS_TITOLO);
    	    int nl = InputDati.leggiIntero(INS_NUMLICENZE, MIN_LICENZE, MAX_LICENZE);
    	    boolean end = false;
    	    Vector <String> a = new Vector <String> ();
    	    
    	    do
    	    {
    	    	    String autore = InputDati.leggiStringaNonVuota(INS_AUTORE);
    	    	    a.add(autore);
    	    	    
    	    	    if(InputDati.leggiUpperChar(INS_ALTRO_AUTORE, "SN") == 'N')
    	    	    	     end = true;
    	    	    
    	    }while(!end);
    	    
    	    int np = InputDati.leggiIntero(INS_NUMPAGINE);
    	    int ap = InputDati.leggiIntero(INS_ANNOP, MIN_ANNOP, ANNO_CORRENTE);
    	    String ce = InputDati.leggiStringaNonVuota(INS_CASAED);
    	    String l = InputDati.leggiStringaNonVuota(INS_LINGUA);
    	    String g = InputDati.leggiStringaNonVuota(INS_GENERE);
    	    		
    	    Libro lib = new Libro(nl, t, a, np, ap, ce, l, g);
    	    
    	    return lib;
    }
	
}
