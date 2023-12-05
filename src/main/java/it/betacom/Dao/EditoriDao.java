package it.betacom.Dao;

import java.util.List;

import it.betacom.model.Editori;

public interface EditoriDao {
	List<Editori> getAllEditori();
	void createEditore(Editori editori);
	Editori readEditore(int id);
	void updateEditore(Editori editori);
	void deleteEditori(Editori editori);
}
