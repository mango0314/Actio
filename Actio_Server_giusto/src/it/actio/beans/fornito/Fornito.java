package it.actio.beans.fornito;

public class Fornito {
	private int idAttivita;
	private int idCorso;
	
	public int getIdAttivita() {
		return idAttivita;
	}
	public void setIdAttivita(int idAttivita) {
		this.idAttivita = idAttivita;
	}
	@Override
	public String toString() {
		return "Fornito [idAttivita=" + idAttivita + ", idCorso=" + idCorso + "]";
	}
	public int getIdCorso() {
		return idCorso;
	}
	public void setIdCorso(int idCorso) {
		this.idCorso = idCorso;
	}

}
