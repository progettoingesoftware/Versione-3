package it.ing.sw.v3;

import java.io.Serializable;
import java.util.Vector;

/**
 * Questa classe rappresenta il modello di una SottoCategoria
 */
public class SottoCategoria extends Categoria implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nomeSottoC;
    
    public static final String DESCRIZIONE_SOTTOCATEGORIA = "\nNome sottocategoria: %s\nRisorse in essa contenute:\n";
    
    /**
     * Metodo costruttore della classe SottoCategoria. A differenza della superclasse, l'attributo elencoRisorse, che 
     * viene ereditato da essa, e' inizializzato nel costruttore
     * 
     * Post: elencoRisorse != null
     * 
     * @param ns
     */
    public SottoCategoria(String ns)
    {
    	    super();
    	    this.nomeSottoC= ns;
    	    elencoRisorse = new Vector <Risorsa> ();
    }
    
    /**
     * Metodo get che restituisce il nome della sottocategoria
     */
    public String getNome()
    {
    	    return nomeSottoC;
    }
    
    /**
     * Metodo toString() per la creazione di una stringa descrittiva contenente i vari attributi dell'oggetto SottoCategoria
     * 
     * Pre: elencoRisorse != null
     * 
     * @return la stringa descrittiva della sottocategoria
     */
    public String toString()
    {
 	   StringBuffer ris = new StringBuffer();
 	   ris.append(String.format(DESCRIZIONE_SOTTOCATEGORIA, nomeSottoC));
 	   
 	   for(int i = 0; i < elencoRisorse.size(); i++)
 	   {
 		   Risorsa r = elencoRisorse.get(i);
 		   ris.append(i+1 + ")"+ r.toString());
 	   }
 	   
 	   return ris.toString();
    }
    
}