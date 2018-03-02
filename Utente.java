package it.ing.sw.v1;

import java.io.Serializable;

/**
 * Questa classe rappresenta il modello di un Utente
 */
public class Utente implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;
    private String cognome;
    private String username;
    private String password;
    
    /**
     * Metodo costruttore della classe Utente
     * @param n : nome dell'utente
     * @param c : cognome dell'utente
     * @param u : username dell'utente
     * @param p : password dell'utente
     */
    public Utente(String n, String c, String u, String p)
    {
    	     this.nome = n;
    	     this.cognome = c;
    	     this.username = u;
    	     this.password = p;
    }
    
    /**
     * Metodi get per il ritorno dei vari attributi della classe Utente
     */
    public String getNome()
    {
   	     return nome;
    }
    
    public String getCognome()
    {
   	     return cognome;
    }
    
    public String getUsername()
    {
   	     return username;
    }
    
    public String getPassword()
    {
   	     return password;
    }
    
}
