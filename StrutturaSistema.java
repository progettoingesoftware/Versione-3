package it.ing.sw.v3.p3;

import java.io.Serializable;
import java.util.Vector;

import it.ing.sw.v3.p1.*;
import it.ing.sw.v3.p2.*;

/**
 * Qusta classe contiene dei metodi per la creazione della struttura preimpostata di alcuni aspetti del sistema
 */
public class StrutturaSistema implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
	 * Metodo per la creazione e l'aggiunta di 5 operatori preimpostati all'elenco degli operatori presente in AnagraficaOperatori
	 * 
	 * Pre : ao.elenco != null
	 * 
	 * @param ao : l'istanza della classe AnagraficaOperatore a cui aggiungere gli operatori
	 */
	public static void aggiuntaOperatoriPreimpostati(AnagraficaOperatori ao)
	{
		Operatore primo = new Operatore("Stefano", "Metelli", "ste", "161095");
		Operatore secondo = new Operatore("Alba", "Pasini", "sum56", "33alb33");
		Operatore terzo = new Operatore("Marco", "Bellini", "mark4", "starwars2");
		Operatore quarto = new Operatore("Fabio", "Piccinelli", "picci", "fighter118");
		Operatore quinto = new Operatore("Ottavia", "Lauretti", "oct4565", "ppla210");
		
		ao.getElenco().addElement(primo);
		ao.getElenco().addElement(secondo);
		ao.getElenco().addElement(terzo);
		ao.getElenco().addElement(quarto);
		ao.getElenco().addElement(quinto);
	}
	
	/**
	 * Metodo per la creazione della struttura base dell'archivio
	 * 
	 * Pre: arc != null
	 * 
	 * @param arc: l'istanza della classe Archivio di cui creare la struttura
	 */
	public static void creazioneStrutturaArchivio(Archivio arc)
	{
		final int DURATA_PRESTITO = 30;
		final int DURATA_PROROGA = 20;
		final int RICHIESTA_PROROGA = 10;
		final int MAX_PRESTITI = 3;

		Categoria c = new Categoria("Libri", DURATA_PRESTITO, DURATA_PROROGA, RICHIESTA_PROROGA, MAX_PRESTITI);
		arc.aggiungiCategoria(c);
	    c.inizializzaElencoSottoCategorie();
	    
	    SottoCategoria s1 = new SottoCategoria("Didattica");
	    SottoCategoria s2 = new SottoCategoria("Classici");
	    SottoCategoria s3 = new SottoCategoria("Fantasy");
	    SottoCategoria s4 = new SottoCategoria("Per ragazzi");
	    SottoCategoria s5 = new SottoCategoria("Gialli");

	    c.aggiungiSottoCategoria(s1);
	    c.aggiungiSottoCategoria(s2);
	    c.aggiungiSottoCategoria(s3);
	    c.aggiungiSottoCategoria(s4);
	    c.aggiungiSottoCategoria(s5);
	    
	    Vector <String> a1 = new Vector <String> ();
	    a1.add("Antoine de Saint_Exupery");
	    Vector <String> a2 = new Vector <String> ();
	    a2.add("J.R.R. Tolkien");
	    Vector <String> a3 = new Vector <String> ();
	    a3.add("George Orwell");
	    Vector <String> a4 = new Vector <String> ();
	    a4.add("E.Gamma");
	    a4.add("R.Helm");
	    a4.add("R.Johnson");
	    a4.add("J.Vlissides");
	    
	    Libro l1 = new Libro(10, "Il piccolo principe", a1, 137, 2015, "Newton Compton", "italiano", "Per ragazzi");
	    Libro l2 = new Libro(5, "Il signore degli anelli", a2, 1264, 2017, "Bompiani", "italiano", "Fantasy");
	    Libro l3 = new Libro(8, "Animal Farm", a3, 112, 2008, "Penguin Books", "english", "Classici");
	    Libro l4 = new Libro(1, "Design Patterns", a4, 395, 2002, "Pearson", "italiano", "Didattica");
	    s1.aggiungiRisorsa(l4);
	    s2.aggiungiRisorsa(l3);
	    s3.aggiungiRisorsa(l2);
	    s4.aggiungiRisorsa(l1);
	}
	
}
