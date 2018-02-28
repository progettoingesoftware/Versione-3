package it.ing.sw.v3;

import java.time.LocalDate;
import java.util.Vector;

public class ArchivioPrestiti 
{
	private Vector <Prestito> elencoPrestiti;
	
	public ArchivioPrestiti()
	{
		elencoPrestiti = new Vector <Prestito> ();
	}
	
    public Prestito getPrestito(Categoria c, Fruitore f, Risorsa r)
    {
      	for(int i = 0; i < elencoPrestiti.size(); i++)
	    {
	    	  Prestito p = elencoPrestiti.get(i);
	    	  
	    	  if((p.getCategoriaAssociata()).equals(c) && (p.getFruitoreAssociato().equals(f)) && (p.getRisorsaInPrestito().equals(r)))
	    			   return p;
	    }
	    
	    return null;
    }
    
    public String getPrestiti(String fru)
    {
    	Vector <Prestito> prestitiInCorso = new Vector <Prestito> ();
    	
      	for(int i = 0; i < elencoPrestiti.size(); i++)
	    {
	    	  Prestito p = elencoPrestiti.get(i);
	    	  
	    	  if(((p.getFruitoreAssociato().getUsername().equals(fru))))
	    			   prestitiInCorso.add(p);
	    }
	    
		for(int i = 0; i < prestitiInCorso.size(); i++)
		{
			Prestito p = prestitiInCorso.get(i);
			ris.append(i+1 + ")" + p.toString());
		}
		
		return ris;
    }
    
    public void scadenzaPrestito()
    {
   	 	for(int i = 0; i < elencoPrestiti.size() ; i++)
   	 	{
   	 		Prestito p = elencoPrestiti.get(i);	
   	 		
   	 		if((LocalDate.now().isAfter(p.getDataDiScadenzaPrestito())))
	    	 			elencoPrestiti.remove(p);    	    	 			
   	 	}
	   
    }
    
    public boolean prorogaPrestito(Fruitore f, Risorsa r)
    {
   	 	for(int i = 0; i < elencoPrestiti.size() ; i++)
   	 	{
   	 			Prestito p = elencoPrestiti.get(i);
   	 			
   	 			if(p.getFruitoreAssociato().equals(f) && p.getRisorsaInPrestito().equals(r))
   	 			{
   	 				/**
   	 				 * La verifica della data avviene mediante due if concatenati:
   	 				 * il primo verifica che la data corrente preceda quella di scadenza indicata per lo specifico fruitore;
   	 				 * il secondo verifica che la data corrente succeda quella di scadenza (indicata per lo specifico fruitore) diminuita di un periodo di 10 giorni
   	 				 */
   	 				if((LocalDate.now().isBefore(p.getDataDiScadenzaPrestito())))
   	 				{
       	 				if((LocalDate.now().isAfter(p.getDataDiScadenzaPrestito().minusDays(p.getCategoriaAssociata().getNumeroGiorniRichiestaProroga()))))
       	 				{
   	 						p.setDataDiScadenzaPrestito(LocalDate.now().plusDays(p.getCategoriaAssociata().getNumeroMaxGiorniProroga()));;
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
	
	public boolean controlloPrestito(Categoria c, Fruitore f)
	{
		int num = 0;
		
      	for(int i = 0; i < elencoPrestiti.size(); i++)
	    {
	    	  Prestito ut = elencoPrestiti.get(i);
	    	  
	    	  if((ut.getCategoriaAssociata()).equals(c) && (ut.getFruitoreAssociato().equals(f)))
	    			   num += 1;
	    }
	    
	    if(c.getNumeroMaxRisorseInPrestito() < num)
	    	return true;
	    else
	    	return false;
	}
	
	
}
