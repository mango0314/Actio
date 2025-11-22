package it.actio.beans.orario_corso;

import java.sql.Time;

public class Orario_corso {
	private int id;
	private int idCorso;
	private int giorno_settimana;
	private Time orarioInizio;
	private Time orarioFine;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCorso() {
		return idCorso;
	}
	public void setIdCorso(int idCorso) {
		this.idCorso = idCorso;
	}
	public int getGiorno_settimana() {
		return giorno_settimana;
	}
	public void setGiorno_settimana(int giorno_settimana) {
		this.giorno_settimana = giorno_settimana;
	}
	public Time getOrarioInizio() {
		return orarioInizio;
	}
	public void setOrarioInizio(Time orarioInizio) {
		this.orarioInizio = orarioInizio;
	}
	public Time getOrarioFine() {
		return orarioFine;
	}
	public void setOrarioFine(Time orarioFine) {
		this.orarioFine = orarioFine;
	}
	@Override
	public String toString() {
		return "Orario_corso [id=" + id + ", idCorso=" + idCorso + ", giorno_settimana=" + giorno_settimana
				+ ", orarioInizio=" + orarioInizio + ", orarioFine=" + orarioFine + "]";
	}

}
