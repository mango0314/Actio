package it.actio.dto;

import java.util.Date;

public class UtenteDTO {

	public UtenteDTO(int id, String email, String password, int ruolo, Integer idPersona, Integer idAttivita,
			String nome, String cognome, Date data_di_nascita, int altezza, double peso, String fotoPath) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.ruolo = ruolo;
		this.idPersona = idPersona;
		this.idAttivita = idAttivita;
		this.nome = nome;
		this.cognome = cognome;
		this.data_di_nascita = data_di_nascita;
		this.altezza = altezza;
		this.peso = peso;
		FotoPath = fotoPath;
	}
	private int id;
	private String email;
	private String password;
	private int ruolo;
	private Integer idPersona;
	private Integer idAttivita;
	private String nome;
	private String cognome;
	private Date data_di_nascita;
	private int altezza;
	private double peso;
	private String FotoPath;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getFotoPath() {
		return FotoPath;
	}
	public void setFotoPath(String fotoPath) {
		FotoPath = fotoPath;
	}
	public int getRuolo() {
		return ruolo;
	}
	public void setRuolo(int ruolo) {
		this.ruolo = ruolo;
	}
	public Integer getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}
	public Integer getIdAttivita() {
		return idAttivita;
	}
	public void setIdAttivita(Integer idAttivita) {
		this.idAttivita = idAttivita;
	}
}
