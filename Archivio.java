package it.ing.sw.v3;

import java.io.Serializable;
import java.util.Vector;

/**
 * Questa classe rappresenta il modello di un Archivio
 */
public class Archivio implements Serializable
{  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Vector <Categoria> elencoCategorie;
	
	public static final String DESCRIZIONE_ARCHIVIO = "\nL'archivio presenta il seguente contenuto:\n";
	public static final String ARCHIVIO_VUOTO ="\nL'archivio e' vuoto";
	
	/**
	 * Metodo costruttore della classe Archivio
	 * 
	 * Post: elencoCategorie != null
	 */
	public Archivio()
	{
		elencoCategorie = new Vector <Categoria> ();
	}
	
	/**
	 * Data una stringa n, questo metodo restituisce l'oggetto Categoria avente n come nome, se e' presente
	 * 
	 * Pre: elencoCategorie != null
	 * 
	 * @param n: il nome di una categoria
	 * @return l'oggetto Categoria avente n come nome altrimenti null
	 */
	public Categoria getCategoria(String n)
	{
		for(int i = 0; i < elencoCategorie.size(); i++)
		{
			Categoria c = elencoCategorie.get(i);
			if(c.getNome().equalsIgnoreCase(n))
				  return c;
		}
	
		return null;
	}
	
	/**
	 * Data una stringa n, questo metodo verifica la presenza di una categoria nell'elenco delle categorie avente come nome n
	 * 
	 * Pre: elencoCategorie != null
	 * 
	 * @param n: una stringa
	 * @return true se nell'elenco delle categorie e' presente una categoria avente come nome n
	 */
	public boolean verificaPresenzaCategoria(String n)
	{
		for(int i = 0; i < elencoCategorie.size(); i++)
		{
			Categoria c = elencoCategorie.get(i);
			if(c.getNome().equalsIgnoreCase(n))
				  return true;
		}
		
		return false;
	}
	
	/**
	 * Metodo per l'aggiunta di una categoria all'archivio, viene invocato al momento della creazione della struttura
	 * dell'archivio nel Main
	 * 
	 * Pre: c != null
	 * 
	 * @param c: la categoria da aggiungere
	 */
	public void aggiungiCategoria(Categoria c)
	{
		elencoCategorie.add(c);
	}
	
	/**
     * Metodo toString() per la creazione di una stringa descrittiva del contenuto dell'archivio
     * 
     * Pre: elencoCategorie != null
     * 
     * @return la stringa descrittiva dell'archivio
     */
	public String toString()
	{
		StringBuffer ris = new StringBuffer();
		ris.append(DESCRIZIONE_ARCHIVIO);
		
		for(int i = 0; i < elencoCategorie.size(); i++)
		{
			Categoria c = elencoCategorie.get(i);
			ris.append(i+1 + ")" + c.toString());
		}
		
		return ris.toString();
	}
	
}
