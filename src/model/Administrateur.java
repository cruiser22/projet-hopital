package model;

import dao.AuthentificationDAO;
import dao.PatientDAO;

public class Administrateur extends Personnel {

	public Administrateur(String login, String password, String nom, int metier) {
		super(login, password, nom, metier);
		// TODO Auto-generated constructor stub
	}

	public void supprimerPatient(int id) {
		PatientDAO dao = new PatientDAO();
		// dao.supprimerPatient(id);
	}

	public void modifierPatient(int id, Patient patientModifie) {
		PatientDAO dao = new PatientDAO();
		// dao.modifierPatient(id, patientModifie);
	}

	public void creerPatient(Patient patient) {
		PatientDAO dao = new PatientDAO();
		// dao.creerPatient(patient);
	}

	public void creerMedecin(Medecin medecin) {
		AuthentificationDAO dao = new AuthentificationDAO();
		// if (dao.existeMedecin() != null) {
		// dao.creerMedecin(medecin)
		// }
	}

	public void supprimerMedecin(int id) {
		AuthentificationDAO dao = new AuthentificationDAO();
		// dao.supprimerMedecin(id);
	}

	public void creerSecretaire(Secretaire secretaire) {
		AuthentificationDAO dao = new AuthentificationDAO();
		// if (dao.existeSecretaire() != null) {
		// dao.creerMedecin(secretaire)
		// }
	}

	public void supprimerSecretaire(int id) {
		AuthentificationDAO dao = new AuthentificationDAO();
		// dao.supprimerSecretaire(id);
	}
}
