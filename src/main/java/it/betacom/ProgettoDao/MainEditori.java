package it.betacom.ProgettoDao;

import it.betacom.implement.EditoriDaoImpl;
import it.betacom.model.Editori;

public class MainEditori {

	public static void main(String[] args) {
		
    	EditoriDaoImpl editori = new EditoriDaoImpl();
		
		
    	System.out.println("************ parte 2 ************");
    	System.out.println();
    	System.out.println();
    	
    	
    	Editori pistola = new Editori(0, "pistola");
    	System.out.println();
    	
    	System.out.println("*************stampo tutti gli editori **************");
    	editori.getAllEditori();
    	System.out.println();
    	
    	System.out.println("*************creato nuovo genere **************");
    	editori.createEditore(pistola);
    	System.out.println();
    	
    	System.out.println("*************stampo tutti gli editori per controllo**************");
    	editori.getAllEditori();
    	System.out.println();
    	
    	System.out.println("*************stampo editore n 2 **************");
		editori.readEditore(2);
    	System.out.println();
    	
    	System.out.println("*************aggiorno nome editore nell istanza in editoreAggiornato **************");
		pistola.setNome("editoreAggiornato");
    	System.out.println();
    	
    	System.out.println("*************aggiorno sul db passando l istanza come parametro**************");
		editori.updateEditore(pistola);
    	System.out.println();
    	
    	System.out.println("*************stampo tutti gli editori per controllo **************");
		editori.getAllEditori();
    	System.out.println();
    	
    	System.out.println("*************elimino editore editoreAggiornato **************");
		editori.deleteEditori(pistola);
    	System.out.println();
    	
    	System.out.println("*************stampo tutti gli editori **************");
		editori.getAllEditori();
    	System.out.println();
	}

}
