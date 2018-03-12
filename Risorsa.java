package it.ing.sw.v3.p2;

import java.io.Serializable;

/**
 * Questa classe rappresenta il modello di una risorsa
 */
public abstract class Risorsa implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	protected String titolo;
	private int numLicenze;
    
	/**
	 * Metodo costruttore della classe Risora
	 * @param titolo: il titolo con cui identificare la risorsa
	 * @param lic: il numero delle licenze associate alla risorsa
	 */
    public Risorsa(String t, int lic)
    {
    	    this.titolo = t;
    	    this.numLicenze = lic;
    }
    
    /**
     * Metodi get per il ritorno dei vari attributi della classe Risorsa
     */
    public String getTitolo()
    {
    	return titolo;
    }
    
    public int getNumLicenze() 
    {
    	   return numLicenze;
    }
    
    /**
     * Metodo astratto toString() per la descrizione di una risorsa
     */
    public abstract String toString();
    
}
