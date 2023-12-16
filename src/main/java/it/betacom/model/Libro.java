package it.betacom.model;

public class Libro {
	private int id;
	private String nome;
	private int numero_pagine;
	private int anno;
	private int autori_id;
	private int genere_id;
	private int editore_id;
	/**
	 * @param id
	 * @param nome
	 * @param numero_pagine
	 * @param anno
	 */
	public Libro(int id, String nome, int numero_pagine, int anno) {
		super();
		this.id = id;
		this.nome = nome;
		this.numero_pagine = numero_pagine;
		this.anno = anno;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getNumero_pagine() {
		return numero_pagine;
	}
	public void setNumero_pagine(int numero_pagine) {
		this.numero_pagine = numero_pagine;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public int getAutori_id() {
		return autori_id;
	}
	public void setAutori_id(int autori_id) {
		this.autori_id = autori_id;
	}
	public int getGenere_id() {
		return genere_id;
	}
	public void setGenere_id(int genere_id) {
		this.genere_id = genere_id;
	}
	public int getEditore_id() {
		return editore_id;
	}
	public void setEditore_id(int editore_id) {
		this.editore_id = editore_id;
	}

	@Override
	public String toString() {
	    return "Libro id=" + id + ", nome=" + nome + ", numero_pagine=" + numero_pagine +
	           ", anno=" + anno + ", autori_id=" + autori_id + ", genere_id=" + genere_id +
	           ", editore_id=" + editore_id ;
	}
	
	
}
