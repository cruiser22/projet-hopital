package model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Hopital {
	private static Hopital instance;
	private ArrayList<Salle> salles;
	private LinkedList<Patient> filleAttente;

	private Hopital() {
		salles = new ArrayList<Salle>();
		filleAttente = new LinkedList<Patient>();
	}

	public void ajouterSalle(Salle salle) {
		salles.add(salle);
	}

	public LinkedList<Patient> getFilleAttente() {
		return filleAttente;
	}

	public static Hopital getInstance() {
		if (instance == null) {
			instance = new Hopital();
		}
		return instance;
	}

}
