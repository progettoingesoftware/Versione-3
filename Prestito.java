package it.ing.sw.v3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import it.ing.sw.v1.Fruitore;
import it.ing.sw.v2.Categoria;
import it.ing.sw.v2.Risorsa;

public class Prestito 
{
	private LocalDate dataDiInizioPrestito;
	private LocalDate dataDiScadenzaPrestito;
	private Categoria categoriaAssociata;
	private Fruitore fruitoreAssociato;
	private Risorsa risorsaInPrestito;
	
	public static final String DESCRIZIONE_PRESTITO = "\nCategoria: %s\nFruitore: %s\nRisorsa: %s\nData di inizio prestito: %s\nData di scadenza prestito: %s\n";
    public static final String FORMATO_DATA = "dd/MM/yyyy";

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
    	    
      	ris.append(String.format(DESCRIZIONE_PRESTITO, categoriaAssociata.getNome(), fruitoreAssociato.getUsername(), risorsaInPrestito, dataDiInizioPrestito.format(formatter), dataDiScadenzaPrestito.format(formatter)));
        return ris.toString();
    } 
    
}
