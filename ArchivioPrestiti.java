package it.ing.sw.v3;

import java.time.LocalDate;
import java.util.Vector;

import it.ing.sw.v1.Fruitore;
import it.ing.sw.v2.Categoria;
import it.ing.sw.v2.Risorsa;

public class ArchivioPrestiti 
{
	private Vector <Prestito> elencoPrestiti;
	
	public static final String INTESTAZIONE_ELENCO = "Elenco dei prestiti in corso: \n";
	//public static final String INTESTAZIONE_RICERCA_RISORSE = "Elenco delle risorse trovate: \n";

	public ArchivioPrestiti()
	{
		elencoPrestiti = new Vector <Prestito> ();
	}
	
	public Vector<Prestito> getElencoPrestiti()
	{
		return elencoPrestiti;
	}
	
    public Prestito getPrestito(Categoria c, Fruitore f, Risorsa r, LocalDate d)
    {
      	for(int i = 0; i < elencoPrestiti.size(); i++)
	    {
	    	  Prestito p = elencoPrestiti.get(i);
	    	  
	    	  if( p.getCategoriaAssociata().equals(c) && p.getFruitoreAssociato().equals(f) && p.getRisorsaInPrestito().equals(r) && p.getDataDiInizioPrestito().equals(d) )
	    			   return p;
	    }
	    
	    return null;
    }
    
    public String getPrestiti(String f)
    {
    	Vector <Prestito> prestitiInCorso = new Vector <Prestito> ();
   	    StringBuffer ris = new StringBuffer();
   	    ris.append(INTESTAZIONE_ELENCO);
   	    
      	for(int i = 0; i < elencoPrestiti.size(); i++)
	    {
	    	  Prestito p = elencoPrestiti.get(i);
	    	  
	    	  if(((p.getFruitoreAssociato().getUsername().equals(f))))
	    			   prestitiInCorso.add(p);
	    }
	    
		for(int i = 0; i < prestitiInCorso.size(); i++)
		{
			Prestito p = prestitiInCorso.get(i);
			ris.append(i+1 + ")" + p.toString());
		}
		
		return ris.toString();
    }
    
    public Vector<Prestito> getVettorePrestiti(String f)
    {
    	Vector <Prestito> prestitiInCorso = new Vector <Prestito> ();
   	    
      	for(int i = 0; i < elencoPrestiti.size(); i++)
	    {
	    	  Prestito p = elencoPrestiti.get(i);
	    	  
	    	  if(((p.getFruitoreAssociato().getUsername().equals(f))))
	    			   prestitiInCorso.add(p);
	    }
	    
		return prestitiInCorso;
    }
    
    public void scadenzaPrestito()
    {
   	 	for(int i = 0; i < elencoPrestiti.size() ; i++)
   	 	{
   	 		Prestito p = elencoPrestiti.get(i);	
   	 		
   	 		if((LocalDate.now().isAfter(p.getDataDiScadenzaPrestito())))
   	 		{
   	 			elencoPrestiti.remove(p);
   	 		}
 	    	 			
   	 	}
	   
    }
    
    public boolean prorogaPrestito(Prestito daProrogare)
    {
   	 	for(int i = 0; i < elencoPrestiti.size() ; i++)
   	 	{
   	 			Prestito p = elencoPrestiti.get(i);
   	 			
   	 			if( p.getCategoriaAssociata().equals(daProrogare.getCategoriaAssociata()) && p.getFruitoreAssociato().equals(daProrogare.getFruitoreAssociato()) && p.getRisorsaInPrestito().equals(daProrogare.getRisorsaInPrestito()) && p.getDataDiInizioPrestito().equals(daProrogare.getDataDiInizioPrestito()) )
   	 			{
   	 				/**
   	 				 * La verifica della data avviene mediante due if concatenati:
   	 				 * il primo verifica che la data corrente preceda quella di scadenza indicata per lo specifico fruitore;
   	 				 * il secondo verifica che la data corrente succeda quella di scadenza (indicata per lo specifico prestito) diminuita di un periodo opportuno
   	 				 */
   	 				if((LocalDate.now().isBefore(daProrogare.getDataDiScadenzaPrestito())))
   	 				{
       	 				if((LocalDate.now().isAfter(daProrogare.getDataDiScadenzaPrestito().minusDays(daProrogare.getCategoriaAssociata().getNumeroGiorniRichiestaProroga()))))
       	 				{
   	 						p.setDataDiScadenzaPrestito(LocalDate.now().plusDays(p.getCategoriaAssociata().getNumeroMaxGiorniProroga()));
       	 					return true;
       	 				}
       	 				
   	 				}
   	 				
   	 			}
   	 			
   	 	}
	    
   	 	return false;
    }
    
	public void aggiungiPrestito(Prestito p)
	{
		elencoPrestiti.add(p);
	}
	
	public void rimuoviPrestito(Prestito p)
	{
		elencoPrestiti.remove(p);
	}
	
	public boolean controlloPrestito(Categoria c, Fruitore f)
	{
		int num = 0;
		
      	for(int i = 0; i < elencoPrestiti.size(); i++)
	    {
	    	  Prestito p = elencoPrestiti.get(i);
	    	  
	    	  if( p.getCategoriaAssociata().equals(c) && p.getFruitoreAssociato().equals(f) )
	    		  	num += 1;
	    }
	    
	    if(c.getNumeroMaxRisorseInPrestito() < num)
	    	return true;
	    else
	    	return false;
	}
	
	public boolean controlloDisponibilitaRisorsa(Risorsa r)
	{
		int num = 0;
		
      	for(int i = 0; i < elencoPrestiti.size(); i++)
	    {
	    	  Prestito p = elencoPrestiti.get(i);
	    	  
	    	  if( p.getRisorsaInPrestito().equals(r) )
	    		  	num += 1;
	    }
	    
	    if(r.getNumLicenze() < num)
	    	return true;
	    else
	    	return false;
	}
	
    public Vector<Risorsa> ricercaRisorsaPerTitolo(String titolo)
    {
    	Vector <Prestito> prestitiInCorso = new Vector <Prestito> ();
    	Vector <Risorsa> risorseTrovate = new Vector <Risorsa> ();

   	    
      	for(int i = 0; i < elencoPrestiti.size(); i++)
	    {
	    	  Prestito p = elencoPrestiti.get(i);
	    	  
	    	  if( p.getRisorsaInPrestito().getTitolo().contains(titolo) )
	    			   prestitiInCorso.add(p);
	    }
	    
		for(int i = 0; i < prestitiInCorso.size(); i++)
		{
			Prestito p = prestitiInCorso.get(i);
			risorseTrovate.add( p.getRisorsaInPrestito() );
		}
		
		return risorseTrovate;
    }
	    
	    public Vector<Risorsa> ricercaRisorsaPerAutore(String autore)
	    {
	    	Vector <Prestito> prestitiInCorso = new Vector <Prestito> ();
	    	Vector <Risorsa> risorseTrovate = new Vector <Risorsa> ();
	   	    
	      	for(int i = 0; i < elencoPrestiti.size(); i++)
		    {
		    	  Prestito p = elencoPrestiti.get(i);
		    	  
		    	  if( p.getRisorsaInPrestito().getAutore().contains(autore) )
		    			   prestitiInCorso.add(p);
		    }
		    
			for(int i = 0; i < prestitiInCorso.size(); i++)
			{
				Prestito p = prestitiInCorso.get(i);
				risorseTrovate.add( p.getRisorsaInPrestito() );
			}
			
			return risorseTrovate;
	    }
	    
	    public Vector<Risorsa> ricercaRisorsaPerAnnoPubblicazione(int anno)
	    {
	    	Vector <Prestito> prestitiInCorso = new Vector <Prestito> ();
	    	Vector <Risorsa> risorseTrovate = new Vector <Risorsa> ();

	      	for(int i = 0; i < elencoPrestiti.size(); i++)
		    {
		    	  Prestito p = elencoPrestiti.get(i);
		    	  
		    	  if( p.getRisorsaInPrestito().getAnnoPub() == anno )
		    			   prestitiInCorso.add(p);
		    }
		    
			for(int i = 0; i < prestitiInCorso.size(); i++)
			{
				Prestito p = prestitiInCorso.get(i);
				risorseTrovate.add( p.getRisorsaInPrestito() );
			}
			
			return risorseTrovate;
	    }
	    
	    public Vector<Risorsa> ricercaRisorsaPerCasaEditrice(String casa)
	    {
	    	Vector <Prestito> prestitiInCorso = new Vector <Prestito> ();
	    	Vector <Risorsa> risorseTrovate = new Vector <Risorsa> ();

	      	for(int i = 0; i < elencoPrestiti.size(); i++)
		    {
		    	  Prestito p = elencoPrestiti.get(i);
		    	  
		    	  if( p.getRisorsaInPrestito().getCasaEditrice().equals(casa) )
		    			   prestitiInCorso.add(p);
		    }
		    
			for(int i = 0; i < prestitiInCorso.size(); i++)
			{
				Prestito p = prestitiInCorso.get(i);
				risorseTrovate.add( p.getRisorsaInPrestito() );
			}
			
			return risorseTrovate;
	    }

	
  /*  public String ricercaElencoRisorsaPerTitolo(String titolo)
    {
    	Vector <Prestito> prestitiInCorso = new Vector <Prestito> ();
   	    StringBuffer ris = new StringBuffer();
   	    ris.append(INTESTAZIONE_RICERCA_RISORSE);
   	    
      	for(int i = 0; i < elencoPrestiti.size(); i++)
	    {
	    	  Prestito p = elencoPrestiti.get(i);
	    	  
	    	  if( p.getRisorsaInPrestito().getTitolo().contains(titolo) )
	    			   prestitiInCorso.add(p);
	    }
	    
		for(int i = 0; i < prestitiInCorso.size(); i++)
		{
			Prestito p = prestitiInCorso.get(i);
			ris.append(i+1 + ")" + p.getRisorsaInPrestito().toString());
		}
		
		return ris.toString();
    }
    
    public String ricercaElencoRisorsaPerAutore(String autore)
    {
    	Vector <Prestito> prestitiInCorso = new Vector <Prestito> ();
   	    StringBuffer ris = new StringBuffer();
   	    ris.append(INTESTAZIONE_RICERCA_RISORSE);
   	    
      	for(int i = 0; i < elencoPrestiti.size(); i++)
	    {
	    	  Prestito p = elencoPrestiti.get(i);
	    	  
	    	  if( p.getRisorsaInPrestito().getAutore().contains(autore) )
	    			   prestitiInCorso.add(p);
	    }
	    
		for(int i = 0; i < prestitiInCorso.size(); i++)
		{
			Prestito p = prestitiInCorso.get(i);
			ris.append(i+1 + ")" + p.getRisorsaInPrestito().toString());
		}
		
		return ris.toString();
    }
    
    public String ricercaElencoRisorsaPerAnnoPubblicazione(int anno)
    {
    	Vector <Prestito> prestitiInCorso = new Vector <Prestito> ();
   	    StringBuffer ris = new StringBuffer();
   	    ris.append(INTESTAZIONE_RICERCA_RISORSE);
   	    
      	for(int i = 0; i < elencoPrestiti.size(); i++)
	    {
	    	  Prestito p = elencoPrestiti.get(i);
	    	  
	    	  if( p.getRisorsaInPrestito().getAnnoPub() == anno )
	    			   prestitiInCorso.add(p);
	    }
	    
		for(int i = 0; i < prestitiInCorso.size(); i++)
		{
			Prestito p = prestitiInCorso.get(i);
			ris.append(i+1 + ")" + p.getRisorsaInPrestito().toString());
		}
		
		return ris.toString();
    }
    
    public String ricercaElencoRisorsaPerCasaEditrice(String casa)
    {
    	Vector <Prestito> prestitiInCorso = new Vector <Prestito> ();
   	    StringBuffer ris = new StringBuffer();
   	    ris.append(INTESTAZIONE_RICERCA_RISORSE);
   	    
      	for(int i = 0; i < elencoPrestiti.size(); i++)
	    {
	    	  Prestito p = elencoPrestiti.get(i);
	    	  
	    	  if( p.getRisorsaInPrestito().getCasaEditrice().equals(casa) )
	    			   prestitiInCorso.add(p);
	    }
	    
		for(int i = 0; i < prestitiInCorso.size(); i++)
		{
			Prestito p = prestitiInCorso.get(i);
			ris.append(i+1 + ")" + p.getRisorsaInPrestito().toString());
		}
		
		return ris.toString();
    }
*/
}