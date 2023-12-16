package it.betacom.ProgettoDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import it.betacom.db.DBHandler;
import it.betacom.implement.EditoriDaoImpl;
import it.betacom.implement.GenereDaoImpl;
import it.betacom.model.Editori;
import it.betacom.model.Genere;


/**
 * Hello world!
 *
 */
public class MainGenere 
{

	
    public static void main( String[] args )
    {
    	GenereDaoImpl genereDaoImpl = new GenereDaoImpl();
    	
    	
    	//stampo tutti i generi
    	System.out.println("*************stampo tutti i generi**************");
    	genereDaoImpl.getAllGenere();
    	
    	Genere rosa = new Genere(0, "rosa");
    	System.out.println();
    	
    	System.out.println("*************creo genere rosa**************");
    	//creo genere rosa e stampo per verificare l aggiunta
    	genereDaoImpl.createGenere(rosa);
    	
    	System.out.println("************stampo per verificare l aggiunta***************");
    	genereDaoImpl.getAllGenere();
    	
    	System.out.println();
    	
    	System.out.println("***********loggo il genere dall' id************");
    	genereDaoImpl.readGenereById(1);
    	System.out.println();
    	
    	
    	//cambio descrizione a rosa poi faccio l udate del record 
    	System.out.println("************faccio l update***********");
    	rosa.setDescrizione("verde");
    	genereDaoImpl.updateGenere(rosa);
    	System.out.println("************stampo per verificare la modifica***************");
    	genereDaoImpl.getAllGenere();
    	System.out.println();
    	
    	//cancello rosa dal db e dal sistema
    	System.out.println("************elimino rosa***************");
    	genereDaoImpl.deleteGenere(rosa);
    	System.out.println("************stampo per verificare la modifica***************");
    	genereDaoImpl.getAllGenere();
    	rosa = null;
    	
    }
}
