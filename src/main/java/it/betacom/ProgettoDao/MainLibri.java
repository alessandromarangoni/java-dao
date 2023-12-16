package it.betacom.ProgettoDao;

import java.util.List;
import java.util.Scanner;

import it.betacom.implement.AutoriDaoImpl;
import it.betacom.implement.GenereDaoImpl;
import it.betacom.implement.LibriDaoImpl;
import it.betacom.model.Autore;
import it.betacom.model.Genere;
import it.betacom.model.Libro;
import it.betacom.service.impl.LibroPrintService;

public class MainLibri {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
        LibriDaoImpl libriDao = new LibriDaoImpl();
        LibroPrintService libroService = new LibroPrintService();

        boolean continua = true;
        while (continua) {
            System.out.println("Seleziona un'opzione:");
            System.out.println("1. Mostra tutti i libri");
            System.out.println("2. Crea un nuovo libro");
            System.out.println("3. Mostra libro per ID");
            System.out.println("4. Aggiorna libro");
            System.out.println("5. Elimina libro");
            System.out.println("6. Esporta dati");
            System.out.println("7. Esci");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            switch (scelta) {
                case 1:
                    showAll(libriDao);
                    break;
                case 2:
                    createBook(scanner, libriDao);
                    break;
                case 3:
                    showBookById(scanner, libriDao);
                    break;
                case 4:
                    updateBook(scanner, libriDao);
                    break;
                case 5:
                    deleteBook(scanner, libriDao);
                    break;
                case 6:
                    extractData(scanner, libroService, libriDao);
                    break;
                case 7:
                    continua = false;
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
            System.out.println();
        }
        scanner.close();
   }


	public static Libro createBook(Scanner scanner, LibriDaoImpl libriDao) {
	    System.out.println("Inserire il titolo del libro:");
	    String titolo = scanner.next();
	    System.out.println("Inserire il numero di pagine:");
	    int pagine = scanner.nextInt();
	    System.out.println("Inserire l'anno di pubblicazione:");
	    int anno = scanner.nextInt();
	    System.out.println("Inserire l'ID dell'autore:");
	    int autoreId = scanner.nextInt();
	    System.out.println("Inserire l'ID del genere:");
	    int genereId = scanner.nextInt();
	    System.out.println("Inserire l'ID dell'editore:");
	    int editoreId = scanner.nextInt();

	    Libro nuovoLibro = new Libro(0, titolo, pagine, anno);
	    nuovoLibro.setAutori_id(autoreId);
	    nuovoLibro.setGenere_id(genereId);
	    nuovoLibro.setEditore_id(editoreId);
	    
	    libriDao.createLibro(nuovoLibro);
	    System.out.println("Libro creato con successo!");

	    return nuovoLibro;
	}

	public static void showBookById(Scanner scanner, LibriDaoImpl libriDao) {
	    System.out.println("Inserire l'ID del libro da visualizzare:");
	    int id = scanner.nextInt();
	    Libro libro = libriDao.readLibro(id);
	    AutoriDaoImpl autoriDao = new AutoriDaoImpl();	   
	    GenereDaoImpl generiDao = new GenereDaoImpl();
	    if (libro != null) {
	    	Autore autore = autoriDao.readAutore(libro.getAutori_id());
        	Genere genere = generiDao.readGenereById(libro.getGenere_id());
	        System.out.println("| nome libro: " + libro.getNome() + " | numero pagine: " + libro.getNumero_pagine() +" | autore: " +  autore.getNome() + " " + autore.getCognome() + " | genere: " + genere.getDescrizione());
	    } else {
	        System.out.println("Libro non trovato.");
	    }
	}

	public static void updateBook(Scanner scanner, LibriDaoImpl libriDao) {
	    System.out.println("Inserire l'ID del libro da aggiornare:");
	    int id = scanner.nextInt();
	    Libro libro = libriDao.readLibro(id);

	    if (libro != null) {
	        boolean continua = true;
	        while (continua) {
	            System.out.println("Seleziona un'opzione per aggiornare:");
	            System.out.println("1. Titolo");
	            System.out.println("2. Numero di pagine");
	            System.out.println("3. Anno di pubblicazione");
	            System.out.println("4. ID Autore");
	            System.out.println("5. ID Genere");
	            System.out.println("6. ID Editore");
	            System.out.println("7. Esci");
	            System.out.print("Scelta: ");

	            int scelta = scanner.nextInt();
	            switch (scelta) {
	                case 1:
	                    System.out.println("Inserisci il nuovo titolo:");
	                    libro.setNome(scanner.next());
	                    break;
	                case 2:
	                    System.out.println("Inserisci il nuovo numero di pagine:");
	                    libro.setNumero_pagine(scanner.nextInt());
	                    break;
	                case 3:
	                    System.out.println("Inserisci il nuovo anno di pubblicazione:");
	                    libro.setAnno(scanner.nextInt());
	                    break;
	                case 4:
	                    System.out.println("Inserisci il nuovo ID Autore:");
	                    libro.setAutori_id(scanner.nextInt());
	                    break;
	                case 5:
	                    System.out.println("Inserisci il nuovo ID Genere:");
	                    libro.setGenere_id(scanner.nextInt());
	                    break;
	                case 6:
	                    System.out.println("Inserisci il nuovo ID Editore:");
	                    libro.setEditore_id(scanner.nextInt());
	                    break;
	                case 7:
	                    continua = false;
	                    break;
	                default:
	                    System.out.println("Scelta non valida.");
	            }
	            System.out.println();
	        }
	        libriDao.UpdateLibro(libro);
	        System.out.println("Libro aggiornato con successo!");
	    } else {
	        System.out.println("Libro non trovato.");
	    }
	}

	public static void deleteBook(Scanner scanner, LibriDaoImpl libriDao) {
	    System.out.println("Inserire l'ID del libro da eliminare:");
	    int id = scanner.nextInt();
	    Libro libro = libriDao.readLibro(id);
	    if (libro != null) {
	        libriDao.deleteLibro(libro);
	        System.out.println("Libro eliminato con successo.");
	    } else {
	        System.out.println("Libro non trovato.");
	    }
	}
	
	public static void showAll(LibriDaoImpl libriDao) {
		List<Libro> libri = libriDao.getAllLibri();
		for(Libro l : libri) {
			System.out.println(l.toString());
		};
	}
	
	public static void extractData(Scanner scanner, LibroPrintService libroService, LibriDaoImpl libriDao) {
	    System.out.println("Seleziona l'operazione di esportazione dei dati:");
	    System.out.println("1. Salva l'elenco dei libri come PDF");
	    System.out.println("2. Salva l'elenco dei libri come CSV");
	    System.out.println("3. Salva l'elenco dei libri come TXT");
	    System.out.println("4. Salva un libro specifico come PDF");
	    System.out.println("5. Salva un libro specifico come CSV");
	    System.out.println("6. Salva un libro specifico come TXT");
	    System.out.print("Scelta: ");
	    int scelta = scanner.nextInt();
	    int id = 0;
	    Libro libro = null;

	    switch (scelta) {
	        case 1:
	            libroService.saveListAsPdf();
	            System.out.println("Elenco dei libri esportato in PDF correttamente");
	            break;
	        case 2:
	            libroService.saveListAsCsv();
	            System.out.println("Elenco dei libri esportato in CSV correttamente");
	            break;
	        case 3:
	            libroService.saveListAsTxt();
	            System.out.println("Elenco dei libri esportato in TXT correttamente");
	            break;
	        case 4:
	            System.out.println("Seleziona il libro da esportare in PDF");
	            id = scanner.nextInt();
	            libro = libriDao.readLibro(id);
	            if (libro != null) {
	                libroService.saveAsPdf(libro);
	                System.out.println("Libro esportato in PDF");
	            } else {
	                System.out.println("Libro non trovato.");
	            }
	            break;
	        case 5:
	            System.out.println("Seleziona il libro da esportare in CSV");
	            id = scanner.nextInt();
	            libro = libriDao.readLibro(id);
	            if (libro != null) {
	                libroService.saveAsCsv(libro);
	                System.out.println("Libro esportato in CSV");
	            } else {
	                System.out.println("Libro non trovato.");
	            }
	            break;
	        case 6:
	            System.out.println("Seleziona il libro da esportare in TXT");
	            id = scanner.nextInt();
	            libro = libriDao.readLibro(id);
	            if (libro != null) {
	                libroService.saveAsTxt(libro);
	                System.out.println("Libro esportato in TXT");
	            } else {
	                System.out.println("Libro non trovato.");
	            }
	            break;
	        default:
	            System.out.println("Scelta non valida.");
	    }
	}

	
}
