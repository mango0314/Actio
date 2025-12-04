package it.actio.beans.account;

public class Account {
	private int id;
	private String email;
	private String password;
	private int ruolo;
	private Integer idAttivita;
	private Integer idPersona;
	
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
	public Integer getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", email=" + email + ", password=" + password + ", ruolo=" + ruolo
				+ ", idCorso=" + idAttivita + ", idPersona=" + idPersona + "]";
	}
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
	
}
