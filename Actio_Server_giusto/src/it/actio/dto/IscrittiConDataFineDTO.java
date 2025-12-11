package it.actio.dto;

import java.util.Date;

public class IscrittiConDataFineDTO {
	
	private int id;
	private String nome;
	private String cognome;
	private Date data_di_nascita;
	private int altezza;
	private double peso;
	private Date dataFineIscrizione;
	
	public String getDataFineIscrizioneFormatted() {
	    if (dataFineIscrizione == null) return null;
	    return new java.text.SimpleDateFormat("dd/MM/yyyy").format(dataFineIscrizione);
	}

	public String getDataDiNascitaFormatted() {
	    if (dataFineIscrizione == null) return null;
	    return new java.text.SimpleDateFormat("dd/MM/yyyy").format(data_di_nascita);
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
	public Date getData_di_nascita() {
		return data_di_nascita;
	}
	public void setData_di_nascita(Date data_di_nascita) {
		this.data_di_nascita = data_di_nascita;
	}
	public int getAltezza() {
		return altezza;
	}
	public void setAltezza(int altezza) {
		this.altezza = altezza;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public Date getDataFineIscrizione() {
		return dataFineIscrizione;
	}
	public void setDataFineIscrizione(Date dataFineIscrizione) {
		this.dataFineIscrizione = dataFineIscrizione;
	}

}
