package it.actio.beans.attivita;

public class Attivita {
	private int id;
	private String nome;
	private String logo;
	private int tipo;
	private String citta;
	
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
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Override
	public String toString() {
		return "Attivita [id=" + id + ", nome=" + nome + ", logo=" + logo + ", tipo=" + tipo + ", citta=" + citta + "]";
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}

}
