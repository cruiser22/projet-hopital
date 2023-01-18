package model;

import java.util.LinkedList;

public class Personnel {
	private String login, password, nom;
	private int metier;
	private Hopital hopital = Hopital.getInstance();

	public Personnel(String login, String password, String nom, int metier) {
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.metier = metier;
	}

	public String toString() {
		return "Personnel [login=" + login + ", password=" + password + ", nom=" + nom + ", metier=" + metier + "]";
	}

	public Hopital getHopital() {
		return hopital;
	}

	public LinkedList<Patient> afficherFilleAttente() {
		return hopital.getFilleAttente();
	}

	public void sortirMenu() {

	}
}
