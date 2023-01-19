package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dao.PatientDAO;
import dao.VisiteDAO;

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

		getHopital().getFileAttente().add(patient);
		FichierDate(patient);

	}

	public Patient afficherProchainPatient() {
		return getHopital().getFileAttente().getFirst();
	}

	public void modifierPatient(int id, String telephone, String adresse) {
		PatientDAO dao = new PatientDAO();
		// dao.modifierPatient(id, telephone, adresse);
	}

	public ArrayList<Visite> getListeVisites(int patientId) {
		VisiteDAO dao = new VisiteDAO();
		return dao.getVisiteBypatientId(patientId);
	}

	public void FichierDate(Patient patient) {
		try {
			FileWriter fw = new FileWriter("patients_heure_arrive.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			bw.write("Patient : " + patient.getNom() + " " + patient.getPrenom() + " arrivé a "
					+ dateFormat.format(date));
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
