package it.betacom.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import it.betacom.implement.AutoriDaoImpl;
import it.betacom.implement.GenereDaoImpl;
import it.betacom.implement.LibriDaoImpl;
import it.betacom.model.Autore;
import it.betacom.model.Genere;
import it.betacom.model.Libro;
import it.betacom.service.PrintService;

public class LibroPrintService implements PrintService<Libro>{
	
	public String path = "Archivio_data_odierna/";

	@Override
	public void saveListAsPdf() {
		
		LibriDaoImpl libriDao = new LibriDaoImpl();
	    List<Libro> libri = libriDao.getAllLibri();
	    AutoriDaoImpl autoriDao = new AutoriDaoImpl();	   
	    GenereDaoImpl generiDao = new GenereDaoImpl();
	    
	    Document document = new Document();
	    try {
	        PdfWriter.getInstance(document, new FileOutputStream(path +"ListaLibri.pdf"));
	        document.open();
	        for (Libro libro : libri) {
	        	Autore autore = autoriDao.readAutore(libro.getAutori_id());
	        	Genere genere = generiDao.readGenereById(libro.getGenere_id());
	        	
	            document.add(new Paragraph("| nome libro: " + libro.getNome() + " | numero pagine: " + libro.getNumero_pagine() +" | autore: " +  autore.getNome() + " " + autore.getCognome() + " | genere: " + genere.getDescrizione()));
	        }
	        document.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

@Override
public void saveListAsCsv() {
    LibriDaoImpl libriDao = new LibriDaoImpl();
    List<Libro> libri = libriDao.getAllLibri();
    AutoriDaoImpl autoriDao = new AutoriDaoImpl();	   
    GenereDaoImpl generiDao = new GenereDaoImpl();
    
    try (PrintWriter writer = new PrintWriter(new File(path + "ListaLibri.csv"))) {
        StringBuilder sb = new StringBuilder();
        // Aggiungi un'intestazione se necessario
        sb.append("ID;Nome;Numero Pagine;Anno;Autore nome;Autore cognome;Genere\n");
        for (Libro libro : libri) {
        	Autore autore = autoriDao.readAutore(libro.getAutori_id());
        	Genere genere = generiDao.readGenereById(libro.getGenere_id());
        	
            sb.append(libro.getId()).append(";");
            sb.append(libro.getNome()).append(";");
            sb.append(libro.getNumero_pagine()).append(";");
            sb.append(libro.getAnno()).append(";");
            sb.append(autore.getNome()).append(";");
            sb.append(autore.getCognome()).append(";");
            sb.append(genere.getDescrizione()).append("\n");
        }
        writer.write(sb.toString());
    } catch (FileNotFoundException e) {
        System.out.println(e.getMessage());
    }
}



	@Override
	public void saveListAsTxt() {
	    LibriDaoImpl libriDao = new LibriDaoImpl();
	    List<Libro> libri = libriDao.getAllLibri();
	    AutoriDaoImpl autoriDao = new AutoriDaoImpl();	   
	    GenereDaoImpl generiDao = new GenereDaoImpl();
	    
	    try (PrintWriter writer = new PrintWriter(new File(path +"ListaLibri.txt"))) {
	        for (Libro libro : libri) {
	        	Autore autore = autoriDao.readAutore(libro.getAutori_id());
	        	Genere genere = generiDao.readGenereById(libro.getGenere_id());
	            writer.println("| nome libro: " + libro.getNome() + " | numero pagine: " + libro.getNumero_pagine() +" | autore: " +  autore.getNome() + " " + autore.getCognome() + " | genere: " + genere.getDescrizione());
	        }
	    } catch (FileNotFoundException e) {
	        System.out.println(e.getMessage());
	    }
	}

	@Override
	public void saveAsPdf(Libro libro) {
	    LibriDaoImpl libriDao = new LibriDaoImpl();
	    Libro libroDaDb = libriDao.readLibro(libro.getId());
	    AutoriDaoImpl autoriDao = new AutoriDaoImpl();	   
	    GenereDaoImpl generiDao = new GenereDaoImpl();
	    Autore autore = autoriDao.readAutore(libroDaDb.getAutori_id());
    	Genere genere = generiDao.readGenereById(libroDaDb.getGenere_id());
	    
	    if (libroDaDb != null) {
	        Document document = new Document();
	        try {
	            PdfWriter.getInstance(document, new FileOutputStream(path + "Libro_" + libroDaDb.getId() + ".pdf"));
	            document.open();
	            document.add(new Paragraph("| nome libro: " + libroDaDb.getNome() + " | numero pagine: " + libroDaDb.getNumero_pagine() +" | autore: " +  autore.getNome() + " " + autore.getCognome() + " | genere: " + genere.getDescrizione()));
	            document.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Libro non trovato.");
	        }
	    }
	}

	@Override
	public void saveAsCsv(Libro libro) {
		 LibriDaoImpl libriDao = new LibriDaoImpl();
		 Libro libroDaDb = libriDao.readLibro(libro.getId());
		 AutoriDaoImpl autoriDao = new AutoriDaoImpl();	   
		 GenereDaoImpl generiDao = new GenereDaoImpl();
		 Autore autore = autoriDao.readAutore(libroDaDb.getAutori_id());
     	 Genere genere = generiDao.readGenereById(libroDaDb.getGenere_id());
     	
		 try (PrintWriter writer = new PrintWriter(new File(path + "Libro_" + libroDaDb.getId() + ".csv"))) {
		        StringBuilder sb = new StringBuilder();
		        sb.append("ID;Nome;Numero Pagine;Anno;Autore nome;Autore cognome;Genere\n");
		        sb.append(libroDaDb.getId()).append(";");
	            sb.append(libroDaDb.getNome()).append(";");
	            sb.append(libroDaDb.getNumero_pagine()).append(";");
	            sb.append(libroDaDb.getAnno()).append(";");
	            sb.append(autore.getNome()).append(";");
	            sb.append(autore.getCognome()).append(";");
	            sb.append(genere.getDescrizione()).append("\n");
		            
		        writer.write(sb.toString());
		    } catch (FileNotFoundException e) {
		        System.out.println(e.getMessage());
		    }
	}

	@Override
	public void saveAsTxt(Libro libro) {
		 LibriDaoImpl libriDao = new LibriDaoImpl();
		 Libro libroDaDb = libriDao.readLibro(libro.getId());
		 AutoriDaoImpl autoriDao = new AutoriDaoImpl();	   
		 GenereDaoImpl generiDao = new GenereDaoImpl();
		 Autore autore = autoriDao.readAutore(libroDaDb.getAutori_id());
     	 Genere genere = generiDao.readGenereById(libroDaDb.getGenere_id()); 
		 
		 try (PrintWriter writer = new PrintWriter(new File(path + "Libro_" + libroDaDb.getId() + ".txt"))) {
			 if (libroDaDb != null) {
		       writer.println("| nome libro: " + libroDaDb.getNome() + " | numero pagine: " + libroDaDb.getNumero_pagine() +" | autore: " +  autore.getNome() + " " + autore.getCognome() + " | genere: " + genere.getDescrizione()); 
			 }
			   
		    } catch (FileNotFoundException e) {
		        System.out.println(e.getMessage());
		    }
	}



}
