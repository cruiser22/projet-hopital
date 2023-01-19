package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import dao.VisiteDAO;

public class Medecin extends Personnel {

	Salle salle;
	Patient patient;

	public Medecin(String login, String password, String nom, int metier) {
		super(login, password, nom, metier);
		salle = Hopital.getInstance().getSalles().get(metier - 1);
		salle.setMedecin(this);
	}

	public void salleDispo() {
		if (this.getFileAttente().size() > 0) {
			patient = this.getFileAttente().pop();
			salle.ajouterVisite(new Visite(patient.getId(), getNom(), salle.getNumero(), LocalDateTime.now()));
		}
	}

	public void sauvegarderVisites() {
		salle.envoiListesBDD();
	}

	public ArrayList<Visite> getListeVisites() {
		VisiteDAO dao = new VisiteDAO();
		return dao.getVisiteByMedecin(getNom());
	}

}
