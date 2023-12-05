package it.betacom.Dao;

import java.util.List;

import it.betacom.model.Genere;

public interface GenereDao {
	
	List<Genere> getAllGenere();
	
	void createGenere(Genere genere);
	
	Genere readGenereById(int idGenere);
	
	void updateGenere(Genere genere);
	
	void deleteGenere(Genere genere);
}
