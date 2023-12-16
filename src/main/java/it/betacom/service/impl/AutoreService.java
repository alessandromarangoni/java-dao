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
import it.betacom.model.Autore;
import it.betacom.service.PrintService;

public class AutoreService implements PrintService<Autore> {

	public String path = "Archivio_data_odierna/";
	
	@Override
	public void saveListAsPdf() {
		AutoriDaoImpl autoriDao = new AutoriDaoImpl();
	    List<Autore> autori = autoriDao.getAllAutori();
	    Document document = new Document();
	    try {
	        PdfWriter.getInstance(document, new FileOutputStream(path +"ListaAutori.pdf"));
	        document.open();
	        for (Autore autore : autori) {
	            document.add(new Paragraph(autore.toString()));
	        }
	        document.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void saveListAsCsv() {
		AutoriDaoImpl autoriDao = new AutoriDaoImpl();
	    List<Autore> autori = autoriDao.getAllAutori();

	    try (PrintWriter writer = new PrintWriter(new File(path +"ListaAutori.csv"))) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("ID;Nome;anno di nascita;Anno di morte;sesso;nazione; ID\n");
	        for (Autore autore : autori) {
	            sb.append(autore.getId()).append(";");
	            sb.append(autore.getNome()).append(";");
	            sb.append(autore.getAnno_nascita()).append(";");
	            sb.append(autore.getAnno_morte()).append(";");
	            sb.append(autore.getSesso()).append(";");
	            sb.append(autore.getNazione()).append(";");
	        }
	        writer.write(sb.toString());
	    } catch (FileNotFoundException e) {
	        System.out.println(e.getMessage());
	    }
	}



	@Override
	public void saveListAsTxt() {
		AutoriDaoImpl autoriDao = new AutoriDaoImpl();
	    List<Autore> autori = autoriDao.getAllAutori();

	    try (PrintWriter writer = new PrintWriter(new File(path +"ListaAutori.txt"))) {
	    	 for (Autore autore : autori) {
	            writer.println(autore.toString());
	        }
	    } catch (FileNotFoundException e) {
	        System.out.println(e.getMessage());
	    }
	}

	@Override
	public void saveAsPdf(Autore autore) {
		AutoriDaoImpl autoriDao = new AutoriDaoImpl();
		Autore autoreDaDb = autoriDao.readAutore(autore.getId());
		Document document = new Document();
	    try {
	        PdfWriter.getInstance(document, new FileOutputStream(path +"Autore" + autoreDaDb.getId() + ".pdf"));
	        document.open();
	          document.add(new Paragraph(autoreDaDb.toString()));
	        document.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void saveAsCsv(Autore autore) {
		AutoriDaoImpl autoriDao = new AutoriDaoImpl();
		Autore autoreDaDb = autoriDao.readAutore(autore.getId());
	    try (PrintWriter writer = new PrintWriter(new File(path +"Autore" + autoreDaDb.getId() + ".csv"))) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("ID;Nome;anno di nascita;Anno di morte;sesso;nazione\n");
	            sb.append(autoreDaDb.getId()).append(";");
	            sb.append(autoreDaDb.getNome()).append(";");
	            sb.append(autoreDaDb.getAnno_nascita()).append(";");
	            sb.append(autoreDaDb.getAnno_morte()).append(";");
	            sb.append(autoreDaDb.getSesso()).append(";");
	            sb.append(autoreDaDb.getNazione()).append(";");
	        writer.write(sb.toString());
	    } catch (FileNotFoundException e) {
	        System.out.println(e.getMessage());
	    }
	}

	@Override
	public void saveAsTxt(Autore autore) {
		AutoriDaoImpl autoriDao = new AutoriDaoImpl();
		Autore autoreDaDb = autoriDao.readAutore(autore.getId());

	    try (PrintWriter writer = new PrintWriter(new File(path +"Autore" + autoreDaDb.getId() + ".txt"))) {
	         writer.println(autoreDaDb.toString());
	    } catch (FileNotFoundException e) {
	        System.out.println(e.getMessage());
	    }
	}

}
