package it.actio.beans.tenuto;

public class Tenuto {
	private int idPersona;
	private int idCorso;
	

	public int getIdCorso() {
		return idCorso;
	}
	public void setIdCorso(int idCorso) {
		this.idCorso = idCorso;
	}
	@Override
	public String toString() {
		return "Tenuto [idPersona=" + idPersona + ", idCorso=" + idCorso + "]";
	}
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

}
