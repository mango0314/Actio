package it.actio.dto;

import java.util.Date;

public class Iscrizione_ConNomeCorso_NomeAttivitaDTO {
	
	private int id;
	private int idCorso;
	private int idPersona;
	private String nome_Corso;
	private String nome_Persona;
	private String cognome_Persona;
	private int idAttivita;
	private Date dataInizio;
	private Date dataFine;
	private int stato;
	
	public int getIdCorso() {
		return idCorso;
	}
	public void setIdCorso(int idCorso) {
		this.idCorso = idCorso;
	}
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getNome_Corso() {
		return nome_Corso;
	}
	public void setNome_Corso(String nome_Corso) {
		this.nome_Corso = nome_Corso;
	}
	public String getNome_Persona() {
		return nome_Persona;
	}
	public void setNome_Persona(String nome_Persona) {
		this.nome_Persona = nome_Persona;
	}
	public String getCognome_Persona() {
		return cognome_Persona;
	}
	public void setCognome_Persona(String cognome_Persona) {
		this.cognome_Persona = cognome_Persona;
	}
	public int getIdAttivita() {
		return idAttivita;
	}
	public void setIdAttivita(int idAttivita) {
		this.idAttivita = idAttivita;
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
