package it.ing.sw.v3;

import java.util.Vector;
import it.ing.sw.InputDati;

/**
 * Questa classe e' di appoggio per l'inserimento dei dati relativi ad una risorsa nei metodi aggiungiRisorsa() e 
 * rimuoviRisorsa() della classe GestoreMenu
 */
public class InserimentoRisorsa 
{
    public static final String INS_TITOLO = "Inserisci il titolo del libro:\n";
    public static final String INS_NUMLICENZE = "Inserisci il numero delle licenze del libro:\n";
    public static final String INS_AUTORE = "Inserisci l'autore del libro(uno alla volta):\n";
    public static final String INS_ALTRO_AUTORE = "Vuoi inserire un altro autore (S/N)?\n";
    public static final String INS_NUMPAGINE = "Inserisci il numero delle pagine del libro:\n";
    public static final String INS_ANNOP = "Inserisci l'anno di pubblicazione del libro:\n";
    public static final int ANNO_CORRENTE = 2018;
    public static final String INS_CASAED = "Inserisci la casa editrice del libro:\n";
    public static final String INS_LINGUA = "Inserisci la lingua in cui e' scritto il libro:\n";
    public static final String INS_GENERE = "Inserisci il genere del libro:\n";
	
    
    /**
     * Metodo per l'acquisizione dei dati relativi ad un libro
     * @return l'oggetto libro costruito sulla base dei dati inseriti
     */
	public static Libro inserisciLibro()
    {
    	    String t = InputDati.leggiStringaNonVuota(INS_TITOLO);
    	    int nl = InputDati.leggiIntero(INS_NUMLICENZE, 0, 500);
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
    	    int ap = InputDati.leggiIntero(INS_ANNOP, 100, ANNO_CORRENTE);
    	    String ce = InputDati.leggiStringaNonVuota(INS_CASAED);
    	    String l = InputDati.leggiStringaNonVuota(INS_LINGUA);
    	    String g = InputDati.leggiStringaNonVuota(INS_GENERE);
    	    		
    	    Libro lib = new Libro(nl, t, a, np, ap, ce, l, g);
    	    
    	    return lib;
    }
	
}
