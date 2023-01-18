package model;

public class Secretaire extends Personnel {

	public Secretaire(String login, String password, String nom, int metier) {
		super(login, password, nom, metier);
	}

	public void ajouterPatientFilleAttente(Patient patient) {
		// si patient existe pas
		// ajouter patient base de donnees
		getHopital().getFilleAttente().add(patient);

	}

	public Patient afficherProchainPatient() {
		return getHopital().getFilleAttente().getFirst();
	}

}
