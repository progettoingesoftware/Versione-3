package it.ing.sw.v2;

import java.io.Serializable;
import java.util.Vector;

/**
 * Questa classe rappresenta il modello di una Categoria.
 *
 * Invariante di classe: (elencoRisorse == null) || (elencoSottoCategorie == null)
 */
public class Categoria implements Serializable
{
   /**
	 * 
	 */
   private static final long serialVersionUID = 1L;
   
   private String nomeCategoria;
   protected Vector <Risorsa> elencoRisorse;
   private Vector <SottoCategoria> elencoSottoCategorie;
   
   private int numeroMaxGiorniPrestito;
   private int numeroMaxGiorniProroga;
   private int numeroGiorniRichiestaProroga;
   private int numeroMaxRisorseInPrestito;

   public static final String DESCRIZIONE_CATEGORIA_SEMPLICE = "Nome categoria: %s\nRisorse in essa contenute:\n";
   public static final String DESCRIZIONE_CATEGORIA_COMPOSTA = "Nome categoria: %s\nSottocategorie in essa contenute:\n";
   
   /**
    * Metodo costruttore della classe Categoria
    * @param n: il nome della categoria
    */
   public Categoria(String n, int numPres, int numMaxPro, int numRiPro, int numRis)
   {
	   this.nomeCategoria = n;
	   this.numeroMaxGiorniPrestito = numPres;
	   this.numeroMaxGiorniProroga = numMaxPro;
	   this.numeroGiorniRichiestaProroga = numRiPro;
	   this.numeroMaxRisorseInPrestito = numRis;
   }
   
   /**
    * Metodo costruttore senza parametri e vuoto della classe Categoria, utile per il costruttore della sottoclasse SottoCategoria
    */
   public Categoria()
   {
	   
   }
   
   /**
    * Inizializza il vettore elencoRisorse
    * 
    * Post: elencoRisorse != null
    */
   public void inizializzaElencoRisorse()
   {
	   elencoRisorse = new Vector <Risorsa> ();
   }
   
   /**
    * Inizializza il vettore elencoSottoCategoria
    * 
    * Post: elencoSottoCategorie != null
    */
   public void inizializzaElencoSottoCategorie()
   {
	   elencoSottoCategorie = new Vector <SottoCategoria> ();
   }
   
   /**
    * Metodi get per il ritorno dei vari attributi della classe Categoria
    */
   public String getNome()
   {
	   return nomeCategoria;
   }
   
   public int getNumeroMaxGiorniPrestito()
   {
	   return numeroMaxGiorniPrestito;
   }
   
   public int getNumeroMaxGiorniProroga()
   {
	   return numeroMaxGiorniProroga;
   }
   
   public int getNumeroGiorniRichiestaProroga()
   {
	   return numeroGiorniRichiestaProroga;
   }
   
   public int getNumeroMaxRisorseInPrestito()
   {
	   return numeroMaxRisorseInPrestito;
   }
     
   public Vector <Risorsa> getElencoRisorse()  
   {
	   return elencoRisorse;
   }
   
   public Vector <SottoCategoria> getElencoSottoCategorie() 
   {
	   return elencoSottoCategorie;
   }
   
   /**
    * Data una stringa n, questo metodo restituisce l'oggetto Risorsa avente n come nome, se e' presente
    * 
    * Pre: elencoRisorse != null
    * 
    * @param n: il nome identificativo di una risorsa
    * @return l'oggetto Risorsa avente n come nome altrimenti null
    */
   public Risorsa getRisorsa(String n)
   {
	   for(int i = 0; i < elencoRisorse.size(); i++)
	   {
		   Risorsa r = elencoRisorse.get(i);
		   if(r.getNome().equalsIgnoreCase(n))
			   return r;
	   }
	   
	   return null;
   }
    
   /**
    * Metodo che permette l'aggiunta di una risorsa all'elenco delle risorse 
    * 
    * Pre: (r != null) && !(elencoRisorse.contains(r))
    * Post: elencoRisorse.contains(r)
    * 
    * @param r: la risorsa da aggiungere
    */
   public void aggiungiRisorsa(Risorsa r)  
   {
	   elencoRisorse.add(r);
   }
   
   /**
    * Metodo che permette la rimozione di una risorsa dall'elenco delle risorse
    * 
    * Pre: (r != null) && (elencoRisorse.contains(r))
    * Post: !(elencoRisorse.contains(r))
    * 
    * @param r: la risorsa da rimuovere
    */
   public void rimuoviRisorsa(Risorsa r)  
   {
	   elencoRisorse.remove(r);
   }
   
   /**
    * Metodo per l'aggiunta di una sottocategoria all'elenco delle sottocategorie, viene invocato al momento della creazione
    * della struttura dell'archivio nel Main
    * 
    * Pre: sc != null
    * 
    * @param sc: la sottocategoria da aggiungere
    */
   public void aggiungiSottoCategoria(SottoCategoria sc)
   {
	   elencoSottoCategorie.add(sc);
   }
   
   /**
    * Metodo per la semplice stampa dell'elenco dei nomi delle risorse contenuti in elencoRisorse
    *
    * Pre: elencoRisorse != null
    * 
    * @return la stringa con l'elenco dei nomi delle risorse
    */
   public String stampaElencoRisorse()
   {
      StringBuffer ris = new StringBuffer();
 	   
 	   for(int i = 0; i < elencoRisorse.size(); i++)
 	   {
 		   Risorsa r = elencoRisorse.get(i);
 		   ris.append(i+1 + ")"+ r.getNome() + "\n");
 	   }
 	   
 	   return ris.toString();
   }
   
   /**
    * Metodo per la semplice stampa dell'elenco dei nomi delle sottocategorie associato ad una categoria
    * 
    * Pre: (c != null) && (elencoSottoCategorie != null)
    * 
    * @return la stringa con l'elenco dei nomi delle sottocategorie
    */
   public String stampaElencoSottocategorie()
   {
	   StringBuffer ris = new StringBuffer();
	   
	   for(int i = 0; i < elencoSottoCategorie.size(); i++)
	   {
		   SottoCategoria sc = elencoSottoCategorie.get(i);
		   ris.append(i+1 + ")" + sc.getNome() + "\n");
	   }
	   
	   return ris.toString();
   }
   
   /**
    * Metodo toString() per la creazione di una stringa descrittiva contenente i vari attributi dell'oggetto Categoria.
    * Se il vettore elencoSottoCategorie e' null, si invoca il metodo toString() per ogni risorsa in elencoRisorse altrimenti
    * si invoca il metodo toString() per ogni sottoCategoria in elencoSottoCategorie
    * @return la stringa descrittiva della categoria
    */
   public String toString()
   {
	   StringBuffer ris = new StringBuffer();
	   
	   if(elencoSottoCategorie == null)
	   {
	       ris.append(String.format(DESCRIZIONE_CATEGORIA_SEMPLICE, nomeCategoria));
	   
	       for(int i = 0; i < elencoRisorse.size(); i++)
	       {
		      Risorsa r = elencoRisorse.get(i);
		      ris.append(i+1 + ")"+ r.toString());
	       }
	   }
	   else
	   {
		   ris.append(String.format(DESCRIZIONE_CATEGORIA_COMPOSTA, nomeCategoria));
		   
		   for(int i = 0; i < elencoSottoCategorie.size(); i++)
	       {
		      SottoCategoria s = elencoSottoCategorie.get(i);
		      ris.append("t" + (i+1) + ")"+ s.toString());
	       }
	   }
	   
	   return ris.toString();
   }
   
}