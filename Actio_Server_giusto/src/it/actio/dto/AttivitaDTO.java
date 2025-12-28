package it.actio.dto;


public class AttivitaDTO {


	private int id;
	private String email;
	private String password;
	private int ruolo;
	private Integer idAttivita;
	private String nomeAttivita;
	private String citta;
	private int tipo;
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
	public int getRuolo() {
		return ruolo;
	}
	public void setRuolo(int ruolo) {
		this.ruolo = ruolo;
	}
	public Integer getIdAttivita() {
		return idAttivita;
	}
	public void setIdAttivita(Integer idAttivita) {
		this.idAttivita = idAttivita;
	}
	public String getNomeAttivita() {
		return nomeAttivita;
	}
	public void setNomeAttivita(String nomeAttivita) {
		this.nomeAttivita = nomeAttivita;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getFotoPath() {
		return FotoPath;
	}
	public void setFotoPath(String fotoPath) {
		FotoPath = fotoPath;
	}
	
	
}