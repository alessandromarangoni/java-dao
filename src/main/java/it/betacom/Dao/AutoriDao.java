package it.betacom.Dao;

import java.util.List;

import it.betacom.model.Autore;

public interface AutoriDao {
	List<Autore> getAllAutori();
	void createAutore(Autore autore);
	Autore readAutore(int id);
	void UpdateAutore(Autore autore);
	void DeleteAutore(Autore autore);
}
