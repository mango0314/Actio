package it.actio.beans.iscrizione;

import java.util.Date;;

public class Iscrizione {
	private int id;
	private int idPersona;
	private int idCorso;
	private Date dataInizio;
	private Date dataFine;
	private int stato; 
	
	public int getIdPersona() {
		return idPersona;
	}
	
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public int getIdCorso() {
		return idCorso;
	}
	public void setIdCorso(int idCorso) {
		this.idCorso = idCorso;
	}
	public Date getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	public Date getDataFine() {
		return dataFine;
	}
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	@Override
	public String toString() {
		return "Iscrizione [idPersona=" + idPersona + ", idCorso=" + idCorso + ", dataInizio=" + dataInizio
				+ ", dataFine=" + dataFine + ", stato=" + stato + "]";
	}

	public int getStato() {
		return stato;
	}

	public void setStato(int stato) {
		this.stato = stato;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
