package it.betacom.model;

public class Autore {
	private int id;
	private String nome;
	private String cognome;
	private int anno_nascita;
	private int anno_morte;
	private String sesso;
	private String nazione;
	
	/**
	 * @param id
	 * @param nome
	 * @param cognome
	 * @param anno_nascita
	 * @param anno_morte
	 * @param sesso
	 * @param nazione
	 */
	public Autore(int id, String nome, String cognome, int anno_nascita, int anno_morte, String sesso, String nazione) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.anno_nascita = anno_nascita;
		this.anno_morte = anno_morte;
		this.sesso = sesso;
		this.nazione = nazione;
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public int getAnno_nascita() {
		return anno_nascita;
	}

	public void setAnno_nascita(int anno_nascita) {
		this.anno_nascita = anno_nascita;
	}

	public int getAnno_morte() {
		return anno_morte;
	}

	public void setAnno_morte(int anno_morte) {
		this.anno_morte = anno_morte;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getNazione() {
		return nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	@Override
	public String toString() {
		return "Autore [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", anno_nascita=" + anno_nascita
				+ ", anno_morte=" + anno_morte + ", sesso=" + sesso + ", nazione=" + nazione + "]";
	}
	
	
}
