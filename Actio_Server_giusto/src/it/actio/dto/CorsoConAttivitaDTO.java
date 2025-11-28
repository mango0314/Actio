package it.actio.dto;

public class CorsoConAttivitaDTO {
	
	private int id;
    private String nomeCorso;
    private String descrizione;
    private int capienza;
    private String nomeAttivita;
    private int postiRimasti;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeCorso() {
		return nomeCorso;
	}
	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getCapienza() {
		return capienza;
	}
	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}
	public String getNomeAttivita() {
		return nomeAttivita;
	}
	public void setNomeAttivita(String nomeAttivita) {
		this.nomeAttivita = nomeAttivita;
	}
	public int getPostiRimasti() {
		return postiRimasti;
	}
	public void setPostiRimasti(int postiRimasti) {
		this.postiRimasti = postiRimasti;
	}
	


}
