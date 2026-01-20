package it.actio.dto;

public class CorsoConAttivitaDTO {
	
	private int id;
    private String nomeCorso;
    private String descrizione;
    private int capienza;
    private int tipo;
    private String fotoPath;
    private String nomeAttivita;
    private int postiRimasti;
    private int stato; 
    
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
	public int getStato() {
		return stato;
	}
	public void setStato(int stato) {
		this.stato = stato;
	}
	public String getFotoPath() {
		return fotoPath;
	}
	public void setFotoPath(String fotoPath) {
		this.fotoPath = fotoPath;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	


}
