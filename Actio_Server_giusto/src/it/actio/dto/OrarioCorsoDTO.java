package it.actio.dto;


public class OrarioCorsoDTO {

    private int id;
    private int idCorso;
    private int giornoSettimana;
    private String giornoSettimanaLabel; 
    private String orarioInizio;
    private String orarioFine;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdCorso() {
        return idCorso;
    }
    public void setIdCorso(int idCorso) {
        this.idCorso = idCorso;
    }

    public int getGiornoSettimana() {
        return giornoSettimana;
    }
    public void setGiornoSettimana(int giornoSettimana) {
        this.giornoSettimana = giornoSettimana;
    }

    public String getGiornoSettimanaLabel() {
        return giornoSettimanaLabel;
    }
    public void setGiornoSettimanaLabel(String giornoSettimanaLabel) {
        this.giornoSettimanaLabel = giornoSettimanaLabel;
    }

	public void setOrarioInizio(String orarioInizio) {
		this.orarioInizio = orarioInizio;
	}
	public void setOrarioFine(String orarioFine) {
		this.orarioFine = orarioFine;
	}
	public String getOrarioInizio() {
		return orarioInizio;
	}
	public String getOrarioFine() {
		return orarioFine;
	}
}
