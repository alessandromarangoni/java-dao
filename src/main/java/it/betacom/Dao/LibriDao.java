package it.betacom.Dao;

import java.util.List;

import it.betacom.model.Libro;

public interface LibriDao {
	List<Libro> getAllLibri();
	void createLibro(Libro libro);
	Libro readLibro(int id);
	void UpdateLibro(Libro libro);
	void deleteLibro(Libro libro);
}
