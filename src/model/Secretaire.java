package model;

import dao.PatientDAO;

public class Secretaire extends Personnel {

	public Secretaire(String login, String password, String nom, int metier) {
		super(login, password, nom, metier);
	}

	public void ajouterPatientFilleAttente(Patient patient) {
		PatientDAO dao = new PatientDAO();
		Patient tempPatient = dao.getPatient(patient.getId());
		if (tempPatient == null) {
			dao.createPatient(patient);
		}
		getHopital().getFilleAttente().add(patient);

	}

	public Patient afficherProchainPatient() {
		return getHopital().getFilleAttente().getFirst();
	}

}
