package model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Hopital {
	private static Hopital instance;
	private ArrayList<Salle> salles;
	private LinkedList<Patient> fileAttente;

	private Hopital() {
		salles = new ArrayList<Salle>();
		fileAttente = new LinkedList<Patient>();
	}

	public void ajouterSalle(Salle salle) {
		salles.add(salle);
	}

	public LinkedList<Patient> getFileAttente() {
		return fileAttente;
	}

	public ArrayList<Salle> getSalles() {
		return salles;
	}

	public static Hopital getInstance() {
		if (instance == null) {
			instance = new Hopital();
		}
		return instance;
	}

}
