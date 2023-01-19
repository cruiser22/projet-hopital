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
		dao.deletePatient(id);
	}

	public void modifierPatient(int idPatient, Patient patient) {
		PatientDAO dao = new PatientDAO();
		dao.updatePatient(idPatient, patient);
	}

	public void creerPatient(Patient patient) {
		PatientDAO dao = new PatientDAO();
		dao.createPatient(patient);
	}

	public void creerMedecin(Medecin medecin) {
		AuthentificationDAO dao = new AuthentificationDAO();
		if (dao.getMedecin(medecin.getLogin()) == null) {
			dao.createMedecin(medecin);
		}
	}

	public void supprimerMedecin(String login) {
		AuthentificationDAO dao = new AuthentificationDAO();
		dao.deleteMedecin(login);
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
