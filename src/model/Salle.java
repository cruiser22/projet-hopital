package model;

import java.util.ArrayList;

public class Salle {
	private int numero;
	private ArrayList<Visite> visites;
	private ArrayList<Medecin> medecins;

	private final int nbVisiteMax = 10;

	public Salle(int numero) {
		this.numero = numero;
		visites = new ArrayList<Visite>(nbVisiteMax);
		medecins = new ArrayList<Medecin>(2);
	}

	public String toString() {
		return "Salle [numero=" + numero + ", medecins=" + medecins + ", nbVisiteMax=" + nbVisiteMax + "]";
	}

}
