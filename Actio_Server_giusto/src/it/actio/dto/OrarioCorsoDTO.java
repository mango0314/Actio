package it.actio.dto;

import java.sql.Time;

public class OrarioCorsoDTO {

    private int id;
    private int idCorso;
    private int giornoSettimana;
    private String giornoSettimanaLabel; 
    private Time orarioInizio;
    private Time orarioFine;

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

    public Time getOrarioInizio() {
        return orarioInizio;
    }
    public void setOrarioInizio(Time orarioInizio) {
        this.orarioInizio = orarioInizio;
    }

    public Time getOrarioFine() {
        return orarioFine;
    }
    public void setOrarioFine(Time orarioFine) {
        this.orarioFine = orarioFine;
    }
}
