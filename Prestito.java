package it.ing.sw.v3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Prestito 
{
	private LocalDate dataDiInizioPrestito;
	private LocalDate dataDiScadenzaPrestito;
	private Categoria categoriaAssociata;
	private Fruitore fruitoreAssociato;
	private Risorsa risorsaInPrestito;
	
 //////   public static final String DESCRIZIONE_PRESTITO = "\nNome: %s\nCognome: %s\nUsername: %s\nPassword: %s\nData di nascita: %s\nData di iscrizione: %s\nData di scadenza: %s\n";
	
	public Prestito(Categoria c, Fruitore f, Risorsa r)
	{
		this.dataDiInizioPrestito = LocalDate.now();
		this.categoriaAssociata = c;
		this.dataDiScadenzaPrestito = dataDiInizioPrestito.plusDays(categoriaAssociata.getNumeroMaxGiorniPrestito());
		this.fruitoreAssociato = f;
		this.risorsaInPrestito = r;
	}
	
	public LocalDate getDataDiInizioPrestito()
	{
		return dataDiInizioPrestito;
	}
	
	public LocalDate getDataDiScadenzaPrestito()
	{
		return dataDiScadenzaPrestito;
	}
	
	public Categoria getCategoriaAssociata()
	{
		return categoriaAssociata;
	}
	
	public Fruitore getFruitoreAssociato()
	{
		return fruitoreAssociato;
	}
	
	public Risorsa getRisorsaInPrestito()
	{
		return risorsaInPrestito;
	}
	
	public void setDataDiScadenzaPrestito(LocalDate nuovads)
	{
		dataDiScadenzaPrestito = nuovads;
	}

    public String toString()
    {
      	StringBuffer ris = new StringBuffer();
      	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_DATA);
    	    
     /// 	ris.append(String.format(DESCRIZIONE_FRUITORE, getNome(), getCognome(), getUsername(), getPassword(), dataDiNascita.format(formatter), dataDiIscrizione.format(formatter), dataDiScadenza.format(formatter)));
        return ris.toString();
    } 
    
	
}
