package it.ing.sw.v3;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Questa classe rappresenta il modello di un Fruitore
 */
public class Fruitore extends Utente implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LocalDate dataDiNascita;             
    private LocalDate dataDiIscrizione;		
    private LocalDate dataDiScadenza;
      
    public static final String DESCRIZIONE_FRUITORE = "\nNome: %s\nCognome: %s\nUsername: %s\nPassword: %s\nData di nascita: %s\nData di iscrizione: %s\nData di scadenza: %s\n";
    public static final int TERMINE_SCADENZA = 5;
    public static final String FORMATO_DATA = "dd/MM/yyyy";
    
    /**
     * Metodo costruttore della classe Fruitore
     * 
     * Pre : dn != null
     * Post : dataDiScadenza == dataDiIscrizione.plusYears(TERMINE_SCADENZA)
     * 
     * @param n : nome del fruitore
     * @param c : cognome del fruitore
     * @param dn : data di nascita del fruitore
     * @param u : username del fruitore
     * @param p : password del fruitore
     */     
    public Fruitore(String n, String c, int an, int mn, int gn, String u, String p)
    {
   	     super(n, c, u, p);
   	     this.dataDiNascita = LocalDate.of(an, mn, gn);
   	     
   	     /**
   	      * L'attributo dataDiIscrizione assume il valore restituito dal metodo now() della classe LocalDate;
   	      * tale valore e' costituito dalla data in cui viene effettuata tale invocazione ottenuta attraverso l'orologio di sistema
   	      */
   	     this.dataDiIscrizione = LocalDate.now();
   	     
   	     /**
   	      * L'attributo dataDiScadenza assume il valore indicato dalla data di iscrizione incrementata di un periodo di 5 anni
   	      */
   	     this.dataDiScadenza = dataDiIscrizione.plusYears(TERMINE_SCADENZA);
    }
    
    /**
     * Metodi get per il ritorno dei vari attributi della classe Fruitore
     */
    public LocalDate getDataDiNascita()
    {
   	     return dataDiNascita;
    }
   
    public LocalDate getDataDiIscrizione()
    {
   	     return dataDiIscrizione;
    }
    
    public LocalDate getDataDiScadenza()
    {
   	     return dataDiScadenza;
    }
    
    /**
     * Metodo set per la modifica della data di scadenza del servizio
     * 
     * Pre : nuovads.isAfter(dataDiScadenza)
     * Post : dataDiScadenza == nuovads.plusYears(TERMINE_SCADENZA)
     * 
     * @param nuovads : data di scadenza aggiornata
     */
    public void setDataDiScadenza(LocalDate nuovads)
    {
   	 	 dataDiScadenza = nuovads;
    }
    
    public String visualizzaPrestitiInCorso(ArchivioPrestiti ap)
    {
    	     return ap.getPrestiti(this.getUsername());;
    }
    
    /**
     * Metodo toString() ereditato dalla classe String per la creazione di una stringa descrittiva contenente i vari attributi dell'oggetto Fruitore
     * @return la stringa descrittiva
     */
    public String toString()
    {
      	StringBuffer ris = new StringBuffer();
      	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_DATA);
    	    
      	ris.append(String.format(DESCRIZIONE_FRUITORE, getNome(), getCognome(), getUsername(), getPassword(), dataDiNascita.format(formatter), dataDiIscrizione.format(formatter), dataDiScadenza.format(formatter)));
        return ris.toString();
    } 
    
}
