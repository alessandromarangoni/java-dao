package it.betacom.ProgettoDao;

import java.util.List;
import java.util.Scanner;

import it.betacom.implement.AutoriDaoImpl;
import it.betacom.model.Autore;
import it.betacom.service.impl.AutoreService;


public class MainAutori {

	public static void main(String[] args) {
		
		 Scanner scanner = new Scanner(System.in);
	        AutoriDaoImpl autoriDao = new AutoriDaoImpl();
	        AutoreService autoreS = new AutoreService();

	        boolean continua = true;
	        while (continua) {
	            System.out.println("Seleziona un'opzione:");
	            System.out.println("1. Mostra tutti gli autori");
	            System.out.println("2. Crea un nuovo autore");
	            System.out.println("3. Mostra autore per ID");
	            System.out.println("4. Aggiorna autore");
	            System.out.println("5. Elimina autore");
	            System.out.println("6. esporta dati");
	            System.out.println("7. Esci");
	            System.out.print("Scelta: ");
	            
	            int scelta = scanner.nextInt();
	            switch (scelta) {
	                case 1:
	                    showAll(autoriDao);
	                    break;
	                case 2:
	                    createAuthor(scanner, autoriDao);
	                    break;
	                case 3:
	                    showAuthorById(scanner, autoriDao);
	                    break;
	                case 4:
	                    updateAuthor(scanner, autoriDao);
	                    break;
	                case 5:
	                	deleteAuthor(scanner, autoriDao);
	                    break;
	                case 6:
	                	extractData(scanner,autoreS,autoriDao);
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

	public static Autore createAuthor(Scanner scanner, AutoriDaoImpl autoriDao) {
        System.out.println("Inserire il nome dell'autore:");
        String nome = scanner.next();
        System.out.println("Inserire il cognome dell'autore:");
        String cognome = scanner.next();
        System.out.println("Inserire la data di nascita");
        int annoNascita = scanner.nextInt();
        System.out.println("Inserire l'anno di morte se defunto, altrimenti digta 0");
        int annoMorte = scanner.nextInt();
        System.out.println("Inserire il sesso M o F");
        String sesso = scanner.next();
        System.out.println("inserire Nazionalita");
        String nazione = scanner.next();
        
        Autore nuovoAutore = new Autore(0, nome, cognome,annoNascita, annoMorte, sesso, nazione);
        autoriDao.createAutore(nuovoAutore);
        System.out.println("Autore creato con successo!");
        
        return nuovoAutore;
    }
	
	 public static void showAuthorById(Scanner scanner, AutoriDaoImpl autoriDao) {
	        System.out.println("Inserire l'ID dell'autore da visualizzare:");
	        int id = scanner.nextInt();
	        Autore autore = autoriDao.readAutore(id);
	        if (autore != null) {
	            System.out.println(autore.toString());
	        } else {
	            System.out.println("Autore non trovato.");
	        }
	    }
	 
	 public static void updateAuthor(Scanner scanner, AutoriDaoImpl autoriDao) {
	        System.out.println("Inserire l'ID dell'autore da aggiornare:");
	        int id = scanner.nextInt();
	        Autore autore = autoriDao.readAutore(id);
	        
	         if (autore != null) {
	        	 
	        	boolean continua = true;
	 	        while (continua) {
	 	            System.out.println("Seleziona un'opzione:");
	 	            System.out.println("1. Nome");
	 	            System.out.println("2. Cognome");
	 	            System.out.println("3. anno di nascita");
	 	            System.out.println("4. anno di morte");
	 	            System.out.println("5. Sesso");
	 	            System.out.println("6. Nazione");
	 	            System.out.println("7. Esci");
	 	            System.out.print("Scelta: ");
	 	            
	 	            int scelta = scanner.nextInt();
	 	            switch (scelta) {
	 	                case 1:
	 	                	System.err.println("Inserisci il nome");
	 	                	autore.setNome(scanner.next());
	 	                    break;
	 	                case 2:
							System.out.println("inserisci il cognome");	
	 	                	autore.setCognome(scanner.next());
	 	                    break;
	 	                case 3:
							System.out.println("inserisci l'anno di nascita");
	 	                	autore.setAnno_nascita(scanner.nextInt());
	 	                    break;
	 	                case 4:
							System.out.println("inserisci l'anno di morte");	
	 	                	autore.setAnno_morte(scanner.nextInt());
	 	                    break;
	 	                case 5:	
							System.out.println("inserisci il sesso");
	 	                	autore.setSesso(scanner.next());
	 	                    break;
	 	              	case 6:
							System.out.println("inserisci la nazione");
	 	                    autore.setNazione(scanner.next());
	 	                    break;
	 	                case 7:
	 	                    continua = false;
	 	                    break;
	 	                default:
	 	                    System.out.println("Scelta non valida.");
	 	            }
	 	            System.out.println();
	 	        }
	            autoriDao.UpdateAutore(autore);
	            System.out.println("Autore aggiornato con successo!");
	        } else {
	            System.out.println("Autore non trovato.");
	        }
	    }
	 
	    public static void deleteAuthor(Scanner scanner, AutoriDaoImpl autoriDao) {
	        System.out.println("Inserire l'ID dell'autore da eliminare:");
	        int id = scanner.nextInt();
	        Autore autore = autoriDao.readAutore(id);
	        if (autore != null) {
	            autoriDao.DeleteAutore(autore);
	            System.out.println("Autore eliminato con successo.");
	        } else {
	            System.out.println("Autore non trovato.");
	        }
	    }
	
		public static void showAll(AutoriDaoImpl autoriDao) {
			List<Autore> autori = autoriDao.getAllAutori();
			for(Autore l : autori) {
				System.out.println(l.toString());
			};
		}
		
		
		 public static void extractData(Scanner scanner, AutoreService autoreS, AutoriDaoImpl autoriDao) {
		        System.out.println("Seleziona l'operazione di esportazione dei dati:");
		        System.out.println("1. Salva l'elenco degli autori come PDF");
		        System.out.println("2. Salva l'elenco degli autori come CSV");
		        System.out.println("3. Salva l'elenco degli autori come TXT");
		        System.out.println("4. Salva un autore specifico come PDF");
		        System.out.println("5. Salva un autore specifico come CSV");
		        System.out.println("6. Salva un autore specifico come TXT");
		        System.out.print("Scelta: ");
		        int scelta = scanner.nextInt();
		        int id = 0;
		        Autore autore = null;
		        switch (scelta) {
		            case 1:
		                autoreS.saveListAsPdf();
						System.out.println("lista esportate correttamente");
		                break;
		            case 2:
		                autoreS.saveListAsCsv();
		                System.out.println("lista esportate correttamente");
		                break;
		            case 3:
		                autoreS.saveListAsTxt();
		                System.out.println("lista esportate correttamente");
		                break;
		            case 4:
		                System.out.println("seleziona l autore da esportare in PDF");
		                id =scanner.nextInt();
		                autore = autoriDao.readAutore(id);
		                autoreS.saveAsPdf(autore);
		                System.out.println("autore esportato");
		                break;
		            case 5:
		            	System.out.println("seleziona l autore da esportare in CSV");
		                id =scanner.nextInt();
		                autore = autoriDao.readAutore(id);
		                autoreS.saveAsCsv(autore);
		                System.out.println("autore esportato");
		                break;
		            case 6:
		            	System.out.println("seleziona l autore da esportare in TXT");
		                id =scanner.nextInt();
		                autore = autoriDao.readAutore(id);
		                autoreS.saveAsTxt(autore);
		                System.out.println("autore esportato");
		                break;
		            default:
		                System.out.println("Scelta non valida.");
		        }
		    }
		
	
}
