package it.ing.sw.v2;

import java.io.Serializable;

/**
 * Questa classe rappresenta il modello di una risorsa
 */
public abstract class Risorsa implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private int numLicenze;
    
	/**
	 * Metodo costruttore della classe Risora
	 * @param n: il nome con cui identificare la risorsa
	 * @param l: il numero delle licenze associate alla risorsa
	 */
    public Risorsa(String n, int l)
    {
    	    this.nome = n;
    	    this.numLicenze = l;
    }
    
    /**
     * Metodi get per il ritorno dei vari attributi della classe Risorsa
     * @return
     */
    public String getNome()
    {
    	    return nome;
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
