package model;

import java.time.LocalDateTime;

public class Visite {

	private int id;
	private int idPatient;
	private String medecin;
	private int numSalle;
	private final int tarif = 23;
	private LocalDateTime date;

	public Visite(int id, int idPatient, String medecin, int numSalle, LocalDateTime date) {
		this.id = id;
		this.idPatient = idPatient;
		this.medecin = medecin;
		this.numSalle = numSalle;
		this.date = date;
	}

	public Visite(int idPatient, String medecin, int numSalle, LocalDateTime date) {
		this.idPatient = idPatient;
		this.medecin = medecin;
		this.numSalle = numSalle;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Visite [id=" + id + ", idPatient=" + idPatient + ", medecin=" + medecin + ", numSalle=" + numSalle
				+ ", tarif=" + tarif + ", date=" + date + "]";
	}

}
	
	