package model;

import java.util.Date;

public class Visite {

	private int id;
	private Patient patient;
	private Medecin medecin;
	private Salle salle;
	private final int tarif = 23;
	private Date date;
	public int getId() {
		return id;
	}
	public Patient getPatient() {
		return patient;
	}
	public Medecin getMedecin() {
		return medecin;
	}
	public Salle getSalle() {
		return salle;
	}
	public int getTarif() {
		return tarif;
	}
	public Date getDate() {
		return date;
	}

	
	
}
