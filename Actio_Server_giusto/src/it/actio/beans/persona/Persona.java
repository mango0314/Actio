package it.actio.beans.persona;

import java.util.Date;

public class Persona {
	private int id;
	private String nome;
	private String cognome;
	private Date data_di_nascita;
	private int altezza;
	private double peso;
	private String foto;
	private String certificatolink;
	
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
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public String getCertificatolink() {
		return certificatolink;
	}
	public void setCertificatolink(String certificatolink) {
		this.certificatolink = certificatolink;
	}
	@Override
	public String toString() {
		return "Persona [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", data_di_nascita=" + data_di_nascita
				+ ", altezza=" + altezza + ", peso=" + peso + ", foto=" + foto + ", certificatolink=" + certificatolink
				+ "]";
	}

}
