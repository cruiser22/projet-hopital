package model;

import java.util.ArrayList;

public class Salle {
	private int numero;
	private ArrayList<Visite> visites;
	private Medecin medecin;

	private final int nbVisiteMax = 10;

	public Salle(int numero) {
		this.numero = numero;
		visites = new ArrayList<Visite>(nbVisiteMax);
	}

	public String toString() {
		return "Salle [numero=" + numero + ", visites=" + visites + ", medecin=" + medecin + ", nbVisiteMax="
				+ nbVisiteMax + "]";
	}

	public ArrayList<Visite> getVisites() {
		return visites;
	}

	public int getNumero() {
		return numero;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	public void ajouterVisite(Visite visite) {
		if (visites.size() >= nbVisiteMax) {
			// envoi base de donnees
			envoiListesBDD();
		}
		visites.add(visite);
	}

	public void envoiListesBDD() {
		// envoie bdd
		visites.clear();
	}
}
